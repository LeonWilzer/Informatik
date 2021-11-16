package Main;

import lib.TIO;
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
<<<<<<< HEAD
        int choice = TIO.AskInt("""
            Please select a method for execution:
            0. Cancel
            1. Morse Decoder
            2. Morse Encoder
            3. Termtree
            4. Benutzerverwaltung
            5. Haus des Nikolaus
            6. Zeichenbaum
            """);

=======
        int choice = TIO.AskInt(
            "Please select a method for execution:\n" + 
            "0. Cancel\n" +
            "1. Morse Decoder\n" +
            "2. Morse Encoder\n" +
            "3. Termtree\n" + 
            "4. Benutzerverwaltung\n" +
            "5. Haus des Nikolaus\n" +
            "6. Zeichenbaum\n" +
            "7. Soziales Netzwerk"
            );
>>>>>>> a32f011f90057591e5b56a3f75f201e713f41b62
        switch(choice)
        {
            case 1:
                TIO.cls();
                TIO.prt(new Morse().decode(TIO.AskString("Please provide some morse code:")));
                break;
            case 2:
                TIO.cls();
                TIO.prt(new Morse().encodeString(TIO.AskString("Please provide some clear text:")));
                break;
            case 3:
                TIO.cls();
                Termtree.Demo();
                break;
            case 4:
                TIO.cls();
                Benutzerverwaltung.Demo();
                break;
            case 5:
                TIO.cls();
                ZeichenVerwaltung.Demo();
                break;
            case 6:
                TIO.cls();
                new Nikolaus();
                break;
            case 7:
                TIO.cls();
                new SozialesNetzwerk();
                break;
        }
    }
}
