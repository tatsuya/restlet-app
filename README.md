# restlet-app

A sample RESTful API using Restlet, learned from [restlet/restlet-tutorial](https://github.com/restlet/restlet-tutorial).

## Prerequisites

- Maven installed on your machine: [Maven documentation link](http://maven.apache.org/)
- Git installed on your machine

This example uses [Restlet Framework 2.3.1][restlet] (Java SE edition) and [MongoDB][mongodb].

## Installation

### Install Maven project

To install the Maven project:

- Go to the root directory of your local copy of this git repository.
- Execute `mvn clean install`

For further instruction about running a Maven project : [Building a project with Maven](http://maven.apache.org/run-maven/)

### Run this application

The main class is: `App.java`

By default the application is launched at `http://localhost:9000`, but you can change it in `App.java`.

You can interact with this application easily using a REST client like [POSTMAN][postman].

## Database access

To interact with the database you can use `mongo` shell for MongoDB.

## Usage

These examples are made using the JSON format but you can use XML or YAML if you want.

### Create a resource

`POST http://localhost:9000/tricks/`

```json
{
  "name": "Ollie",
  "description": "A trick in which the snowboarder springs off the tail of the board and into the air."
}
```

The returned status is : `201 Created`. Note that the location of the created resource is written in the `Location` HTTP header.

### Retrieve all resources

```
http://localhost:9000/tricks
```

The trailing slash is optional : both `http://localhost:9000/tricks/` and `http://localhost:9000/tricks` will work. It should retrieve:

```json
[
  {
    "id": "01234567-89ab-cdef-0123-456789abcdef",
    "name": "Ollie",
    "description": "A trick in which the snowboarder springs off the tail of the board and into the air."
  }
]
```

### Retrieve a resource by id

```
http://localhost:9000/tricks/01234567-89ab-cdef-0123-456789abcdef
```

It should retrieve:

```json
{
  "id": "01234567-89ab-cdef-0123-456789abcdef",
  "name": "Ollie",
  "description": "A trick in which the snowboarder springs off the tail of the board and into the air."
}
```

[restlet]: http://restlet.com/download/current
[mongodb]: http://www.mongodb.org/
[postman]: http://www.getpostman.com/