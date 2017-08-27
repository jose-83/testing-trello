# Trello Testing

This project provides a framework for testing functionalities of Trello. It contains a few selenium tests and uses Maven as a build tool.    

## Getting Started

It can be run on Mac or Windows platform.

### Prerequisites

Chrome(version 60.0.3112.101 or greater) and Firefox(version 55.0.3 or greater) have been installed and can be run on your local machine.
Follow these instructions: https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html and install maven (version 3.3 or greater) based on your OS.

## Running the tests

```
mvn clean install
```

runs all tests on default browser which is Chrome. For running test on Firefox, you should run:
```
mvn clean install -Drun.config.browser=firefox
```

