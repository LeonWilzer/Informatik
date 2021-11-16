package Main;

import lib.TIO;
import Chat.ChatClient;
import Chat.ChatServer;
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
        int choice = TIO.AskInt(
            "Please select a method for execution:\n" + 
            "0. Cancel\n" +
            "1. Morse Decoder\n" +
            "2. Morse Encoder\n" +
            "3. Termtree\n" + 
            "4. Benutzerverwaltung\n" +
            "5. Haus des Nikolaus\n" +
            "6. Zeichenbaum\n" +
            "7. Soziales Netzwerk\n" +
            "8. Chat Client\n" +
            "9. Chat Server"
            );
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
        }
    }
}
