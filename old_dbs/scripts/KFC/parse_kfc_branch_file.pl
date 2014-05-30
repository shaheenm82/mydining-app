#!/usr/bin/perl
use strict;
use warnings;
use LWP;
use XML::Simple;
#use HTML::Parser ();

open(MYOUTFILE, ">kfc_branches.sql"); #open for write, overwrite

my $e;
my $name;
my $suburb;
my $xml = new XML::Simple (KeyAttr=>[]);

# read XML file
my $data = $xml->XMLin("stores.xml");

# dereference hash ref
# access <employee> array
foreach $e (@{$data->{store}})
{
  $name = ucfirst($e->{name});
  $suburb = ucfirst($e->{city});
  my $page = lc($name);
  $page =~ s/,|-|&|\/|\(|\)//g;
  $page =~ s/ /-/g;
  $page =~ s/--/-/g;

  print $page."\n";

  my $browser = LWP::UserAgent->new( );
  my $response = $browser->get("http://www.kfc.co.za/c/stores/".$page);

  my $content = $response->content();

  parse_html($content, $name, $suburb);
# my $sql = "INSERT INTO branch (rest_id, name, province, suburb, address, telephone_no, location_lat, location_long, halaal) \nVALUES (3,'".$name."','Gauteng','".$suburb."','".$address."','".$telephone."','".$lat."','".$lng."',0);\n\n";
#
# #print "$sql\n";
# print MYOUTFILE $sql;
}

