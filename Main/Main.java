package Main;

import lib.TIO;
import Morse.Morse;

public class Main {

    public static void main(String[] args) {
        new Main().Menu();
    }

    public void Menu(){

        int choice = TIO.AskInt(
            "Please select a method for execution:\n" + 
            "0. Cancel\n" +
            "1. Morse Decoder"
            );
        switch(choice)
        {
            case 1:
                TIO.cls();
                TIO.prt(new Morse().translator(TIO.AskString("Please provide some morse code:")));
                break;
        }
    }
}