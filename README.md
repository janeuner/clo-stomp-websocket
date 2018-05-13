# clo-stomp-websocket

This project explores a WebSocket implementation of the Clue board game with swing and HTML clients, which all connect to a web-based session server.

## Project Progress
1. [x] Swing Client Prototype interface.
2. [x] ChatHistory implementation.
3. [x] Addition of the GameSession class, implementation of clientjoin/ gamesetup UCs.
4. [x] Move chat into game GameSession context.
5. [x] Implementation of EndGameMessage.
6. [x] Implement JUnit on message classes and GameEntityID
7. [x] Initial implementation of GameSession state machine.
8. [ ] Addition of game board move messaging.

# Project Organization and Build Hints

All commands are run from the project root directory

## server

clo-stomp-websocket server is derived from and heavily influenced by the [STOMP WebSocket GS guide for the Spring Framework](https://spring.io/guides/gs/messaging-stomp-websocket/).

### Building the server

Build the server with: **gradle server:bootJar**

Launch the server with: **java -jar server/build/libs/server.jar**

### Debugging the server

To build and run the server with with debugging enabled, execute: **gradle server:bootRun --debug-jvm**

The server will launch and print out a message that identifies the debugging port.  You may then point your favorite IDE at that port to connect a debugger.

### HTML Client

With the server running, you can connect your browser to [localhost port 8080](http://localhost:8080/) to interact with the basic mechanisms for joining a session and participating in chat.

## client 

The swing client is a multi-window application which steps through the gameplay user flow for CLO.

### Building the server

Build the server with: **gradle client:jar**

Launch the server with: **java -jar client/build/libs/clo-swing-client-0.0.1.jar**

### Debugging the server

To build and run the swing client with with debugging enabled, execute: **gradle client:run --debug-jvm**

The client will launch and print out a message that identifies the debugging port.  You may then point your favorite IDE at that port to connect a debugger.
