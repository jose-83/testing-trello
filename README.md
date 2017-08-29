# Trello Testing

This project provides a framework for testing functionalities of Trello. It contains a few selenium tests and uses Maven as a build tool.    

## Getting Started

It can be run on Mac or Windows platform.

### Prerequisites

Follow these instructions: https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html and install maven (version 3.3 or greater) based on your OS.
Java version 1.8, Chrome(version 60.0.3112.101 or greater), and Firefox(version 55.0.3 or greater) must have been installed and can be run on your local machine.

## Running the tests

On the command line, navigate to project root directory and run:(it compiles all testss and runs only smoke test which is here testing the log in functionality)
```
mvn clean install
```

For running all other tests, one should test the unit profile which has been defined in the maven pom:
```
mvn test -Punit
```

The default browser is Chrome. For running test on Firefox, you should pass proper property: 
```
mvn test -Punit -Drun.config.browser=firefox
```

