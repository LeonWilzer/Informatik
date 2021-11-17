package EvilCHat;


/**
 * Ein Messenger-Server
 * 
 * @author QUA-LiS NRW
 * @version 1.0
 */

import javax.swing.JOptionPane;
import lib.List;
import lib.Server;

public class MessengerServer extends Server {
    private List<Teilnehmer> angemeldeteTeilnehmer;

    public MessengerServer(int pPort) {
        super(pPort);
        if (!isOpen())
            JOptionPane.showMessageDialog(null, "Fehler beim Starten des Servers auf Port " + pPort + "!");
        else {
            angemeldeteTeilnehmer = new List<Teilnehmer>();
            System.out.println("Server ist gestartet.");
        }

    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        send(pClientIP, pClientPort,
            PROT.SC_WK + PROT.TRENNER + "Willkommen auf dem Messenger-Server!");
    }

  @Override
  public synchronized void processMessage(String pClientIP, int pClientPort, String pMessage) {
    String[] pMessageZerteilt = pMessage.split(PROT.TRENNER);
    System.out.println("C0:" + pMessageZerteilt[0] + "!");
    if (!istTeilnehmerAngemeldet(pClientIP, pClientPort)) {
      if (pMessageZerteilt[0].equals(PROT.CS_AN)) {
        if (istNameVergeben(pMessageZerteilt[1]))
          send(pClientIP, pClientPort, PROT.SC_ER + PROT.TRENNER + "Der Name ist bereits vergeben!");
        else {
          meldeTeilnehmerAn(pClientIP, pClientPort, pMessageZerteilt[1]);
          send(pClientIP, pClientPort,PROT.SC_AO + PROT.TRENNER + pMessageZerteilt[1]);         
        }
      } else {
        System.out.println("FEHLER");
        send(pClientIP, pClientPort,PROT.SC_ER + PROT.TRENNER + "Sie sind nicht angemeldet!");
      }
    } else {
      switch (pMessageZerteilt[0]) {
        case PROT.CS_AN:
          send(pClientIP, pClientPort, PROT.SC_ER + PROT.TRENNER + "Sie sind bereits angemeldet!");
          break;
        case PROT.CS_GA:
          send(pClientIP, pClientPort, PROT.SC_AT + PROT.TRENNER + gibAlleAngemeldetenTeilnehmernamen());
          break;
        case PROT.CS_NA:
          sendToAll(PROT.SC_ZU + PROT.TRENNER + findeTeilnehmernameZuIPAdresseUndPort(pClientIP, pClientPort));
          break;
        case PROT.CS_AB:
          sendToAll(PROT.SC_AB + PROT.TRENNER + findeTeilnehmernameZuIPAdresseUndPort(pClientIP, pClientPort));
          meldeTeilnehmerAb(pClientIP, pClientPort);
          closeConnection(pClientIP, pClientPort);
          break;
        case PROT.CS_TX:
          String empfaengerIP = findeIPAdresseZuTeilnehmer(pMessageZerteilt[1]);
          int empfaengerPort = findePortZuTeilnehmer(pMessageZerteilt[1]);
          String senderName = findeTeilnehmernameZuIPAdresseUndPort(pClientIP, pClientPort);
          send(empfaengerIP, empfaengerPort,PROT.SC_TX + PROT.TRENNER 
                                            + senderName 
                                            + PROT.TRENNER + pMessageZerteilt[2]);
          break;
      }
    }
  }

    @Override
    public synchronized void processClosingConnection(String pClientIP, int pClientPort) {
        if (isConnectedTo(pClientIP, pClientPort))
            send(pClientIP, pClientPort, PROT.SC_BY);
    }

    private void meldeTeilnehmerAn(String pClientIP, int pClientPort, String pName) {
        Teilnehmer neuerTeilnehmer = new Teilnehmer(pClientIP, pClientPort, pName);
        neuerTeilnehmer.setzeAngemeldet(true);
        angemeldeteTeilnehmer.append(neuerTeilnehmer);
    }

