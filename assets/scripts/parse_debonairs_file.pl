#!/usr/bin/perl
use Geo::Coder::Google;
use LWP;

open(MYINPUTFILE, "debonairs_rest_import.txt");
open(MYFETCHFILE, "debonaires_details_list.txt");
open(MYOUTFILE, ">debonaires_branches.sql"); #open for write, overwrite
#https://www.debonairspizza.co.za/Store/GetStoreDetailsPopup/1

while (<MYINPUTFILE>) {
  next if (/^\s+$/);

  # Good practice to store $_ value because
  # subsequent operations may change it.
  my($line) = $_;

  $line =~ s/^\s+//;
  chomp($line);

  $name = $line;

  $line = <MYINPUTFILE>;
  $line =~ s/^\s+//;
  chomp($line);

  $address = $line;
  @addresses = split(", ",$address);
  $suburb = $addresses[-1];
  my $map_addr = $addresses[-1].",".$addresses[-1];

  $line = <MYINPUTFILE>;
  $line =~ s/^\s+//;
  chomp($line);

  if ($line =~ m/Halaal/) {
    $halaal = 1;
  }else{
    $halaal = 0;
  }

  $fetch = <MYFETCHFILE>;
  chomp($fetch);
  $fetch =~ s/,.*$//;

  #print "$fetch\n";
  my $browser = LWP::UserAgent->new( );
  my $response = $browser->get("https://www.debonairspizza.co.za/Store/GetStoreDetailsPopup/".$fetch);

  my $content = $response->content();

  my $telephone = parse_html($content);

  my $geocoder = Geo::Coder::Google->new(apiver => 3);
  my $location;

  eval { $location = $geocoder->geocode(location => $map_addr) };
  warn() if $@;
  #my $location = $geocoder->geocode(location => $map_addr); 

  my $lat = $location->{geometry}{location}{lat};
  my $lng = $location->{geometry}{location}{lng};
  #print "$lat $lng\n";

  $sql = "INSERT INTO branch (rest_id, name, province, suburb, address, telephone_no, location_lat, location_long, halaal) \nVALUES (4,'".$name."','Gauteng','".$suburb."','".$address."','".$telephone."','".$lat."','".$lng."',".$halaal.");\n\n";

  #print "$sql\n";
  print MYOUTFILE $sql;
}

sub parse_html {
  my @lines = split("\n",$_[0]);
  my $line;
  while (@lines) {
    $line = shift @lines;
    chomp($line);

    if ($line =~ m/Telephone/) {
      $line =~ s/^.*<\/strong>//;
      $line =~ s/[(|)|-|\s]//g;
      $line = substr($line,0,10);
      
      $line =~ s/([0-9]{3})([0-9]{3})([0-9]{4})/($1) $2-$3/;
      return $line;
    }
  }
}
