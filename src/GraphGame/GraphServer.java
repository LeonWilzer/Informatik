package GraphGame;

import java.util.Random;

import javax.lang.model.util.ElementScanner14;

import lib.Graph;
import lib.List;
import lib.Server;
import lib.Vertex;
import lib.Edge;

public class GraphServer extends Server {

	Graph gameGraph;
	int nodeAmount;
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
		boolean added = false;
		for (int i = 0; i < players.length; i++) {
			if (players[i] == null) {
				players[i] = pClientIP + ":" + pClientPort;
				added = true;
				break;
			}
		}

		if (!added)
			Error.ERR_TOO_MANY_USERS.resolve(this, pClientIP, pClientPort);
	}

	@Override
	public void processMessage(String pClientIP, int pClientPort, String pMessage) {
		int playerID = getPlayerID(pClientIP, pClientPort);
		char[] mA = pMessage.toCharArray();

		boolean escaped = false;
		List<String> arguments = new List<String>();

		Instruct type = Instruct.NULL;

		arguments.toFirst();

		int arguments_length = 0;

		for (char c : mA) {
			if (escaped) {
				arguments.getContent().concat(Character.toString(c));
				escaped = false;
			} else {
				switch (c) {
					case Protocol.ESCAPE:
						escaped = true;
						break;
					case Protocol.ARG_SEP:
						if(type == Instruct.NULL)
						{
							arguments.toFirst();
							type = parseInstruction(arguments.getContent());
						}
							arguments.append(new String());
							arguments_length++;
							arguments.toLast();
						break;
					case Protocol.SUCC:
						type = Instruct.SUCC;
						break;
					case Protocol.ERROR:
						type = Instruct.ERROR;
						break;
					default:
						arguments.getContent().concat(Character.toString(c));
				}
			}
		}

		String[] parameters = new String[arguments_length];
		arguments.toFirst();

		int i = 0;
		while(arguments.hasAccess())
		{
			parameters[i] = arguments.getContent();
			i++;
			arguments.next();
		}

		switch(type)
		{
			case TURN:
				if(playerID==current_player)
					takeTurn(Integer.parseInt(parameters[1]), parseColour(parameters[2].charAt(0)), pClientIP, pClientPort);
				else
					Error.ERR_INVALID_USER.resolve(this, pClientIP, pClientPort);
				break;
			case NEWGAME:
				if(playerID == current_player)
					newGame(Integer.parseInt(parameters[1]));
				else
					Error.ERR_INVALID_USER.resolve(this, pClientIP, pClientPort);
				break;
			case WINNER:
				if(playerID == current_player)
					forfeit();
				else
					Error.ERR_INVALID_USER.resolve(this, pClientIP, pClientPort);
				break;
			default:
				Error.ERR.resolve(this, pClientIP, pClientPort);
		}
	}

	private Colour parseColour(char pColour)
	{
		switch(pColour)
		{
			case Protocol.BLUE:
				return Colour.BLUE;
			case Protocol.GREEN:
				return Colour.GREEN;
			case Protocol.YELLOW:
				return Colour.YELLOW;
			default:
				return Colour.RED;
		}
	}

	private Instruct parseInstruction(String pInstruct)
	{
		switch(pInstruct)
		{
			case Protocol.NEWGAME:
				return Instruct.NEWGAME;
			case Protocol.TURN:
				return Instruct.TURN;
			case Protocol.COLARR:
				return Instruct.COLARR;
			case Protocol.WINNER:
				return Instruct.WINNER;
			default:
				return Instruct.NULL;
		}
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

	// Example "GRAPH|3|0-1,1-2,0-2"
	private void sendGraphToAll() {
		String graphString = Protocol.GRAPH + Protocol.ARG_SEP;
		graphString += nodeAmount + Protocol.ARG_SEP;

		List<Edge> eL = gameGraph.getEdges();
		eL.toFirst();

		while(eL.hasAccess())
		{
			graphString += eL.getContent().getVertices()[0].getID() + Protocol.CONN_SEP + eL.getContent().getVertices()[1].getID() + Protocol.LIST_SEP;
			eL.next();
		}

		graphString.substring(0, graphString.length()-1);

		sendToAll(graphString);
	}

	// Example "COLARR|3|G,Y,R"
	private void sendColourArrToAll()
	{
		String arrString = Protocol.COLARR + Protocol.ARG_SEP;
		arrString += colourArray.length + Protocol.ARG_SEP;

		for(int i=0; i<colourArray.length; i++)
			arrString += colourArray[i].toChar() + Protocol.LIST_SEP;

		arrString.substring(0, arrString.length()-1);

		sendToAll(arrString);
	}

	public void forfeit()
	{
		if(current_player<=0)
			sendToAll(Protocol.WINNER + Protocol.ARG_SEP + (players.length-1));
		else
			sendToAll(Protocol.WINNER + Protocol.ARG_SEP + (current_player - 1));
	}

	private void newGame(int pNodeNum) {
		buildGraph(pNodeNum);
		sendGraphToAll();
		sendColourArrToAll();
		current_player = 0;
		boolean cont = true;
		while(cont)
		{
		}
	}

	private int getPlayerID(String pIP, int pPort) {
		for (int i = 0; i < max_players; i++) {
			if (players[i] == pIP + ":" + pPort)
				return i;
		}
		return -1;
	}

	private void buildGraph(int pNodeNum) {
		nodeAmount = pNodeNum;
		gameGraph = new Graph();
		for (int i = 0; i < pNodeNum; i++)
			gameGraph.addVertex(new Vertex(Integer.toString(i)));

		for (int i = 0; i < pNodeNum; i++) {
			for (int j = 0; j < pNodeNum; i++) {
				if (j != i && rnd.nextBoolean())
					gameGraph.addEdge(new Edge(gameGraph.getVertex(Integer.toString(i)),
							gameGraph.getVertex(Integer.toString(j)), 0));
			}
		}

		colourArray = new Colour[pNodeNum];
	}

	private void takeTurn(int pNodeNum, Colour pColour, String pPlayerIP, int pPlayerPort) {
		int playerID = getPlayerID(pPlayerIP, pPlayerPort);

		if (current_player == playerID) {
			Result turnResult = markNode(pNodeNum, pColour);
			if (turnResult == Succ.NODE_COLOURED){
				sendGraphToAll();
				playerpp();
			}

			turnResult.resolve(this, pPlayerIP, pPlayerPort);
		} else {
			Error.ERR_INVALID_USER.resolve(this, pPlayerIP, pPlayerPort);
		}
	}

	private void playerpp() {
		current_player++;
		if (current_player >= max_players)
			current_player = 0;
	}

	private Result markNode(int pNodeNum, Colour pColour) {
		Vertex node = gameGraph.getVertex(Integer.toString(pNodeNum));
		if (!node.isMarked()) {
			List<Vertex> neighbours = gameGraph.getNeighbours(node);
			neighbours.toFirst();
			while (neighbours.hasAccess()) {
				if (colourArray[Integer.parseInt(neighbours.getContent().getID())] == pColour)
					return Error.ERR_INVALID_COLOUR;
				neighbours.next();
			}
			node.setMark(true);
			colourArray[pNodeNum] = pColour;
			return Succ.NODE_COLOURED;
		} else {
			return Error.ERR_NODE_ALREADY_COLOURED;
		}
	}
}