#  my $browser = LWP::UserAgent->new( );
#  my $response = $browser->get("http://www.kfc.co.za/c/menu/");
#
#  print $response->code();
#  my $content = $response->content();
#  parse_html($content);
#  #print $content;
#
#
sub parse_html {
  my @lines = split("\n",$_[0]);
  my $line;

  my $name;
  my $suburb;
  my $province;
  my $latlng;
  my $lat;
  my $lng;
  my $delivery;
  my $address;
  my $tel_code;
  my $telephone;
  my $halaal;
  my $disabled;
  my $driverthru;

  $name = $_[1];
  $suburb = $_[2];

  while (@lines) {
    $line = shift @lines;
    chomp($line);
    $line =~ s/(^\s+|\s+$)//;

    if ($line =~ m/store_btns/) {
      $line = shift @lines;
      chomp($line);
      $line =~ s/(^\s+|\s+$)//;

      $latlng = $line;
      $latlng =~ s/^.*center=//;
      $latlng =~ s/&zoom.*$//;
      my @latlng = split(",",$latlng);
      $lat = $latlng[0];
      $lng = $latlng[1];
      print $lat." ".$lng."\n";
    }

    if ($line =~ m/delivery/) {
      $delivery = 1;
    }

    if ($line =~ m/Address:/) {
      $line = shift @lines;
      chomp($line);
      $line =~ s/(^\s+|\s+$)//;
      $address = $line;
      $address =~ s/<.+?>//g;
      $address =~ s/&amp;/&/;
    }

    if ($line =~ m/Telephone:/) {
      $line = shift @lines;
      chomp($line);
      $line =~ s/(^\s+|\s+$)//;
      $telephone = $line;
      $telephone =~ s/<.+?>//g;

      $tel_code = substr($telephone,1,3);

      if ($tel_code eq "011" || $tel_code eq "012" || $tel_code eq "016" ) {
        $province = "Gauteng";
      }elsif($tel_code eq "013" || $tel_code eq "017"){
        $province = "Mpumalanga";
      }elsif($tel_code eq "014" || $tel_code eq "018"){
        $province = "North West";
      }elsif($tel_code eq "015"){
        $province = "Limpopo";
      }elsif($tel_code eq "021" || $tel_code eq "022" || $tel_code eq "023" || $tel_code eq "028"){
        $province = "Western Cape";
      }elsif($tel_code eq "024" || $tel_code eq "027" || $tel_code eq "053" || $tel_code eq "054"){
        $province = "Northern Cape";
      }elsif($tel_code eq "031" || $tel_code eq "032" || $tel_code eq "033" || 
        $tel_code eq "034" || $tel_code eq "035" || $tel_code eq "036" || 
          $tel_code eq "039"){
        $province = "KwaZulu-Natal";
      }elsif($tel_code eq "040" || $tel_code eq "041" || $tel_code eq "042" || 
        $tel_code eq "043" || $tel_code eq "044" || $tel_code eq "045" || 
          $tel_code eq "046" || $tel_code eq "047" || $tel_code eq "048" || $tel_code eq "049"){
        $province = "Eastern Cape";
      }elsif($tel_code eq "051" || $tel_code eq "056" || $tel_code eq "057" || $tel_code eq "058"){
        $province = "Free State";
      }else{
        $province = "<province>"
      }
    }

    

    if ($line =~ m/halaal_not png_bg/) {
      $halaal = 0;
    }
    if ($line =~ m/halaal png_bg/) {
      $halaal = 1;
    }

    if ($line =~ m/disabled_not png_bg/) {
      $disabled = 0;
    }
    if ($line =~ m/disabled png_bg/) {
      $disabled = 1;
    }

    if ($line =~ m/drivethru_not png_bg/) {
      $driverthru = 0;
    }
    if ($line =~ m/drivethru png_bg/) {
      $driverthru = 1;
    }
  }

  my $sql = "INSERT INTO branch (rest_id, name, province, suburb, address, telephone_no, location_lat, location_long, halaal, delivery, drive-thru, disabled) \nVALUES (6,'".$name."','".$province."','".$suburb."','".$address."','".$telephone."','".$lat."','".$lng."',".$halaal.",".$delivery.",".$driverthru.",".$disabled.");\n\n";
  print $sql;
  print MYOUTFILE $sql;
}
#
#sub get_sub_menu {
#  my $category = $_[0];
#  my $browser = LWP::UserAgent->new( );
#  my $response = $browser->get("http://www.kfc.co.za/c".$category);
#
#  my $content = $response->content();
#
#  #$category =~ s///;
#
#  parse_sub_menu($content);
#  #print $content;
#}
#
#sub parse_sub_menu {
#  my @lines = split("\n",$_[0]);
#  my $line;
#  while (@lines) {
#    $line = shift @lines;
#    chomp($line);
#    $line =~ s/(^\s+|\s+$)//;
#
#    if ($line =~ m/class="\/menu\/"/) {
#      $line =~ s/<.+?>//g;
#      $line =~ s/KFC Menu: //;
#      my $sql = "INSERT INTO menu_category (master_category, category) \nVALUES ('<mc>','".$line."');\n\n";
#      #
#      print "$sql\n";
#      print MYOUTFILE $sql;
#    }
#
#    if ($line =~ m/menu_group_item/) {
#      $line = shift @lines;
#      chomp($line);
#      if ($line =~ m/a href/) {
#        $line =~ s/^.*="//;
#        $line =~ s/".*$//;
#        print $line."\n";
#          #return $line;
#        get_menu_item($line);
#      }
#      #return $line;
#    }
#  }
#}
#
#sub get_menu_item {
#  my $browser = LWP::UserAgent->new( );
#  my $response = $browser->get("http://www.kfc.co.za/c".$_[0]);
#
#  my $content = $response->content();
#  parse_menu_item($content);
#  #print $content;
#}
#
#sub parse_menu_item {
#  my @lines = split("\n",$_[0]);
#  my $line;
#  my $name;
#  my $description;
#  my $price;
#
#  while (@lines) {
#    $line = shift @lines;
#    chomp($line);
#    $line =~ s/(^\s+|\s+$)//;
#
#    if ($line =~ m/meal_info/) {
#      while ($line !~ m/<\/div>/) {
#        $line = shift @lines;
#        chomp($line);
#        print $line;
#
#        if ($line =~ m/h1/) {
#        $line =~ s/<.+?>//g;
#        $line =~ s/(^\s+|\s+$)//;
#        $name = $line;
#        print "Name: ".$line."\n";
#        }
#
#      #$line = shift @lines;
#      #chomp($line);
#        if ($line =~ m/desc/) {
#          $line =~ s/<.+?>//g;
#          $line =~ s/(^\s+|\s+$)//;
#          $description = $line;
#          print "Desc".$line."\n";
#        }
#
#      #$line = shift @lines;
#      #chomp($line);
#        if ($line =~ m/price/) {
#          $line =~ s/<.+?>//g;
#          $line =~ s/(^\s+|\s+$)//;
#          $price = $line;
#          print "Price: ".$line."\n";
#        }
#          #return $line;
#        #get_menu_item($line);
#      #return $line;
#      }
#      my $sql = "INSERT INTO menu (rest_id, category, dish, description,cost) \nVALUES (<r_id>,<category>,'".$name."','".$description."','".$price."');\n\n";
#      #
#      print "$sql\n";
#      print MYOUTFILE $sql;
#    }
#  }
#}
