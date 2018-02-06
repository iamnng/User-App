# User-App

Start the Application:
----------------------
- *Eclipse*: Go To UserAppApplication.class -> Run As -> Spring Boot App
- *Command Line*: mvn spring-boot:run

Build the Project:
------------------
mvn clean package

Available Endpoints:
--------------------
1) Create a User:
-----------------
curl -X POST \
  http://192.168.56.101:8080/v1/user \
  -H 'content-type: application/json' \
  -d '{
	"fName": "John",
	"lName": "Trott",
	"salary": 15000
}'

2) Get All Users:
-----------------
curl -X GET http://192.168.56.101:8080/v1/users

Database Details:
-----------------
The application works with Postgres database with following values:
- Port: 5432
- Database: pg-test
- Username: postgres
- Password: admin

How to run the application in a Docker Container and refer to Postgresql Docker Container
------------------------------------------------------------------------------------------
Step 1) Pull the Postgresl Docker Image
$ sudo docker pull postgres

Step 2) Start the Postgres Container
$ sudo docker run --name postgres-standalone -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres

This will create a container with:
- Container Name = postgres-standalone
- Username = postgres
- Password = admin

Step 3) Update the application.properties. Refer to postgres-standalone (container name) instead of localhost
spring.datasource.url= jdbc:postgresql://postgres-standalone:5432/postgres

Step 4) Build the application JAR. It will be available in /target directory
$ mvn clean package -Dmaven.test.skip=true

We have to skip the tests because the postgres-standalone container has not been linked with our application as of yet.

Step 5) Using the Dockerfile, build a Docker image for our application
$ sudo docker build . -t users-application

Step 6) Start the container
$ sudo docker run -p 8080:8080 --name users-application --link postgres-standalone:postgres -d users-application

This command will also link postgres-standalone container with the container in which our application is running.

Step 7) Hit the endpoints as usual

Another AWESOME way to start the applications by running the containers and linking them
--------------------------------------------------------------------------------------
Step 1) Clean up the running containers

Step 2) Make sure that the application docker image is ready (Step 5 above)

Step 3) Run the command: $ sudo docker-compose up

AWS ECS
-------
The repository also contains Task Definition to run these 2 docker containers on ECS.

Upload the task definition to ECS, create a service by referring to this task definition and the 2 containers will be started by ECS on EC2 instances.