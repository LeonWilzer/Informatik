package EvilCHat;

/**
 * Ein Messenger-Client
 * 
 * @author QUA-LiS NRW
 * @version 1.0
 */

import javax.swing.JOptionPane;

import lib.Client;
import lib.List;
public class MessengerClient extends Client {
	MessengerClient _this;

	private MessengerClientGUI messengerClientGUI;
	String eigenerName;
	boolean angemeldet;

	public MessengerClient(String pServerIP, int pServerPort, MessengerClientGUI pGUI) {
		super(pServerIP, pServerPort);

		_this = this;

		messengerClientGUI = pGUI;
		eigenerName = null;
		angemeldet = false;

		if (!isConnected()) {
			JOptionPane.showMessageDialog(null,
				"Fehler beim Herstellen der Verbindung!\nDas Programm wird jetzt beendet.");
			System.exit(1);
		}

	}

	@Override
	public void processMessage(String pMessage) {
		String[] pMessageZerteilt = pMessage.split(PROT.TRENNER);
		System.out.println("S0:" + pMessageZerteilt[0] + "!");

		if (!angemeldet) {
			switch (pMessageZerteilt[0]) {
				case PROT.SC_WK:
				String willkommensnachricht = "";
				for (int index = 1; index < pMessageZerteilt.length; index++) {
					willkommensnachricht += pMessageZerteilt[index] + " ";
				}
				JOptionPane.showMessageDialog(null, willkommensnachricht);
				break;

				case PROT.SC_AO:
				eigenerName = pMessageZerteilt[1];
				angemeldet = true;
				send(PROT.CS_GA);
				send(PROT.CS_NA);
				messengerClientGUI.initialisiereNachAnmeldung();
				break;
				//Hier fehlt noch die Fehlerbehandlung, falls versucht wurde sich mit einem bereits belegten Namen anzumelden    
			}
		} else {
			switch (pMessageZerteilt[0]) {
				case PROT.SC_TX:
				messengerClientGUI.ergaenzeNachrichten(pMessageZerteilt[1] + " schreibt:\n" + pMessageZerteilt[2]);
				break;

				case PROT.SC_ZU:
				  if (!pMessageZerteilt[1].equals(eigenerName)) {
					messengerClientGUI.ergaenzeTeilnehmerListe(pMessageZerteilt[1]);
				  }
				  break;
				  
				case PROT.SC_AB:
				  if (!pMessageZerteilt[1].equals(eigenerName)){
					messengerClientGUI.loescheNameAusTeilnehmerListe(pMessageZerteilt[1]);
					JOptionPane.showMessageDialog(null,"Teilnehmer "+ pMessageZerteilt[1] +" hat sich abgemeldet.");
				  } else {
					messengerClientGUI.leereNachLogout();
				  }; break;

				case PROT.SC_AT:
				  for (int index = 1; index < pMessageZerteilt.length; index++) {
					if (!pMessageZerteilt[index].equals(eigenerName)) {
						messengerClientGUI.ergaenzeTeilnehmerListe(pMessageZerteilt[index]);
					}
				  }
				  break;

				case PROT.SC_BY:
				  eigenerName = null;
				  angemeldet = false;
				  JOptionPane.showMessageDialog(null,"Verbindung durch den Messenger-Server geschlossen.\nDas Programm wird jetzt beendet.");
				  //System.exit(0);
				  messengerClientGUI.setVisible(false);
				  break;

				case PROT.SC_ER:
				  JOptionPane.showMessageDialog(null, pMessage.substring(1));
				  break;

				default:
				  JOptionPane.showMessageDialog(null, "Unzulässige Anweisung empfangen: '" + pMessage + "'");
				  break;
			}
		}
	}

	public void anmelden(String pName) {
	   send(PROT.CS_AN + PROT.TRENNER + pName); 
	}

	public void abmelden() {
		// Client sendet Abmeldung an Server
	}

	public void nachrichtSenden(String pEmpfaenger, String pNachricht) {
	  if (!pNachricht.equals("")) {
		// Client sendet TextNachricht zur Weiterleitung an Chatteilnehmer pEmpfaenger
		messengerClientGUI.ergaenzeNachrichten("Du schreibst an " + pEmpfaenger + "\n" + pNachricht);
	  }
	}
}
