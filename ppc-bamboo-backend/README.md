# bookservice

This is a "microservice" application intended to be part of a microservice architecture.

This application is configured for Service Discovery and Configuration with the PPC-Registry. On launch, it will refuse to start if it is not able to connect to the PPC-Registry at [http://localhost:8761](http://localhost:8761).

## Development

To start your application in the dev profile, simply run:

    ./mvnw

## Building for production

To optimize the bookservice application for production, run:

    ./mvnw -Pprod clean package

To ensure everything worked, run:

    java -jar target/*.war

## Testing

To launch your application's tests, run:

    ./mvnw clean test

## Using Docker to simplify development (optional)

You can use Docker to improve your development experience. A number of docker-compose configuration are available in the `src/main/docker` folder to launch required third party services.
For example, to start a mysql database in a docker container, run:

    docker-compose -f src/main/docker/mysql.yml up -d

To stop it and remove the container, run:

    docker-compose -f src/main/docker/mysql.yml down

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

    ./mvnw package -Pprod docker:build

Then run:

    docker-compose -f src/main/docker/app.yml up -d
