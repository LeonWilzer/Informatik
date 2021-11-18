package EvilCHat;

import lib.List;
import lib.Triple;

public interface ClientInterface {
    public List<Triple<String, String, MessageType>> getMessages();

    public List<String> getUsers();

    public void addMessage(String pSender, String pMessage, MessageType pMessageType);

    public void close();

    public void login(String pName);

    public void logout();

    public void sendMessage(String pReceiver, String pMessage);

    public void sendPublicMessage(String pMessage);

    public String getNickname();
}
