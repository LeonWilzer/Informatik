package GraphGame;

import lib.TIO;

public class GraphClientTUI {

    // start game
    public static void setStart(boolean connected) {
        TIO.prt("Welcome to グラーフ・ゲーム");
        if (connected) {
            TIO.prt("Connected! ✅");
            TIO.prt("_.~\"~._.~\"~._ The game will begin now! _.~\"~._.~\"~._\n");
        } else {
            TIO.prt("Connection failed! ❌");
            TIO.prt("Make sure the server is running and the IP and port is correct, then restart the game.");
            TIO.prt("The game will now close.");
        }

        TIO.prt("Available colors are: \u001B[31m[R]ed\u001B[36m, \u001B[32m[G]reen\u001B[36m, \u001B[34m[B]lue\u001B[36m, \u001B[33m[Y]ellow");
    }

    // ask user for their turn
    // mode: 0/null=basic; 1=client-error; 2=server-error
    public static Turn getTurn(int mode) {
        switch (mode) {
            default:
                TIO.prt("It's your turn!");
                break;
            
            case 1:
                TIO.prt("⚠️ Error: your input was invalid!");
                TIO.prt("Make sure your input looks something like this \"A:G\" (this will color node 'A' in green)\n");
                break;
            
            case 2:
                TIO.prt("⚠️ Error: The server reported an issue with your turn. Please try again.");
                break;
        }
        
        String result[] = TIO.AskString("Please enter your turn [Node:Color]: ").split(":");
        if (result.length != 3 && result[1].equals(":")) {
            
            return getTurn(1);
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
    public static void setFinish(boolean userWon) {
        if (userWon) {
            TIO.prt("\n🎊 Jayy! ✨ You have won the game! 🎉🎉🎉");
        } else {
            TIO.prt("\nIt looks like your opponent had the edge over you. You'll surely win next time :)");     
        }

        TIO.prt("The game has concluded and will now exit.");
        TIO.prt("_.~\"~._.~\"~. 🎈 Thank you for palying. 🎈 _.~\"~._.~\"~.");
    }
}
