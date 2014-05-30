#!/usr/bin/perl
#push(@INC, '/home/shaheen/app/shaheen/product/11.2.0/client_1/perl/lib/site_perl/5.10.0/');
#use lib '/home/shaheen/app/shaheen/product/11.2.0/client_1/perl/lib/site_perl/5.10.0/';
use strict;
use warnings;

use LWP;
use HTTP::Response;
use HTTP::Message;

open(DEBRESTFILE, "fishaways_gauteng_branches.txt");
open(MYOUTFILE, ">fishaways_branches.sql"); #open for write, overwrite

my $halaal;

while (<DEBRESTFILE>) {
  next if (/^\s+$/);

  # Good practice to store $_ value because
  # subsequent operations may change it.
  my($line) = $_;

  $line =~ s/^\s+//;
  chomp($line);

  $line =~ s/^.*href="//;
  $line =~ s/".*$//;

# my $name = $line;
#
# $line = <DEBRESTFILE>;
# $line =~ s/^\s+//;
# chomp($line);
#
# my $address = $line;
# my @addresses = split(", ",$address);
# my $suburb = $addresses[-1];
#
# $line = <DEBRESTFILE>;
# $line =~ s/^\s+//;
# chomp($line);
#
# if ($line =~ m/Halaal/) {
#   $halaal = 1;
# }else{
#   $halaal = 0;
# }
#
# $line = <DEBDETFILE>;
# chomp($line);
# my @keys = split(', ',$line);
# 
  my $fetch = $line;
  #$fetch =~ s/([0-9]{1,3})/\1/;

  print "$fetch\n";

 my $browser = LWP::UserAgent->new( );
 my $response = $browser->get($fetch);

 my $content = $response->content();
 #print "$content\n";
#
 parse_html($content);
#
# my $sql = "INSERT INTO branch (rest_id, name, province, suburb, address, telephone_no, location_lat, location_long, halaal) \nVALUES (4,'".$name."','Gauteng','".$suburb."','".$address."','".$telephone."','','',".$halaal.");\n\n";
#
# #print "$sql\n";
# print MYOUTFILE $sql;
}

sub parse_html {
  my $line;
  my @latlng;
  my $name;
  my $suburb;
  my @addr_items;
  my $address;
  my $telephone;
  #print "$_[0]\n";

  my @lines = split("\n",$_[0]);
  while (@lines) {
    
    my $line = shift @lines;

    #next if (/^\s+$/);

    #$line =~ s/^\s+//;
    #chomp($line);
    #print "$line\n";

    if ($line =~ m/restaurant-info/) {
      while ($line ne m/days/) {
        $line = shift @lines;
        if ($line =~ m/h2/) {
          $line =~ s/^\s+//;
          $line =~ s/<h2>//;
          $line =~ s/<\/h2>//;

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

  my $sql = "INSERT INTO branch (rest_id, name, province, suburb, address, telephone_no, location_lat, location_long, halaal) \nVALUES (5,'".$name."','Gauteng','".$suburb."','".$address."','".$telephone."','".$latlng[0]."','".$latlng[1]."',0);\n\n";
#
# #print "$sql\n";
  print MYOUTFILE $sql;
}

sub parse_html2 {
  my $line;
  #print "$_[0]\n";

  my @lines = split("\n",$_[0]);
  foreach $line (@lines) {
    next if (/^\s+$/);
    
    if ($line =~ m/Telephone/) {
      $line =~ s/^.*\/strong>//;
      $line =~ s/<\/p>//;
      #$line =~ s/^\s+//;
      $line =~ s/[(|)|-| ]//g;
      $line = substr($line,0,10);
      $line =~ s/([0-9]{3})([0-9]{3})([0-9]{4})/($1) $2-$3/;
      
      return $line;
    }
  }
}
