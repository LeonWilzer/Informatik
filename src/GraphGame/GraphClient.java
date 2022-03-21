package GraphGame;

import lib.Client;

public class GraphClient extends Client {
    char proposedTurnIndex;
    char proposedTurnColour;

    public GraphClient(String pServerIP, int pServerPort) {
        super(pServerIP, pServerPort);
        GraphClientTUI.setStart(isConnected());
    }

    @Override
    public void processMessage(String pMessage) {
        String words[] = pMessage.split("|");

        switch (words[0]) {
            // the used protocol is just a concept draft
            case "OK":
                turnAccept();
                break;

            case "YOUR_TURN":
                turnStart();
                break;

            case "SET_COLOUR":
                // example protocol: SET_COLOUR|B|R (set node 'B' to red)
                turnAccept(words);
                break;
        }
    }

    // turn the server accepted
    private void turnAccept() {
        GraphClientTUI.setTurn(new Turn('_', null, false)); // only the true part is relevant when confirming a turn in the TUI
    }

    // an turn the server accepted
    private void turnAccept(String words[]) {
        GraphClientTUI.setTurn(
                new Turn(words[1].toCharArray()[0], ColourHelper.getColourFromChar(words[2].toCharArray()[0]), true));                                                                                                                   // TUI
    }

    // ask client user for his turn and send to server as proposal (accept or error will return)
    private void turnStart() {
        Turn turn = GraphClientTUI.getTurn(0);
        super.send("SET_COLOUR|" + turn.getNode() + "|" + turn.getColour().toString());
    }

    private void updateGraph(char index, char colour) {
        // TODO
    }
}
