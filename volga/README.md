# Volga add-on project

Volga is a Vaadin add-on that helps you to add meta data to your Vaadin applications, which will help social media services and search engines better interpret your application.

The add-on is in its early stages so expect some changes to its API in the future.

Currently it supports following meta tags:

 * basic "description" meta tag
 * twitter cards
 * open graph meta information

It is highly suggested that you use it with [the History add-on](https://vaadin.com/directory#!addon/history), which makes it possible to serve different meta tags for different views of your applications. See [the demo project](https://github.com/mstahv/volga/tree/master/volga-example) for a usage example.

## Development instructions 

This is a Vaadin add-on project created with in.virit:vaadin-addon archetype.


1. Import to your favourite IDE
2. Run main method of the Server class to launch embedded web server that lists all your test UIs at http://localhost:9998
3. Code and test
  * create UI's for various use cases for your add-ons, see examples. These can also work as usage examples for your add-on users.
  * create browser level and integration tests under src/test/java/
  * Browser level tests are executed manually from IDE (JUnit case) or with Maven profile "browsertests" (mvn verify -Pbrowsertests). If you have a setup for solidly working Selenium driver(s), consider enabling that profile by default.
4. Test also in real world projects, e.g. create a demo project, build a snapshot release ("mvn install") and use the snapshot build in it.

## Creating releases

1. Push your changes to e.g. Github 
2. Update pom.xml to contain proper SCM coordinates (first time only)
3. Use Maven release plugin (mvn release:prepare; mvn release:perform)
4. Upload the ZIP file generated to target/checkout/target directory to https://vaadin.com/directory service (and/or optionally publish your add-on to Maven central)

