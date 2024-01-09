# SMS (Simple Messenger System)
## Description
###### This is a simple messenger system that allows users to send messages to each other.
## Features
- Register a new user
- Login as a user
- List all users
- Fetch unread messages
- Fetch unread messages or chat history of a specific user
- Send messages to other users
- Logout as a user
## Running the application (using Docker)
1. Clone this repository
2. Run `docker build -t sms .`
3. Run `docker run -p 8080:8080 sms`
4. The application will be running on `localhost:8080`
## APIs
### User
#### Register `POST /user`
````
curl -X POST -H "Content-Type: application/json" -d '{"username":"testuser","passcode":"testpass"}' http://localhost:8080/user
````
#### Login `POST /login`
````
curl -X POST -H "Content-Type: application/json" -d '{"username":"testuser","passcode":"testpass"}' http://localhost:8080/login
````
#### List all users `GET /user`
````
curl -X GET http://localhost:8080/user
````
#### Logout `POST /logout`
````
curl -X POST http://localhost:8080/logout
````
### Message
#### Fetch all unread messages `GET /user/{username}/message`
````
curl -X GET http://localhost:8080/user/testuser/message
````
#### Fetch unread messages or chat history of a specific user `GET /user/{username}/message?friend={friend}`
````
curl -X GET http://localhost:8080/user/testuser/message?friend=testuser2
````
#### Send a message to a specific user `POST /user/{username}/message`
````
curl -X POST -H "Content-Type: application/json" -d '{"text":"test message","to":"testuser2"}' http://localhost:8080/user/testuser/message
````