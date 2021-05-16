package Nikolaus;

import lib.List;
import lib.TIO;

import java.util.HashMap;
import java.util.Map;

import lib.Edge;
import lib.Graph;
import lib.Vertex;

public class Nikolaus
{
	private Graph niko;
	public Nikolaus()
	{
		niko = genNiko();
		TIO.prt("Ist valide? " + istValide(TIO.AskString("Bitte validen Weg angeben:").toUpperCase(), niko));
		TIO.prt("Ist ein Rundweg? " + istRundweg(TIO.AskString("Bitte Rundweg angeben").toUpperCase(), niko));
	}

	public Graph genNiko()
	{
		String nameList = "ABCDE";

		Map<Character,Vertex> vertexDict = new HashMap<Character, Vertex>();
		List<Edge> edgeList = new List<Edge>();
		Graph haus = new Graph();

		for(char pID : nameList.toCharArray())
			vertexDict.put(pID, new Vertex(Character.toString(pID)));

		edgeList.append(new Edge(vertexDict.get('A'), vertexDict.get('B'), 0));
		edgeList.append(new Edge(vertexDict.get('B'), vertexDict.get('C'), 0));
		edgeList.append(new Edge(vertexDict.get('C'), vertexDict.get('A'), 0));
		edgeList.append(new Edge(vertexDict.get('A'), vertexDict.get('E'), 0));
		edgeList.append(new Edge(vertexDict.get('E'), vertexDict.get('C'), 0));
		edgeList.append(new Edge(vertexDict.get('C'), vertexDict.get('D'), 0));
		edgeList.append(new Edge(vertexDict.get('D'), vertexDict.get('E'), 0));
		edgeList.append(new Edge(vertexDict.get('E'), vertexDict.get('B'), 0));

		for(char key : nameList.toCharArray())
			haus.addVertex(vertexDict.get(key));

		edgeList.toFirst();
		while(edgeList.hasAccess())
		{
			haus.addEdge(edgeList.getContent());
			edgeList.next();
		}

		return haus;
	}

	public boolean istRundweg(String pWeg, Graph pGraph)
	{
		char[] wegArray = pWeg.toCharArray();
		if(wegArray[0]==wegArray[wegArray.length-1] && istValide(pWeg, pGraph))
			return true;
		else
			return false;
	}

	public boolean istValide(String pWeg, Graph pGraph)
	{
		char[] wegArray = pWeg.toCharArray();

		for(int i=0; i<wegArray.length-1; i++)
		{
			List<Vertex> nachbarn = pGraph.getNeighbours(pGraph.getVertex(Character.toString(wegArray[i])));
			boolean hatNachbar = false;

			nachbarn.toFirst();

			while(!hatNachbar && nachbarn.hasAccess())
			{
				if(nachbarn.getContent().equals(pGraph.getVertex(Character.toString(wegArray[i+1]))))
					hatNachbar = true;

				nachbarn.next();
			}

			if(!hatNachbar)
				return false;
		}
		return true;
	}
}