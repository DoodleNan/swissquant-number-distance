# SwissQuant - Numbers Distance 

## Problem
You are given a binary file called “points” containing 10 million points (x,y). Each point is encoded as two successive 16 bit signed integer values.
Implement and ship us a Java program that computes, of all the points in the file 
1) The 10 closest points to (-200,300)
2) The 20 furthest points from (1000, 25)

## What is it
swissquant-number-distance is a spring application to return 
num of points(configurable) closest/furthest points to a given origin. It is built with maven.

## How to use
### Prerequisites
- JDK 8 & JRE installed
- Maven installed

All dependencies/executables are packaged in the application. You should be able to build and run it from any unix-like systems.

### Start the application
To reduce starting time we don't run unit tests on start the server. It takes around 10s to start the application. 
We will cover unit test in section below.
1. From command line:
Navigate to root location of application where we define pom.xml, and execute: 
```
 mvn spring-boot:run
```
2. From IDE:
Simple click "run" button, or navigate to `SwissQuantNumbersDistanceApplication.java` class, right click it and choose 
`run SwissQuantNumbersDistanceApplication`.

### Sending http request to application
Once the application is running, i.e, you see info from console
` Started SwissQuantNumbersDistanceApplication in 14.444 seconds (JVM running for xxxxx)`
the application is ready to accept http request and serve the queries.
There are two APIs: 1). findFurthestPointsFrom 2). findClosestPointsTo.

### findFurthestPointsFrom 
It returns numOfPoints(configurable) furthest points to a given origin. Example curl command:
```
curl --location --request POST 'http://localhost:8080/furthestPointsFrom?numOfPoints=20' \
--header 'Content-Type: application/json' \
--data-raw '{"x": 1000,"y": 25}' | json_pp
```
Example output:
```
% Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   506    0   476  100    30     70      4  0:00:07  0:00:06  0:00:01   104
[
   {
      "x" : -32736,
      "y" : -32676
   },
   {
      "x" : -32723,
      "y" : -32691
   },
   {
      "x" : -32763,
      "y" : 32705
   },
   {
      "x" : -32763,
      "y" : 32703
   },
   {
      "x" : -32710,
      "y" : -32707
   },
   {
      "x" : -32695,
      "y" : -32759
   },
   {
      "x" : -32723,
      "y" : 32749
   },
   {
      "x" : -32686,
      "y" : -32752
   },
   {
      "x" : -32747,
      "y" : -32690
   },
   {
      "x" : -32726,
      "y" : -32693
   },
   {
      "x" : -32714,
      "y" : 32757
   },
   {
      "x" : -32752,
      "y" : -32755
   },
   {
      "x" : -32748,
      "y" : -32721
   },
   {
      "x" : -32710,
      "y" : -32723
   },
   {
      "x" : -32751,
      "y" : 32743
   },
   {
      "x" : -32745,
      "y" : -32736
   },
   {
      "x" : -32760,
      "y" : -32746
   },
   {
      "x" : -32729,
      "y" : -32729
   },
   {
      "x" : -32730,
      "y" : -32740
   },
   {
      "x" : -32690,
      "y" : -32753
   }
]
```
#### findClosestPointsTo - 
It returns numOfPoints(configurable) closet points to a given origin. 

Example curl command:
```
curl --location --request POST 'http://localhost:8080/closestPointsTo?numOfPoints=10' \
--header 'Content-Type: application/json' \
--data-raw '{
    "x": 1000,
    "y": 25
}' | json_pp
```

Example output:
```
% Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   202    0   172  100    30     70     12  0:00:02  0:00:02 --:--:--    82
[
   {
      "x" : 974,
      "y" : 44
   },
   {
      "x" : 997,
      "y" : 57
   },
   {
      "x" : 991,
      "y" : 55
   },
   {
      "x" : 969,
      "y" : 32
   },
   {
      "x" : 1022,
      "y" : 48
   },
   {
      "x" : 985,
      "y" : 31
   },
   {
      "x" : 984,
      "y" : 36
   },
   {
      "x" : 983,
      "y" : 9
   },
   {
      "x" : 1026,
      "y" : 27
   },
   {
      "x" : 975,
      "y" : 12
   }
]
```
### Terminate the application
You can terminate the application by keyboard input or IDE stop button. 

## Design ideas
1. Instead of use fixed result set size specified in assignment(10 closest points and 20 furthest points), I implement `numOfPoints` as configurable path parameter
to enable dynamic result set size.
2. Instead of use fixed origin point specified in assignment((-200,300) for closest and (1000, 25) for furthest), I implemented 'Point' as request body
to enable dynamic origin feature.
3. The assignment asks to read input from a given file. I can imagine other use cases when we need to read from cloud/database/Redis for different reasons,
e.g.the data set size is too large to load in memory, or we want to introduce database/cache into the system etc. So I designed InputFetcher interface with FileInputFetcher
as one of its implementation to fulfill the current requirement and reserve the extensibility in the future
4. To decrease the API response time I used a ConcurrentHashMap to store {point - timestamp} pair on application starts.
It will be served as an in memory cache for all upcoming request, so that we don't need to read from file for each request.
The only runtime is the calculation of distance and comparing them.
5. I am not an expert of points distance calculation so I use the brute force algorithm to calculate
each point's distance to the given origin, and use fixed size(numOfPoints parameter from request) min/max heap to store the points.
With ConcurrentHashMap serving as in memory cache even with the brute force algorithm the run time for
each request is just around 1s(not counting application starts time).
6. I reserve the space for smarter algorithms to calculate distance via strategy pattern, in the future
once I am smart enough I will fill in the gap. =.=

## Unit test

### Run all unit test
```
mvn test
```

### Run a single/multiple unit test
```
mvn -Dtest=path1/to/test, path2/to/test test
```
### Run a single test method from a test class
```
mvn -Dtest=path1/to/test#methodname test
```
### Jacoco Integration
You can find Code coverage report under {projectRoot}/target/site/jacoco folder.

## Improvement Ideas
1. If the origin points and result set size doesn't change frequently, we can cache the results to a Map, key is
origin and value is result set
2. Some algorithm improvement ideas.
3. Use multi-threads to read the input file and write to ConcurrentHashMap, as our
ConcurrentHashMap is thread safe.
4. Implement a nice UI.






    