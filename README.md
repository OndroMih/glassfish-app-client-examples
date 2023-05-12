# Example GlassFish Application client project

This will execute a standalone Java application inside a GlassFish application client container, which is connected to a GlassFish server.

This example client applicaiton calls a simple remote EJB deployed on the GlassFish server.

## Requirements

This project requires Eclipse GlassFish Server 7 and Java 11 or newer.

If you want to use it with an older version of GlassFish server, you need to do the following changes:
* In pom.xml file, change the version of the `jakarta.jakartaee-api` dependency from `10.0.0` to `8.0.0`
* In all `*.java` files, change `jakarta.` in imports to `javax.`, e.g. change `import jakarta.ejb.EJB;` to `import javax.ejb.EJB`

Otherwise everything should work as described below.

## Build the application

Build the application JAR file:

```
mvn package
```

The application JAR file will be created in `target/sampleclient.jar`

## Generate GlassFish appclient launcher

To run the client application, we need a GlassFish launcher to enrich our application and execute it in a container, in a similar way as it is executed using Java Web Start.

We need GlassFish server installed on the machine where we build the application from sources. GlassFish server doesn't need to be running.

To generate the launcher quickly, just execute the following (provide the path to the GlassFish installation):

```
mvn -P prepare antrun:run -Dgf.install="Path to GlassFish installation"
```

This will generate appclient.jar needed to run the application without GlassFish installation and unpack it into the `target/appclient` directory in this project.

## Deploy the application on GlassFish server

To simplify this example, the client application also contains the actual EJB. The same JAR file can be deployed to GlassFish server. When the client is started, it won't call the EJB in the client but it will connect to the GlassFish server and execute the EJB deployed on the server.

To deploy on GlassFish server, open GlassFish Admin Console and deploy the file `target/sampleclient.jar` as an application. In a real-world case, we would deploy an EAR file. But for this demo, a JAR file is enough.


## Launch the client application on the machine with GlassFish

Execute the following command:

On Windows:

```
target\appclient\glassfish\bin\appclient.bat -client target\sampleclient.jar
```

On Linux/Mac (may require adding permissions to execute the `target/appclient/glassfish/bin/appclient` script:

```
target/appclient/glassfish/bin/appclient -client target/sampleclient.jar
```

The client application should print the following:

```
INFO: ORB initialization succeeded: com.sun.corba.ee.impl.orb.ORBImpl@8c12524
Client
Finished
```

In GlassFish server, you should see the following message in the log, with the words "Hello from a remote EJB":

```
[INFO] [] [jakarta.enterprise.logging.stdout] [tid: _ThreadID=57 _ThreadName=p: thread-pool-1; w: 1] [levelValue: 800] [[
  Hello from a remote EJB]]
```

## Launch the client application on a machine remote from GlassFish

To launch the client and connect it to a remote GlassFish server, we need to specify the location of the GlassFish server.

To specify it on the command line:

```
target/appclient/glassfish/bin/appclient -client target/sampleclient.jar -targetserver glassfishserver.com
```

We can also specify this in the configuration file `target/appclient/glassfish/config/asenv.bat` on Windows (or `asenv.conf` on Linux/Mac). There we need to change the value of `address` in the `target-server` element, such as:

```
<target-server name="GlassFish server" address="glassfishserver.com" port="3700"/>
```

Then we can just launch the client with:

```
target/appclient/glassfish/bin/appclient -client target/sampleclient.jar
```

For more information how to configure the launcher, look at the GlassFish documentation about the package-client script: https://glassfish.org/docs/latest/reference-manual.html#package-appclient .

## Apply the recipe for an EAR application

If you have an EAR application that contains the Java client downloaded and started using Java Web Start, you need to do the following:

* Extract the Java client part into a separate project and remove it from the final EAR application
* Copy the directory `target/appclient` from this project into the Java client part in your project
* Launch your client application like the client application in this project but replace references to `target/sampleclient.jar` with references to your application JAR file
