package GraphGame;

import lib.Client;
import lib.Edge;
import lib.Graph;
import lib.Vertex;

public class GraphClient extends Client {
    char proposedTurnIndex;
    char proposedTurnColour;
    Graph graph;
    Colour graphColours[];

    public GraphClient(String pServerIP, int pServerPort) {
        super(pServerIP, pServerPort);
        GraphClientTUI.setStart(isConnected());
        graph = new Graph();
    }

    @Override
    public void processMessage(String pMessage) {
        String words[] = pMessage.split("|");

        switch (words[0]) {
            case "GRAPH":
                setGraph(words);
                break;
            
            case "COLARR":
                updateColours(words);
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

    // show the winner
    private void finish(String pWords[]) {
        GraphClientTUI.setFinish(pWords[1].toCharArray()[0]);
    }

    private void setGraph(String pWords[]) {
        // Example "GRAPH|3|0-1,1-2,0-2"
        int graphLength = Integer.parseInt(pWords[1]);
        String graphVertices[] = pWords[3].split(",");

        for (int i = 0; i < graphLength; i++) {
            graph.addVertex(new Vertex(Integer.toString(i)));
        }

        for (int i = 0; i < graphVertices.length; i++) {
            graph.addEdge(new Edge(graph.getVertex(graphVertices[i].substring(0)), graph.getVertex(graphVertices[i].substring(2)), 0));
        }
    }

    private void updateColours(String pWords[]) {
        // Example "COLARR|3|G,Y,R"
        graphColours = new Colour[Integer.parseInt(pWords[1])];
        String colours[] = pWords[2].split(",");
        for (int i = 0; i < graphColours.length; i++) {
            graphColours[i] = ColourHelper.getColourFromChar(colours[i].charAt(0));
        }
    }
}
