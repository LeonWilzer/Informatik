package Main;

import lib.TIO;
import Morse.Morse;
import Termtree.Termtree;

public class Main {

    public static void main(String[] args) {
        new Main().Menu();
    }

    public void Menu(){

        int choice = TIO.AskInt(
            "Please select a method for execution:\n" + 
            "0. Cancel\n" +
            "1. Morse Decoder\n" +
            "2. Morse Encoder\n" +
            "3. Termtree"
            );
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
                new Termtree();
                break;
        }
    }
}