    private void meldeTeilnehmerAb(String pClientIP, int pClientPort) {
        boolean gefunden = false;
        angemeldeteTeilnehmer.toFirst();
        while (angemeldeteTeilnehmer.hasAccess() && !gefunden) {
            if (angemeldeteTeilnehmer.getContent().gibIPAdresse().equals(pClientIP)
            && angemeldeteTeilnehmer.getContent().gibPort() == pClientPort) {
                angemeldeteTeilnehmer.getContent().setzeAngemeldet(false);
                angemeldeteTeilnehmer.getContent().setzeName(null);
                angemeldeteTeilnehmer.remove();
                gefunden = true;
            } else {
                angemeldeteTeilnehmer.next();
            }
        }
    }

    private boolean istTeilnehmerAngemeldet(String pClientIP, int pClientPort) {
        boolean gefunden = false;
        boolean angemeldet = false;
        angemeldeteTeilnehmer.toFirst();
        while  (angemeldeteTeilnehmer.hasAccess() && !gefunden) {
            if (angemeldeteTeilnehmer.getContent().gibIPAdresse().equals(pClientIP)
            && angemeldeteTeilnehmer.getContent().gibPort() == pClientPort) {
                angemeldet = angemeldeteTeilnehmer.getContent().istAngemeldet();
                gefunden = true;
            } else {
                angemeldeteTeilnehmer.next();
            }
        }
        return (angemeldet);
    }

    private boolean istNameVergeben(String pName) {
        boolean gefunden = false;
        angemeldeteTeilnehmer.toFirst();
        while (angemeldeteTeilnehmer.hasAccess() && !gefunden) {
            if (angemeldeteTeilnehmer.getContent().gibName().equals(pName)) {
                gefunden = true;
            } else {
                angemeldeteTeilnehmer.next();
            }
        }
        return (gefunden);
    }

    private String findeIPAdresseZuTeilnehmer(String pName) {
        String ipAdresse = null;
        boolean gefunden = false;
        angemeldeteTeilnehmer.toFirst();
        while (angemeldeteTeilnehmer.hasAccess() && !gefunden) {
            if (angemeldeteTeilnehmer.getContent().gibName().equals(pName)) {
                ipAdresse = angemeldeteTeilnehmer.getContent().gibIPAdresse();
                gefunden = true;
            } else {
                angemeldeteTeilnehmer.next();
            }
        }
        return (ipAdresse);
    }

    private int findePortZuTeilnehmer(String pName) {
        int port = -1;
        boolean gefunden = false;
        angemeldeteTeilnehmer.toFirst();
        while (angemeldeteTeilnehmer.hasAccess() && !gefunden) {
            if (angemeldeteTeilnehmer.getContent().gibName().equals(pName)) {
                port = angemeldeteTeilnehmer.getContent().gibPort();
                gefunden = true;
            } else {
                angemeldeteTeilnehmer.next();
            }
        }
        return (port);
    }

    private String findeTeilnehmernameZuIPAdresseUndPort(String pClientIP, int pClientPort) {
        String gefundenerTeilnehmername = null;
        boolean gefunden = false;
        angemeldeteTeilnehmer.toFirst();
        while (angemeldeteTeilnehmer.hasAccess() && !gefunden) {
            if (angemeldeteTeilnehmer.getContent().gibIPAdresse().equals(pClientIP)
            && angemeldeteTeilnehmer.getContent().gibPort() == pClientPort) {
                gefundenerTeilnehmername = angemeldeteTeilnehmer.getContent().gibName();
                gefunden = true;
            } else {
                angemeldeteTeilnehmer.next();
            }
        }
        return (gefundenerTeilnehmername);
    }

    private String gibAlleAngemeldetenTeilnehmernamen() {
        String teilnehmernamen = "";
        angemeldeteTeilnehmer.toFirst();
        while (angemeldeteTeilnehmer.hasAccess()) {
            teilnehmernamen = teilnehmernamen + angemeldeteTeilnehmer.getContent().gibName() + PROT.TRENNER;
            angemeldeteTeilnehmer.next();
        }
        if (!teilnehmernamen.equals("")) {
            return (teilnehmernamen.substring(0, teilnehmernamen.length() - 1));
        } else {
            return ("");
        }
    }

    
}
