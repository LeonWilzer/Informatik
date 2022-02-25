package EvilCHat;

import java.util.Scanner;

import lib.List;
import lib.TIO;
import lib.Triple;

public class MessengerClientTUI implements UI {

	private ClientInterface curCl;
	private List<ClientInterface> clients;
	private Scanner sc;
	private boolean autoFocus;
	/*
	public static void main(String[] args) {
		MessengerClientTUI ui = new MessengerClientTUI();
		ui.run();
	}
	*/

	public MessengerClientTUI() {
		clients = new List<ClientInterface>();
		sc = new Scanner(System.in);
		curCl = new internalClient(this);
		autoFocus = false;
		TIO.cls();
	}

	public void run() {
		while (true) {
			String input = sc.nextLine();
			if (input.startsWith("/")) {
				execCommand(input);
			} else {
				curCl.sendPublicMessage(input);
			}
		}
	}

	public void execCommand(String pCommand) {
		String[] com = pCommand.trim().split("\\s");
		try {
			switch (com[0]) {
			case "/help":
				curCl.addMessage("", """
						Commands:
						1. /help
							Displays this help.
						2. /exit
							Closes the client.
						3. /login <username>
							Logs you into the current server.
						4. /logout
							Logs you out of the current server.
						5. /nextServer
						   /ns
						   Switch focus to the next server in the list.
						6. /toggleAutoFocus
							Toggles whether or not to focus automatically when receiving a message.
						7. /whisper <recipient> [message]
						   /w
							Send a private message to the specified recipient.
										""", MessageType.INFO);
				break;
			case "/exit":
				clients.toFirst();
				while(clients.hasAccess())
				{
					clients.getContent().logout();
					clients.next();
				}
				System.exit(0);
				break;
			case "/connect":
				curCl = new MessengerClient(com[1], Integer.parseInt(com[2]), this);
				clients.append(curCl);
				break;
			case "/login":
				curCl.login(com[1]);
				break;
			}					break;
					}
					clients.next();
				}
			case "/nextServer":
			case "/ns":
				clients.next();
				if (!clients.hasAccess())
					clients.toFirst();
				curCl = clients.getContent();
				renderFrame(curCl);
				break;
			case "/toggleAutoFocus":
				autoFocus = !autoFocus;
				curCl.addMessage("", "Set automatic focus to: " + autoFocus, MessageType.INFO);
				break;
			case "/whisper":
			case "/w":
				String message = "";
				for (int i = 2; i < com.length; i++)
					message += com[i] + " ";
				curCl.sendMessage(com[1], message.trim());
				break;
			default:
				curCl.addMessage("", "Command " + pCommand + " not found. Try: /help", MessageType.INFO);
			}
		} catch (Exception e) {
			curCl.addMessage("", e.toString(), MessageType.ERROR);
		}
	}

	@Override
	public void renderFrame(ClientInterface pClient) {
		if (!autoFocus || curCl != pClient)
			return;

		TIO.cls();
		List<Triple<String, String, MessageType>> messages = pClient.getMessages();
		messages.toFirst();
		while (messages.hasAccess()) {
			String sender = messages.getContent().F;
			String message = messages.getContent().S;
			switch (messages.getContent().T) {
			case ERROR:
				System.out.println(TIO.COLOR_ERROR + sender + message + TIO.ANSI_RESET);
				break;
			case INFO:
				System.out.println(TIO.ANSI_CYAN + sender + message + TIO.ANSI_RESET);
				break;
			case OTHER_MESSAGE:
				System.out.println(TIO.ANSI_GREEN + sender + ": " + message + TIO.ANSI_RESET);
				break;
			case OWN_MESSAGE:
				System.out.println(TIO.ANSI_BLUE + sender + ": " + message + TIO.ANSI_RESET);
				break;
			case WARNING:
				System.out.println(TIO.COLOR_WARNING + sender + message + TIO.ANSI_RESET);
				break;
			case WHISPER:
				System.out.println(TIO.ANSI_PURPLE + sender + " whispers: " + message + TIO.ANSI_RESET);
				break;
			default:
				System.out.println(TIO.ANSI_RESET + sender + ": " + message + TIO.ANSI_RESET);
			}
			messages.next();
		}
	}

	private class internalClient implements ClientInterface {

		private List<Triple<String, String, MessageType>> messages;
		private List<String> users;

		private UI ui;

		private internalClient(UI pUi) {
			messages = new List<Triple<String, String, MessageType>>();
			ui = pUi;
		}

		@Override
		public List<Triple<String, String, MessageType>> getMessages() {
			return messages;
		}

		@Override
		public List<String> getUsers() {
			return users;
		}

		@Override
		public void addMessage(String pSender, String pMessage, MessageType pMessageType) {
			messages.append(new Triple<String, String, MessageType>(pSender, pMessage, pMessageType));
			ui.renderFrame(this);
		}

		@Override
		public void login(String pName) {
			addMessage("", "This is only an internal server. Message /help for guidance.", MessageType.INFO);
		}

		@Override
		public void sendMessage(String pReceiver, String pMessage) {
			addMessage("", "This is only an internal server. Message /help for guidance.", MessageType.INFO);
		}

		@Override
		public void close() {
		}

		@Override
		public void logout() {
		}

		@Override
		public String getNickname() {
			return null;
		}

		@Override
		public void sendPublicMessage(String pMessage) {
			addMessage("", "This is only an internal server. Message /help for guidance.", MessageType.INFO);
		}
	}
}