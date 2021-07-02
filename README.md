# Interval merge

This repository contains an exercise application that takes intervals as input and merges them, so it only returns
mutually exclusive intervals as output.

The application is build as Java command-line application.

## Build and Test

The project is setup using maven as dependency management and build tool and uses Java 11. You can build the project by
running ```mvn clean package```, which will also run the tests. To just run the tests without packaging the project, you
can run ```mvn clean test```

## Running the packaged .jar file

After you build the project with ```mvn clean package```, you can run the built .jar file. An example usage would be:

```shell
 java -jar ./target/interval-merge-1.0-SNAPSHOT.jar "[1,3]" "[4,5]" "[2,7]"
```
