#!/usr/bin/perl


# Adds a new application to busybox


use strict;
use English;

if(@ARGV == 0) {
    die "Usage: $0 package directory\n";
}

my $dir=$ARGV[0];


my $curParagraph = "";
my $curName;
my $ifLevel = 0;

my %newParagraphs;

sub processLine {
    if(/^\s*#\s*if/) {
	$ifLevel++;
    } elsif(/^\s*#\s*endif/) {
	$ifLevel--;
    } elsif(/^\s*(\/\/)?\s*IF_.*?APPLET.*?\(\s*(.*?)\s*,/) {
	$curName=$2;
    } elsif(/^\s*}/) {
	$curName="{";
    }
    $curParagraph .= $_;
}

foreach my $dir (@ARGV) {
    if(-f "$dir/applets.h") {
	open(NEW_PARAGRAPH, "<$dir/applets.h") ||
	    die "Could not read $dir/applets.h ($ERRNO)\n";
	while(<NEW_PARAGRAPH>) {
	    processLine;
	    if($ifLevel == 0 && defined $curName) {
		print "Adding paragraph for $curName\n";
		$newParagraphs{$curName} = $curParagraph;
		undef $curName;
		$curParagraph = "";
	    }
	}
	close(NEW_PARAGRAPH);

	undef $curName;
	$curParagraph = "";
    }
}
    
if(%newParagraphs) {
    my $oldName="include/applets.h";
    my $newName="$oldName.$$";
    open(NEW, ">", $newName) || die "Could not write $newName ($ERRNO)\n";
    open(APPLETS_H, "<", $oldName) || die "Could not read $oldName ($ERRNO)\n";
    while(<APPLETS_H>) {
	processLine;
	if($ifLevel == 0) {
	    if(defined $curName) {
		foreach my $newName (sort { $a cmp $b } keys %newParagraphs) {
		    if($curName eq $newName) {
			delete $newParagraphs{$newName};
		    }
		    if($curName gt $newName) {
			print NEW $newParagraphs{$newName};
			delete $newParagraphs{$newName};
		    }
		}
	    }
	    print NEW $curParagraph;
	    undef $curName;
	    $curParagraph = "";
	}
    }
    close(APPLETS_H);
    close(NEW);
    rename($newName,$oldName) ||
	die "Could not rename $newName to $oldName ($ERRNO)\n";
}

# Do Makefile
my %subMakefiles;
foreach my $dir (@ARGV) {
#    if(-f "$dir/Makefile") {
	$subMakefiles{$dir}=1;
#    }
}

if(%subMakefiles) {
    my $oldName="Makefile";
    my $newName="Makefile.$$";
    open(NEW, ">", $newName) || die "Could not write $newName ($ERRNO)\n";
    my $on=0;
    my $pfx="";
    open(MAKEFILE, "<", $oldName) || die "Could not read $oldName ($ERRNO)\n";
    while(<MAKEFILE>) {
	if(/^libs-y\s+:=\s*\\$/) {
	    $on=1;
	}
	if(/^\s*$/) {
	    if($on) {
		foreach my $dir (keys %subMakefiles) {
		    print NEW "$pfx$dir/ \\\n";
		}
	    }
	    $on=0;
	}
	if($on && /^(\s*)(.*)\/ \\$/) {
	    $pfx=$1;
	    delete $subMakefiles{$2};
	}
    print NEW $_;
    }
    close(MAKEFILE);
    close(NEW);
    rename($newName,$oldName) ||
	die "Could not rename $newName to $oldName ($ERRNO)\n";
}

# Do Config.in
my %configIn;
foreach my $dir (@ARGV) {
    if(-f "$dir/Config.in") {
	$configIn{$dir}=1;
    }
}

if(%configIn) {
    my $oldName="Config.in";
    my $newName="$oldName.$$";
    open(NEW, ">", $newName) ||
	die "Could not write $newName ($ERRNO)\n";
    open(CONFIG_IN, "<", $oldName) ||
	die "Could not read $oldName ($ERRNO)\n";
    while(<CONFIG_IN>) {
	if(/^\s*source\s+(.*)\/Config.in\s*$/) {
	    delete $configIn{$1};
	}
	print NEW $_;
    }
    close(CONFIG_IN);

    foreach my $dir (keys %configIn) {
	print NEW "source $dir/Config.in\n";
    }
    close(NEW);
    rename($newName,$oldName) ||
	die "Could not rename $newName to $oldName ($ERRNO)\n";
}

# do usage.h
my %usageH;
foreach my $dir (@ARGV) {
    if(-f "$dir/usage.h") {
	$usageH{$dir}=1;
    }
}

if(%usageH) {
    my $oldName="include/usage.h";
    my $newName="$oldName.$$";
    open(NEW, ">", $newName) ||
	die "Could not write $newName ($ERRNO)\n";
    open(USAGE, "<", $oldName) ||
	die "Could not read $oldName ($ERRNO)\n";
    while(<USAGE>) {
	if(/^\s*\#include\s+"..\/(.*)\/usage\.h"\s*$/) {
	    delete $usageH{$1};
	}
	print NEW $_;
    }
    close(USAGE);

    foreach my $dir (keys %usageH) {
	print NEW "#include \"../$dir/usage.h\"\n";
    }
    rename($newName,$oldName) ||
	die "Could not rename $newName to $oldName ($ERRNO)\n";
}

my %makefileFlags;
foreach my $dir (@ARGV) {
    if(-f "$dir/Makefile.flags") {
	$makefileFlags{$dir}=1;
    }
}

if(%makefileFlags) {
    my $oldName="Makefile.flags";
    my $newName="$oldName.$$";
    open(NEW, ">", $newName) ||
	die "Could not write $newName ($ERRNO)\n";
    open(MAKEFILE, "<", $oldName) ||
	die "Could not read $oldName ($ERRNO)\n";
    while(<MAKEFILE>) {
	if(/^\s*include \$\(srctree\)\/(.*)\/Makefile.flags*$/) {
	    delete $makefileFlags{$1};
	}
	print NEW $_;
    }
    close(MAKEFILE);

    foreach my $dir (keys %makefileFlags) {
	print NEW "include \$(srctree)/$dir/Makefile.flags\n";
    }
    rename($newName,$oldName) ||
	die "Could not rename $newName to $oldName ($ERRNO)\n";
}
