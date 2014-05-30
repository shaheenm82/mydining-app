#!/usr/bin/perl
#push(@INC, '/home/shaheen/app/shaheen/product/11.2.0/client_1/perl/lib/site_perl/5.10.0/');
#use lib '/home/shaheen/app/shaheen/product/11.2.0/client_1/perl/lib/site_perl/5.10.0/';
use strict;
use warnings;

use LWP;
use HTTP::Response;
use HTTP::Message;

sub find_branch_details {
  my $line;
  my @latlng;
  my $suburb;
  my @addr_items;
  my $address;
  my $telephone;
  
  my $province = $_[0];
  my $name = $_[1];
  my $fetch = $_[2];

  print $province."\n";
  print $name."\n";
  print $fetch."\n";

  my $browser1 = LWP::UserAgent->new( );
  my $response1 = $browser1->get("http://www.fishaways.co.za/".$fetch);

  my $content1 = $response1->content();

  my @lines = split("\n",$content1);
  while (@lines) {
    
    $line = shift @lines;
    #print "branch content $line\n";
    #next if (/^\s+$/);

    $line =~ s/^\s+//;
    $line =~ s/\s+$//;

    chomp($line);
    
    if ($line =~ m/restaurant-info/) {
      while ($line !~ m/open-times/) {
        $line = shift @lines;

        $line =~ s/^\s+//;
        $line =~ s/\s+$//;

        chomp($line);
        #print "branch details $line\n";

        if ($line =~ m/h2/) {
          $line =~ s/<.+?>//g;
          #$line =~ s/^\s+//;
          #$line =~ s/<h2>//;
          #$line =~ s/<\/h2>//;

          $name = $line;
          $name =~ s/^\s+|\s+$//g;

          print "$name\n";
        }
        if ($line =~ m/maps.google.co.za/) {
          $line =~ s/^.*ll=//;
          $line =~ s/&z=.*$//;

          @latlng = split(",",$line);
          print "$latlng[0], $latlng[1]\n";
        }
        if ($line =~ m/restaurant-address/) {
          $line = shift @lines;
          $line =~ s/^\s+//;
          $line =~ s/<\/div>//;
          $line =~ s/<.+?>//g;

          $address = $line;
          $address =~ s/^\s+|\s+$//g;

          @addr_items = split(",",$address);
          $suburb = $addr_items[-1];
          $suburb =~ s/^\s+|\s+$//;

          print "$suburb\n$address\n";
        }

        if ($line =~ m/restaurant-tel/) {
          $line = shift @lines;
          $line =~ s/^\s+//;
          $line =~ s/ //g;
          $line = substr($line,0,10);

          $telephone = $line;
          $telephone =~ s/([0-9]{3})([0-9]{3})([0-9]{4})/($1) $2-$3/;
          print "$telephone\n";
        }
      }
    }
  }

  my $sql = "INSERT INTO branch (rest_id, name, province, suburb, address, telephone_no, location_lat, location_long, halaal) \nVALUES (5,'".$name."','".$province."','".$suburb."','".$address."','".$telephone."','".$latlng[0]."','".$latlng[1]."',1);\n\n";
#
print "$sql\n";
  print MYOUTFILE $sql;
}

sub find_branches {
  my $line;
  my $province;
  my $province_found;
  my $branch;
  my $fetch;

  $province_found = 0;

  my @lines = split("\n",$_[0]);
  while (@lines) {
    
    my $line = shift @lines;

    #next if (/^\s+$/);

    $line =~ s/^\s+//;
    $line =~ s/\s+$//;
    chomp($line);
    #print "$line\n";

    if ($line =~ m/level-0/) {
      
      while ($line !~ m/restaurant-info/) {
        $line = shift @lines;

        if ($line =~ m/^\s+$/) {
          next;
        }
        #next if (//);

        $line =~ s/^\s*//;
        $line =~ s/\s*$//;
        chomp($line);
        
        if ($line =~ m/level-1/) {
          #Province
          $province = $line;
          $province =~ s/^.+#">//;
          $province =~ s/<\/a.+$//;

          $province = join " ", map {ucfirst} split " ", $province;
          $province_found = 1;
          next
        }
        print "$line\n";

          $fetch = $line;
          $fetch =~ s/^.*href="//;
          $fetch =~ s/".*$//;

          $branch = $line;
          $branch =~ s/<.+?>//g;

          find_branch_details($province, $branch, $fetch);
        
      }  
    }
  }

#  my $sql = "INSERT INTO branch (rest_id, name, province, suburb, address, telephone_no, location_lat, location_long, halaal) \nVALUES (5,'".$name."','Gauteng','".$suburb."','".$address."','".$telephone."','".$latlng[0]."','".$latlng[1]."',0);\n\n";
#
# #print "$sql\n";
#  print MYOUTFILE $sql;
}

#open(DEBRESTFILE, "fishaways_gauteng_branches.txt");
open(MYOUTFILE, ">fishaways_branches.sql"); #open for write, overwrite

my $halaal;

my $browser = LWP::UserAgent->new( );
my $response = $browser->get("http://www.fishaways.co.za/restaurants.php");

my $content = $response->content();

find_branches($content);



