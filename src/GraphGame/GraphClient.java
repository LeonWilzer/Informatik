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
                // example protocol: SET_COLOUR|B|RED
                break;
        }
    }

    // an turn the server accepted
    private void turnAccept() {
        // TODO
    }

    // ask client user for his turn and send to server as proposal (accept or error will return)
    private void turnStart() {
        // TODO
    }

    private void updateGraph(char index, char colour) {
        // TODO
    }
}
