# File-Transfer-System

## Overview
This project implements a simple **File Transfer System** using Java. It consists of two main components:
1. **FileTransferClient** - Responsible for sending files to the server.
2. **FileTransferServer** - Receives the files from the client and verifies their integrity using an MD5 checksum.

## Features
- **File Transmission:** The client sends a file to the server over a socket connection.
- **Checksum Verification:** Ensures file integrity by calculating an MD5 checksum before and after transmission.
- **Automatic File Reception:** The server listens continuously for incoming file transfers.


## How It Works
1. The **client** reads a file, computes its MD5 checksum, and transmits it to the server.
2. The **server** receives the file, computes the checksum, and compares it with the original checksum.
3. If the checksums match, the file is deemed successfully transferred.

## How to Run
### 1. Start the Server
Compile and run the server:

javac FileTransferServer.java

java FileTransferServer

### 2.Start the Client
Compile and run the client:

javac FileTransferClient.java

java FileTransferClient
