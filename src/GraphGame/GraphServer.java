package GraphGame;

import java.util.Random;

import lib.Graph;
import lib.List;
import lib.Server;
import lib.Vertex;
import lib.Edge;

public class GraphServer extends Server {

    Graph gameGraph;
    String[] players;
    Colour[] colourArray;
    int current_player;
    int max_players;
    Random rnd;

    public GraphServer(int pPort) {
        super(pPort);
        current_player = 0;
        max_players = 2;
        players = new String[max_players];
        rnd = new Random();
    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) {
                players[i] = pClientIP + ":" + pClientPort;
                break;
            }
        }
    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        int playerID = getPlayerID(pClientIP, pClientPort);
    }

    @Override
    public void processClosingConnection(String pClientIP, int pClientPort) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == pClientIP + ":" + pClientPort) {
                players[i] = null;
                break;
            }
        }
    }

    private void newGame(int pNodeNum) {
        buildGraph(pNodeNum);

    }

    private int getPlayerID(String pIP, int pPort) {
        for (int i = 0; i < max_players; i++) {
            if (players[i] == pIP + ":" + pPort) {
                return i;
            }
        }
        return -1;
    }

    private void buildGraph(int pNodeNum) {
        gameGraph = new Graph();
        for (int i = 0; i < pNodeNum; i++) {
            gameGraph.addVertex(new Vertex(Integer.toString(i)));
        }

        for (int i = 0; i < pNodeNum; i++) {
            for (int j = 0; j < pNodeNum; i++) {
                if (j != i && rnd.nextBoolean()) {
                    gameGraph.addEdge(new Edge(gameGraph.getVertex(Integer.toString(i)),
                    gameGraph.getVertex(Integer.toString(j)), 0));
                }
            }
        }

        colourArray = new Colour[pNodeNum];
    }

    private Result takeTurn(int pPlayerID, int pNodeNum, Colour pColour) {
        if (current_player == pPlayerID) {
            Result turnResult = markNode(pNodeNum, pColour);
            if (turnResult == Result.OK) {
                playerpp();
                return Result.OK;
            } else {
                // TODO
            }
        } else {
            return Result.ERR_INVALID_USER;
        }
    }

    private void playerpp() {
        current_player++;
        if (current_player >= max_players) {
            current_player = 0;
        }
    }

    private Result markNode(int pNodeNum, Colour pColour) {
        Vertex node = gameGraph.getVertex(Integer.toString(pNodeNum));
        if (!node.isMarked()) {
            List<Vertex> neighbours = gameGraph.getNeighbours(node);
            neighbours.toFirst();
            while (neighbours.hasAccess()) {
                if (colourArray[Integer.parseUnsignedInt(neighbours.getContent().getID())] == pColour) {
                    return false;
                }
                neighbours.next();
            }
            node.setMark(true);
            colourArray[pNodeNum] = pColour;
            return true;
        } else {
            return false;
        }
    }
}
