# User-App

Start the Application:
----------------------
1) *Eclipse*: Go To UserAppApplication.class -> Run As -> Spring Boot App
2) *Command Line*: mvn spring-boot:run

Build the Project:
------------------
mvn clean package

Available Endpoints:
--------------------
1) Create a User:

curl -X POST \
  http://192.168.56.101:8080/v1/user \
  -H 'content-type: application/json' \
  -d '{
	"fName": "John",
	"lName": "Trott",
	"salary": 15000
}'

2) Get All Users:

curl -X GET http://192.168.56.101:8080/v1/users
