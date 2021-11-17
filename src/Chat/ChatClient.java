package Chat;

import java.util.Scanner;

import lib.Client;
import lib.List;
import lib.TIO;

public class ChatClient extends Client{
	private List<String> messages;
	private String username;

	public static void main(String[] args) {
		new ChatClient(TIO.AskString("Server IP?"), TIO.AskInt("Server Port?"), TIO.AskString("Username?")).run();

	}

	public ChatClient(String pServerIP, int pServerPort, String pUsername)
	{
		super(pServerIP, pServerPort);
		messages = new List<String>();
		username = pUsername;
	}

	@Override
	public void processMessage(String pMessage) {
		addMessage(pMessage);
	}

	public void renderFrame()
	{
		TIO.cls();
		messages.toFirst();
		while(messages.hasAccess())
		{
			TIO.prt(messages.getContent());
			messages.next();
		}
		TIO.prt("Input:");
	}

	public void run()
	{
		while(true)
		{
			String message = new Scanner(System.in).nextLine();
			if(message.length()>0)
			{
				if(message.toCharArray()[0]=='/')
				{
				   addMessage(message);
				   execCommand(message);
				}
				else
				{
					if(isConnected())
					{
					   send(username+": "+message);
					}
					else
					{
						addMessage("No server connection!");
					}
				}
			}
		}
	}

	public void execCommand(String pCommand)
	{
		switch(pCommand)
		{
			case "/help":
				addMessage("""
Commands:
1. /help 
	Displays this help.
2. /exit
	Closes the client.
				""");
				break;
			case "/exit":
				close();
				System.exit(0);
				break;
			default:
				addMessage("Command " +pCommand+ " not found. Try: /help");
		}
	}

	public void addMessage(String pMessage)
	{
		messages.append(pMessage);
		renderFrame();
	}
}