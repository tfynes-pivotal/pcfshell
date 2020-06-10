#!/bin/bash
cp -R ./pcfshell-forGH ./pcfshell
cp pcfshell-offline-assets/* ./pcfshell/PcfShellInstances/bin
pushd pcfshell/PcfShellInstanceRouter
mvn package
popd
zip -r pcfshell-06-10-20.zip ./pcfshell/*
rm -fr ./pcfshell
mkdir x
cd x
cp ../pcfshell-06-10-20.zip ./pcfshell.zip
cp ../pcfshell-forGH/setupPcfShell.sh ./


