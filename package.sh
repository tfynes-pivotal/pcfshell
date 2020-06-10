#!/bin/bash

# basic script for assembling a distributable zip inclusive of all offline assets
#cp -R ./pcfshell-forGH ./pcfshell
cp pcfshell-offline-assets/* ./pcfshell/PcfShellInstances/bin
pushd pcfshell/PcfShellInstanceRouter
mvn package
popd
zip -r pcfshell-06-10-20.zip ./pcfshell/*
mkdir x
cp ./pcfshell/setupPcfShell.sh ./x/
rm -fr ./pcfshell
cd x
cp ../pcfshell-06-10-20.zip ./pcfshell.zip


