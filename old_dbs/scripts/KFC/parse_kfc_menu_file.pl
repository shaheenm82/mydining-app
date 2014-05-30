#!/usr/bin/perl
use strict;
use warnings;
use LWP;
#use XML::Simple;
#use HTML::Parser ();

open(MYOUTFILE, ">kfc_menus.sql"); #open for write, overwrite

  my $browser = LWP::UserAgent->new( );
  my $response = $browser->get("http://www.kfc.co.za/c/menu/");

  print $response->code();
  my $content = $response->content();
  parse_html($content);
  #print $content;


sub parse_html {
  my @lines = split("\n",$_[0]);
  my $line;
  while (@lines) {
    $line = shift @lines;
    chomp($line);

    if ($line =~ m/menu_item/) {
      $line = shift @lines;
      chomp($line);
      if ($line =~ m/a href/) {
        $line =~ s/^.*="//;
        $line =~ s/".*$//;
        print $line."\n";
          #return $line;
        get_sub_menu($line);
      }
      #return $line;

    }
  }
}

sub get_sub_menu {
  my $category = $_[0];
  my $browser = LWP::UserAgent->new( );
  my $response = $browser->get("http://www.kfc.co.za/c".$category);

  my $content = $response->content();

  #$category =~ s///;
  
  parse_sub_menu($content);
  #print $content;
}

sub parse_sub_menu {
  my @lines = split("\n",$_[0]);
  my $line;
  while (@lines) {
    $line = shift @lines;
    chomp($line);
    $line =~ s/(^\s+|\s+$)//;

    if ($line =~ m/class="\/menu\/"/) {
      $line =~ s/<.+?>//g;
      $line =~ s/KFC Menu: //;
      my $sql = "INSERT INTO menu_category (master_category, category) \nVALUES ('<mc>','".$line."');\n\n";
      #
      print "$sql\n";
      print MYOUTFILE $sql;
    }

    if ($line =~ m/menu_group_item/) {
      $line = shift @lines;
      chomp($line);
      if ($line =~ m/a href/) {
        $line =~ s/^.*="//;
        $line =~ s/".*$//;
        print $line."\n";
          #return $line;
        get_menu_item($line);
      }
      #return $line;
    }
  }
}

sub get_menu_item {
  my $browser = LWP::UserAgent->new( );
  my $response = $browser->get("http://www.kfc.co.za/c".$_[0]);

  my $content = $response->content();
  parse_menu_item($content);
  #print $content;
}

sub parse_menu_item {
  my @lines = split("\n",$_[0]);
  my $line;
  my $name;
  my $description;
  my $price;

  while (@lines) {
    $line = shift @lines;
    chomp($line);
    $line =~ s/(^\s+|\s+$)//;

    if ($line =~ m/meal_info/) {
      while ($line !~ m/<\/div>/) {
        $line = shift @lines;
        chomp($line);
        print $line;

        if ($line =~ m/h1/) {
        $line =~ s/<.+?>//g;
        $line =~ s/(^\s+|\s+$)//;
        $name = $line;
        print "Name: ".$line."\n";
        }
      
      #$line = shift @lines;
      #chomp($line);
        if ($line =~ m/desc/) {
          $line =~ s/<.+?>//g;
          $line =~ s/(^\s+|\s+$)//;
          $description = $line;
          print "Desc".$line."\n";
        }

      #$line = shift @lines;
      #chomp($line);
        if ($line =~ m/price/) {
          $line =~ s/<.+?>//g;
          $line =~ s/(^\s+|\s+$)//;
          $price = $line;
          print "Price: ".$line."\n";
        }
          #return $line;
        #get_menu_item($line);
      #return $line;
      }
      my $sql = "INSERT INTO menu (rest_id, category, dish, description,cost) \nVALUES (<r_id>,<category>,'".$name."','".$description."','".$price."');\n\n";
      #
      print "$sql\n";
      print MYOUTFILE $sql;
    }
  }
}
# create object

# read XML file
#my $data = $xml->XMLin("steers_branches.xml");
#
## dereference hash ref
## access <employee> array
#foreach $e (@{$data->{marker}})
#{
#  my $name = ucfirst($e->{name});
#  if ($e->{address3} =~ m//) {
#    if ($e->{address2} =~ m//) {
#      $suburb = ucfirst($e->{address1});
#      $address = ucfirst($e->{address1});
#    }else{
#      $suburb = ucfirst($e->{address2});
#      $address = ucfirst($e->{address1}).", ".ucfirst($e->{address2});
#    }
#    $suburb = ucfirst($e->{address3});
#  }else{
#    $address = ucfirst($e->{address1}).", ".ucfirst($e->{address2}).", ".ucfirst($e->{address3});
#  }
#
#  my $telephone = formatTel($e->{tel});
#
#  my $lat = $e->{lat};
#  my $lng = $e->{lng};
#
#  my $sql = "INSERT INTO branch (rest_id, name, province, suburb, address, telephone_no, location_lat, location_long, halaal) \nVALUES (3,'".$name."','Gauteng','".$suburb."','".$address."','".$telephone."','".$lat."','".$lng."',0);\n\n";
#
#  #print "$sql\n";
#  print MYOUTFILE $sql;
#}
#
#sub formatTel {
#  my $tel;
#  my $t;
#  my @tels = split("-",@_[0]);
#
#  $tel = "(".$tels[0].") ".$tels[1]."-".$tels[2];
#
#  return $tel;
#}
