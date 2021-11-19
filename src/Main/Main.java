package Main;

import lib.TIO;
import Chat.ChatClient;
import Chat.ChatServer;
import EvilCHat.MessengerClientTUI;
import EvilCHat.MessengerServer;
import Morse.Morse;
import Nikolaus.Nikolaus;
import SozialesNetzwerk.SozialesNetzwerk;
import SymMan.ZeichenVerwaltung;
import Termtree.Termtree;
import UserAdministration.Benutzerverwaltung;

public class Main {

    public static void main(String[] args) {
        new Main().Menu();
    }

    public void Menu()
    {
        int choice = TIO.AskInt("""
            Please select a method for execution:
            0. Cancel
            1. Morse Decoder
            2. Morse Encoder
            3. Termtree
            4. Benutzerverwaltung
            5. Haus des Nikolaus
            6. Zeichenbaum
            7. Soziales Netzwerk
            8. Chat Client
            9. Chat Server
            10. EvilChat Client
            11. EvilChat Server
            """);
        TIO.cls();
        switch(choice)
        {
            case 1:
                TIO.prt(new Morse().decode(TIO.AskString("Please provide some morse code:")));
                break;
            case 2:
                TIO.prt(new Morse().encodeString(TIO.AskString("Please provide some clear text:")));
                break;
            case 3:
                Termtree.Demo();
                break;
            case 4:
                Benutzerverwaltung.Demo();
                break;
            case 5:
                ZeichenVerwaltung.Demo();
                break;
            case 6:
                new Nikolaus();
                break;
            case 7:
                new SozialesNetzwerk();
                break;
            case 8:
                new ChatClient(TIO.AskString("Server IP?"), TIO.AskInt("Server Port?"), TIO.AskString("Username?")).run();
                break;
            case 9:
                new ChatServer(TIO.AskInt("Port?"), TIO.AskString("Server Name?"));
                break;
            case 10:
                new MessengerClientTUI().run();
                break;
            case 11:
                new MessengerServer(TIO.AskInt("Port?"));
                break;
            default:
                TIO.prt("Please select a number ranging from 1 to 11");
                break;
        }
    }
}
