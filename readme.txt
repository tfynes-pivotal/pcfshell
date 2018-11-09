=== PCF Shell ===

Useful commands

cf-cli
cf top
kubectl
om-cli
bosh
gfsh
uaac
mysqlsh
fly-cli
git
jq
...

Single instance mode - just push 'PcfShellInstances' to an org/space - NOTE - modify PcfShellInstances/bin/haproxy/haproxy.conf to ensure your default password is unique
			default username - pcfuser

Scaled mode -
	Push PcfShellInstances app to an org/space
	Map-route to an internal domain - e.g. 
		cf map-route pcfshellinstances apps.internal --hostname pcfshellinstances
	Push PcfShellInstancesRouter to an org/space. Modify it's manifest.yml zuul.routes.primary.url to the internal address you're exposing internal pcfshell instances app on
	Add networking policy to enable direct access from InstancesRouter to Instances
		cf add-network-policy PcfShellInstancesRouter --destination-app pcfshellinstances --protocol tcp --port 8080
	


User can & SHOULD modify password using 'setpassword' utility within running instance of the shell.
User should call 'logout' script to reset instance at end of session.


NOTE: OpenJDK and PiotalGemfire not included;	
		openjdk-11.0.1_linux-x64_bin.tar.gz  ( https://download.java.net/java/GA/jdk11/13/GPL/openjdk-11.0.1_linux-x64_bin.tar.gz )
		pivotal-gemfire-9.3.0.tgz  ( https://network.pivotal.io/products/pivotal-gemfire#/releases/40937 )

Download these assets and them to the PcfShellInstances/bin folder
