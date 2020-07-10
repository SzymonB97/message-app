Spring application which communicates with Cassandra database by REST API. Application and database works on Docker containers. Docker-compose file creates app image if it not exists, runs Cassandra container and runs application container which is waiting for the Cassandra when it will be ready. After containers start, application creates messages_keyspace (if not exists) and 'messages' table. After that we can do some actions (using REST API):
 - add message
 - get messages with given email
 - "send" (delete from database) messages with given magic_number
 - shutdown application

Application should automatically remove messages older than 2 min. After shutdown application should drop messages_keyspace from Cassandra.

Example using cURL:

1. Add message

    curl -X POST "http://localhost:8080/api/message" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"email\": \"example.example@example.com\", \"title\": \"Test\", \"content\": \"test text\", \"magic_number\": 1}"
    
2. Send messages

    curl -X POST "http://localhost:8080/api/send" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"magic_number\": 1}"
    
3. Get messages with given email

    curl -X GET "http://localhost:8080/api/messages/example.example@example.com"
    
4. Shutdown application using REST API

    curl -X POST "http://localhost:8080/api/shutdown"
