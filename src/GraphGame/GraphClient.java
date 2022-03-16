package GraphGame;

import lib.Client;

public class GraphClient extends Client {
    GraphClientTUI tui;
    char proposedTurnIndex;
    char proposedTurnColour;

    public GraphClient(String pServerIP, int pServerPort) {
        super(pServerIP, pServerPort);
        tui = new GraphClientTUI();
    }

    private 

    @Override
    public void processMessage(String pMessage) {
        String words[] = pMessage.split("|");

        switch(words[0]) {
            // the used protocol is just a concept draft
            case "OK":
                turnCornfirm();
                break;

            case "YOUR_TURN":
                turnStart();
                break;
            
            case "SET_COLOUR":
                // example protocol: SET_COLOUR|B|RED
                break;
        }
    }

    private void turnPropose() {
        // TODO
    }

    private void turnCornfirm() {
        // TODO
    }

    private void turnStart() {
        // TODO
    }

    private void updateGraph(char index, char colour) {
        // TODO
    }
}
