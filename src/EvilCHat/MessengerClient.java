package EvilCHat;

/**
 * Ein Messenger-Client
 * 
 * @author QUA-LiS NRW
 * @version 1.0
 */

import lib.Client;
import lib.List;
import lib.TIO;
import lib.Triple;

public class MessengerClient extends Client implements ClientInterface {

	MessengerClient _this;

	// private MessengerClientGUI messengerClientGUI;
	String eigenerName;
	boolean angemeldet;

	private List<Triple<String, String, MessageType>> nachrichtenList;
	private List<String> users;
	private UI ui;

	public MessengerClient(String pServerIP, int pServerPort, UI pUi) {
		super(pServerIP, pServerPort);

		_this = this;

		// messengerClientGUI = pGUI;
		eigenerName = null;
		angemeldet = false;

		nachrichtenList = new List<Triple<String, String, MessageType>>();
		users = new List<String>();
		ui = pUi;

		if (!isConnected()) {
			// JOptionPane.showMessageDialog(null,
			// "Fehler beim Herstellen der Verbindung!\nDas Programm wird jetzt beendet.");
			System.out.println(
					TIO.COLOR_ERROR + "Fehler beim Herstellen der Verbindung!\nDas Programm wird jetzt beendet.");
			System.exit(1);
		}

	}

	@Override
	public void processMessage(String pMessage) {
		String[] pMessageZerteilt = pMessage.split(PROT.TRENNER);
		// System.out.println("S0:" + pMessageZerteilt[0] + "!");

		if (!angemeldet) {
			switch (pMessageZerteilt[0]) {
			case PROT.SC_WK:
				String willkommensnachricht = "";
				for (int index = 1; index < pMessageZerteilt.length; index++) {
					willkommensnachricht += pMessageZerteilt[index] + " ";
				}
				// JOptionPane.showMessageDialog(null, willkommensnachricht);
				addMessage("", willkommensnachricht, MessageType.INFO);
				break;

			case PROT.SC_AO:
				eigenerName = pMessageZerteilt[1];
				angemeldet = true;
				send(PROT.CS_GA);
				send(PROT.CS_NA);
				addMessage("", "Logged in.", MessageType.INFO);
				// messengerClientGUI.initialisiereNachAnmeldung();
				break;
			// Hier fehlt noch die Fehlerbehandlung, falls versucht wurde sich mit einem
			// bereits belegten Namen anzumelden

			case PROT.SC_ER:
				addMessage("", pMessageZerteilt[1], MessageType.ERROR);
				break;
			}
		} else {
			switch (pMessageZerteilt[0]) {
			case PROT.SC_TX:
				// messengerClientGUI.ergaenzeNachrichten(pMessageZerteilt[1] + " schreibt:\n"
				// +pMessageZerteilt[2]);
				addMessage(pMessageZerteilt[1], pMessageZerteilt[2], MessageType.OTHER_MESSAGE);
				break;

			case PROT.SC_ZU:
				if (!pMessageZerteilt[1].equals(eigenerName)) {
					// messengerClientGUI.ergaenzeTeilnehmerListe(pMessageZerteilt[1]);
					addUser(pMessageZerteilt[1]);
				}
				break;

			case PROT.SC_AB:
				if (!pMessageZerteilt[1].equals(eigenerName)) {
					// messengerClientGUI.loescheNameAusTeilnehmerListe(pMessageZerteilt[1]);
					removeUser(pMessageZerteilt[1]);
					// JOptionPane.showMessageDialog(null, "Teilnehmer " + pMessageZerteilt[1] + "
					// hat sich abgemeldet.");
					addMessage(pMessageZerteilt[1], " hat sich abgemeldet.", MessageType.INFO);
				} else {
					// messengerClientGUI.leereNachLogout();
					clear();
				}
				;
				break;

			case PROT.SC_AT:
				for (int index = 1; index < pMessageZerteilt.length; index++) {
					if (!pMessageZerteilt[index].equals(eigenerName)) {
						// messengerClientGUI.ergaenzeTeilnehmerListe(pMessageZerteilt[index]);
						addUser(pMessageZerteilt[index]);
					}
				}
				break;

			case PROT.SC_BY:
				eigenerName = null;
				angemeldet = false;
				// JOptionPane.showMessageDialog(null,
				// "Verbindung durch den Messenger-Server geschlossen.\nDas Programm wird jetzt
				// beendet.");
				// System.exit(0);
				close();
				// messengerClientGUI.setVisible(false);
				break;

			case PROT.SC_ER:
				// JOptionPane.showMessageDialog(null, pMessage.substring(1));
				addMessage("", pMessage.substring(1), MessageType.ERROR);
				break;

			default:
				// JOptionPane.showMessageDialog(null, "Unzulässige Anweisung empfangen: '" +
				// pMessage + "'");
				addMessage("", "Unzulaessige Anweisung empfangen:", MessageType.WARNING);
				break;
			}
		}
	}

	public void login(String pName) {
		if (!angemeldet)
			send(PROT.CS_AN + PROT.TRENNER + pName);
		else
			addMessage("", "You are alread logged in on this server.", MessageType.INFO);
	}

	@Override
	public void logout() {
		// Client sendet Abmeldung an Server
		send(PROT.CS_AB+PROT.TRENNER);
	}

	@Override
	public void sendMessage(String pReceiver, String pMessage) {
		if (!pMessage.equals("")) {
			// Client sendet TextNachricht zur Weiterleitung an Chatteilnehmer pEmpfaenger
			// messengerClientGUI.ergaenzeNachrichten("Du schreibst an " + pEmpfaenger +
			// "\n" + pNachricht);
			send(PROT.CS_TX + PROT.TRENNER + pReceiver +PROT.TRENNER+ pMessage);
			addMessage(eigenerName, pMessage, MessageType.OWN_MESSAGE);
		}
	}

	public void sendPublicMessage(String pMessage) {
		if (!pMessage.equals("")) {
			if (users.isEmpty()) {
				addMessage("", "You are all alone :(", MessageType.INFO);
			} else {
				users.toFirst();
				while (users.hasAccess()) {
					send(PROT.CS_TX + PROT.TRENNER + users.getContent() + PROT.TRENNER + pMessage);
					users.next();
				}
				addMessage(eigenerName, pMessage, MessageType.OWN_MESSAGE);
			}
		}
	}

	@Override
	public void addMessage(String pSender, String pNachricht, MessageType pMessageType) {
		nachrichtenList.append(new Triple<String, String, MessageType>(pSender, pNachricht, pMessageType));
		ui.renderFrame(this);
	}

	@Override
	public List<Triple<String, String, MessageType>> getMessages() {
		return nachrichtenList;
	}

	private void addUser(String pString) {
		users.toFirst();
		boolean unique = true;
		while (users.hasAccess()) {
			if (users.getContent() == pString) {
				unique = false;
				break;
			}
			users.next();
		}

		if (unique)
			users.append(pString);
	}

	private void removeUser(String pString) {
		users.toFirst();
		while (users.hasAccess()) {
			if (pString == users.getContent()) {
				users.remove();
				break;
			}
		}
	}

	@Override
	public List<String> getUsers() {
		return users;
	}

	private void clear() {
		users = new List<String>();
		nachrichtenList = new List<Triple<String, String, MessageType>>();
		ui.renderFrame(this);
	}

	@Override
	public String getNickname() {
		return eigenerName;
	}
}