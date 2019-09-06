=== PCF Shell ===

Useful commands

cf-cli
cf top
cf bg-restage
cf mysql-tools
cf local
cf drains plugin
cf log-cache plugin
cf SchedulerForPCF plugin
cf dataflow-shell

credhub
kubectl
om-cli
bosh
gfsh (gfsh98, gfsh97, gfsh96)
mysqlsh
fly-cli
uaa-cli
git
jq
pks

tmux

javac (v12)
mvn
gradle

Single instance mode - just push 'PcfShellInstances' to an org/space - NOTE - modify PcfShellInstances/bin/haproxy/haproxy.conf to ensure your default password is unique
			default username/password - pcfuser / pcfpass


Auto deploy scaled mode with 'setupPcfShell.sh' - ensure space-developer has permissions to set cf networking policies. Target cf foundtaion and space. 
pushd PcfShellRouter
mvn package
popd
setupPcfShell.sh <shellname> <instance-count>

Note if more than 2gb disk required by instance, execute cf scale command on that app.

Scaled mode -
	Push PcfShellInstances app to an org/space
	Map-route to an internal domain - e.g. 
		cf map-route pcfshellinstances apps.internal --hostname pcfshellinstances
	Push PcfShellInstancesRouter to an org/space. Modify it's manifest.yml zuul.routes.primary.url to the internal address you're exposing internal pcfshell instances app on
	Add networking policy to enable direct access from InstancesRouter to Instances
		cf add-network-policy PcfShellInstancesRouter --destination-app pcfshellinstances --protocol tcp --port 8080
	
User can & SHOULD modify password using 'setpassword' utility within running instance of the shell.
User should call 'logout' script to reset instance at end of session.

Multiple gfsh versions for backward compatibility - gfsh98, gfsh97, gfsh96
NOTE: OpenJDK and PiotalGemfire not included;	
		openjdk-12.0.1_linux-x64_bin.tar.gz 
		pivotal-gemfire-9.8.1.tgz 
		pivotal-gemfire-9.7.2.tgz 
		pivotal-gemfire-9.6.2.tgz 
		gradle-5.6.2-bin.zip

Download these assets and them to the PcfShellInstances/bin folder


