#!/usr/bin/perl
use strict;
use warnings;
use XML::Simple;

open(MYOUTFILE, ">steers_branches.sql"); #open for write, overwrite

# create object
my $e;
my $suburb;
my $address;
my $xml = new XML::Simple (KeyAttr=>[]);

# read XML file
my $data = $xml->XMLin("steers_branches.xml");

# dereference hash ref
# access <employee> array
foreach $e (@{$data->{marker}})
{
  my $name = ucfirst($e->{name});
  if ($e->{address3} =~ m//) {
    if ($e->{address2} =~ m//) {
      $suburb = ucfirst($e->{address1});
      $address = ucfirst($e->{address1});
    }else{
      $suburb = ucfirst($e->{address2});
      $address = ucfirst($e->{address1}).", ".ucfirst($e->{address2});
    }
    $suburb = ucfirst($e->{address3});
  }else{
    $address = ucfirst($e->{address1}).", ".ucfirst($e->{address2}).", ".ucfirst($e->{address3});
  }
  
  my $telephone = formatTel($e->{tel}); 
  
  my $lat = $e->{lat};
  my $lng = $e->{lng};

  my $sql = "INSERT INTO branch (rest_id, name, province, suburb, address, telephone_no, location_lat, location_long, halaal) \nVALUES (3,'".$name."','Gauteng','".$suburb."','".$address."','".$telephone."','".$lat."','".$lng."',0);\n\n";

  #print "$sql\n";
  print MYOUTFILE $sql;
}

sub formatTel {
  my $tel;
  my $t;
  my @tels = split("-",@_[0]);

  $tel = "(".$tels[0].") ".$tels[1]."-".$tels[2];
  
  return $tel;
}
