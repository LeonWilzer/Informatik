package Chat;

import lib.Server;
import lib.TIO;

public class ChatServer extends Server{

    String name;
    public ChatServer(int pPort, String pName) {
        super(pPort);
        name = pName;
        TIO.prt(name +" is online.");
    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        send(pClientIP, pClientPort, "Welcome to " + name +"!");
        sendToAll("A new user has joined " + name + " :)");
    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        sendToAll(pMessage);
    }

    @Override
    public void processClosingConnection(String pClientIP, int pClientPort) {
        sendToAll("A user has left " + name + " :(");
    }
    
}