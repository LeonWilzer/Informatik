package GraphGame;

import lib.TIO;

public class GraphClientTUI {

    // start game
    public static void setStart(boolean connected) {
        TIO.prt("Welcome to グラーフ・ゲーム");
        if (connected) {
            TIO.prt("Connected! ✅");
            TIO.prt("_.~"~._.~"~._ The game will begin now! _.~"~._.~"~._\n");
        } else {
            TIO.prt("Connection failed! ❌");
            TIO.prt("Make sure the server is running and the IP and port is correct, then restart the game.");
            TIO.prt("The game will now close.");
        }

        TIO.prt("Available colors are: \u001B[31m[R]ed\u001B[36m, \u001B[32m[G]reen\u001B[36m, \u001B[34m[B]lue\u001B[36m, \u001B[33m[Y]ellow");
    }

    // ask user for their turn
    public static Turn getTurn() {
        TIO.prt("It's your turn!");
        String result[] = TIO.AskString("Please enter your turn [Node:Color]: ").split(":");
        if (result.length != 3 && result[1].equals(":")) {
            TIO.prt("Error: your input was invalid!");
            TIO.prt("Make sure your input looks something like this \"A:G\" (this will color node 'A' in green)");
            return getTurn();
        } else {
            TIO.prt("Submitting input..");
        }

        return new Turn(result[0].toCharArray()[0], ColourHelper.getColourFromChar(result[2].toCharArray()[0]));
    }

    // tell user about a confirmed turn either by the player or the opponent
    public static void setTurn(Turn pTurn) {
        if (pTurn.isSelf()) {
            TIO.prt("Your turn was accepted!\n");
        } else {
            TIO.prt("\nYour opponent made his turn!");
            TIO.prt("The Node '" + pTurn.getNode() + "' is now " + pTurn.getColour().toString());
        }
    }

    // tell user about who won the game
    public static void setFinish() {
        // TODO
    }
}
