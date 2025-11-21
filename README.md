# Chat Messenger with Log Facility

A simple Java based Client-Server Chat Messenger that allows real-time communication between the client and server devices.

## Project Structure

1. **Server.java** → Handles incoming client connections and chat messaging  
2. **Client.java** → Connects to the server, sends and receives messages  
3. **README.md** → Project description , steps to run and Documentation  

## Features

1. Real-time chat between Server and Client
2. Platform-independent  
3. Easy to run from terminal or any IDE
4. Logging of all messages, connections and disconnections. 
5. Supports multiple clients (if implemented)   

## Getting Started

### Prerequisites
1. Java JDK 8 or later installed  
2. Terminal or preferred IDE (IntelliJ, VS Code, etc.) / Notepad / Editor
   (Editing the host if connection to be done between two different devices {optional step})

### Connection

#### For using the same deivce as Server and Client as well.

   1. First run **Server.java**
   2. Then run **Client.java** on another terminal

#### For using two different devices (using the actual concept of networking between two devices)

1. Both the devices should be connected to the same wifi OR by connecting LAN cable to both the devices
2. Get the IP address of network from device which will run the Server.java (Windows : CMD : ipconfig : select the IPV4 address)
3. Paste this IP address to the 110th line of Client.java in place of host ; ie. "localhost"
4. Run **Server.java** first
5. Then run the **Client.java** from the other device
