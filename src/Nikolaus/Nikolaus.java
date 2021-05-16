package Nikolaus;

import lib.List;

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
			haus.addEdge(edgeList.getContent());

		return haus;
	}
}