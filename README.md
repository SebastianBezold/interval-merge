# Interval merge

This repository contains an exercise application that takes intervals as input and merges them, so it only returns
mutually exclusive intervals as output.

The application is build as Java command-line application.

## Assumptions

1) Input intervals are always input as lowerBound < upperBound ([1,3] and not [3,1])
2) Input intervals are passed as ordered list. Meaning the lowerBound of interval n is lower that the lowerBound of
   interval n+1.

## Build and Test

The project is setup using maven as dependency management and build tool and uses Java 11. You can build the project by
running ```mvn clean package```, which will also run the tests. To just run the tests without packaging the project, you
can run ```mvn clean test```
