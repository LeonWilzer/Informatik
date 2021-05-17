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

		Graph haus = new Graph();

		for(char pID : nameList.toCharArray())
			haus.addVertex(new Vertex(Character.toString(pID)));

		haus.addEdge(new Edge(haus.getVertex("A"), haus.getVertex("B"), 0));
		haus.addEdge(new Edge(haus.getVertex("B"), haus.getVertex("C"), 0));
		haus.addEdge(new Edge(haus.getVertex("C"), haus.getVertex("A"), 0));
		haus.addEdge(new Edge(haus.getVertex("A"), haus.getVertex("E"), 0));
		haus.addEdge(new Edge(haus.getVertex("E"), haus.getVertex("C"), 0));
		haus.addEdge(new Edge(haus.getVertex("C"), haus.getVertex("D"), 0));
		haus.addEdge(new Edge(haus.getVertex("D"), haus.getVertex("E"), 0));
		haus.addEdge(new Edge(haus.getVertex("E"), haus.getVertex("B"), 0));

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