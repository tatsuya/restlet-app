# restlet-app

A sample RESTful API using Restlet, learned from [restlet/restlet-tutorial](https://github.com/restlet/restlet-tutorial).

## Prerequisites

- Maven installed on your machine: [Maven documentation link](http://maven.apache.org/)
- Git installed on your machine

This example uses [Restlet Framework 2.3.1][restlet] (Java SE edition) and [MongoDB][mongodb] as persistent storage (optional).

## Installation

### Install Maven project

To install the Maven project:

- Go to the root directory of your local copy of this git repository.
- Execute `mvn clean install`

For further instruction about running a Maven project : [Building a project with Maven](http://maven.apache.org/run-maven/)

### Run this application

The main class is: `App.java`. By default the application is launched at `http://localhost:9000`, but you can change it in `App.java`.

You can interact with this application easily using a REST client like [POSTMAN][postman].

## Database

The persistent layer can be switched between MongoDB and on-memory storage. You can set which storage is used by updating `AppConfig.java`. By default on-memory persistence is used. It means whenever you restart the application all data is just disappeared.

## Description

This Web API contains 2 main resources:

- Trick: Represents a snowboard trick (name, description, etc.), identified by an auto-generated id.
- Movie: Represents a movie of snowboard trick, identified by an auto-generated id.

## Usage

These examples are made using the JSON format but you can use XML or YAML if you want.

### Create a trick

`POST http://localhost:9000/tricks/`

```json
{
  "name": "Ollie",
  "description": "A trick in which the snowboarder springs off the tail of the board and into the air."
}
```

The returned status is : `201 Created`. Note that the location of the created resource is written in the `Location` HTTP header.

### Retrieve all created tricks

`GET http://localhost:9000/v1/tricks/`

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

### Retrieve a trick by id

`GET http://localhost:9000/tricks/01234567-89ab-cdef-0123-456789abcdef`

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
