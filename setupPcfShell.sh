#!/bin/bash

# PCFShell Cluster Deployer v0.1 alpha
# @fynesy
#


if [ "$#" -ne 2 ];
  then
     echo "Usage setupPcfshell.sh <hostname> <instances>" && exit 1
fi

hostname=$1
instance_count=$2


# Assumes cf already targeting foundation/org/space in which pcfshell will run
# Inputs: 
#   
#
#

#Download pcfshell asset (including all cli tools and dependencies)
curl -L  -o pcfshell.zip https://<repo containing zipped assets inc all deps>

#Extract
unzip ./pcfshell.zip

#Push PcfShellInstances

	pushd ./pcfshell/PcfShellInstances

	#Create random-prefix for pcfshellinstances hostname on apps.internal domain
	  UUID=$(env LC_CTYPE=C LC_ALL=C tr -dc "a-z" < /dev/urandom | head -c 10)
          cf push -d apps.internal -n $UUID-pcfshellinstances -i $instance_count
	  popd

#Push PcfShellInstanceRouter

	pushd ./pcfshell/PcfShellInstanceRouter

	#push surgical instance routing api gateway
	   cf push -n $hostname --no-start
	#update instance router with location of pcfshellinstances
	   cf set-env pcfshell zuul.routes.primary.url "http://$UUID-pcfshellinstances.apps.internal:8080"
        #enable c2c networking policy 
    	   cf add-network-policy pcfshell --destination-app pcfshellinstances --protocol tcp --port 8080
        #start instance router
           cf start pcfshell
	popd

