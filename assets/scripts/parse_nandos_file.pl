#!/usr/bin/perl

open(MYINPUTFILE, "nandos_gauteng.txt");
open(MYLATLNGFILE, "nandos_gauteng_latlng.txt");
open(MYOUTFILE, ">nandos_branches.sql"); #open for write, overwrite

while (<MYINPUTFILE>) {
  next if (/^\s+$/);

  # Good practice to store $_ value because
  # subsequent operations may change it.
  my($line) = $_;

  $line =~ s/^\s+//;

  if ($line =~ m/^[0-9]{1,2}\./) {
    $found = "true";            
    $address = "";
                        
    chomp($line);
                
    $name = $line;                                                
    $name =~ s/^[0-9]{1,2}\. Nando's //;                                      
    #print "$name";
                                                                      
    while ($found eq "true") {
      $line = <MYINPUTFILE>;
      chomp($line);

      $line =~ s/^\s+//;

      if ($line eq "View restaurant") {                          
        print "$line";
      }else{
        if ($line =~ m/^[0-9]{3} [0-9]{3} [0-9]{4}/) {
          $line =~ s/([0-9]{3}) ([0-9]{3}) ([0-9]{4})/(\1) \2-\3/;
          $telephone = $line;

          @addresses = split(", ",$address);
          $suburb = $addresses[-2];
          #print "$suburb\n";
          #print "$address\n";
          #print "$telephone";
          $found = "false";
        }else{          
          #chomp($line);
          $addr = $line;
          $addr =~ s/, \w*$//;
          #print $test;

          if ($address ne "") {
            $address .= ", ".$addr;
          }else{
            $address .= $addr;
          }
          
        }        
      }
    }

    $line = <MYLATLNGFILE>;
    my @latlng_list = split(',',$line);
    my $latlng;
    my $lat;
    my $lng;
    foreach $latlng(@latlng_list) {
      if ($latlng =~ m/latitude/) {
        $lat = $latlng;
        $lat =~ s/^.*:"//;
        $lat =~ s/"$//;
        #print "$lat\n";

      }
      if ($latlng =~ m/longitude/) {
        $lng = $latlng;
        $lng =~ s/^.*:"//;
        $lng =~ s/"$//;
        #print "$lng\n";

      }
    }

    $sql = "INSERT INTO branch (rest_id, name, province, suburb, address, telephone_no, location_lat, location_long, halaal) \nVALUES (1,'".$name."','Gauteng','".$suburb."','".$address."','".$telephone."','".$lat."','".$lng."',0);\n\n";

    #print "$sql\n";
    print MYOUTFILE $sql;
  }
}
