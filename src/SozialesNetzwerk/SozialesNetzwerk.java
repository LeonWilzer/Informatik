package SozialesNetzwerk;

import lib.BetterList;
import lib.Edge;
import lib.Graph;
import lib.List;
import lib.TIO;
import lib.Vertex;

/**
 * Beschreiben Sie hier die Klasse SozialesNetzwerk.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class SozialesNetzwerk
{
    Graph meinNetz;
    
    public SozialesNetzwerk()
    {
       meinNetz= new Graph();
       Vertex a = new Vertex("A");
       Vertex b = new Vertex("B");
       Vertex c = new Vertex("C");
       Vertex d = new Vertex("D");
       Vertex e = new Vertex("E");
       Vertex f = new Vertex("F");
       Vertex g = new Vertex("G");
       Vertex h = new Vertex("H");
       Vertex i = new Vertex("I");
       Vertex j = new Vertex("J");
       
       meinNetz.addVertex(a);
       meinNetz.addVertex(b);
       meinNetz.addVertex(c);
       meinNetz.addVertex(d);
       meinNetz.addVertex(e);
       meinNetz.addVertex(f);
       meinNetz.addVertex(g);
       meinNetz.addVertex(h);
       meinNetz.addVertex(i);
       meinNetz.addVertex(j);
      
       
       meinNetz.addEdge(new Edge(a,b,1));
       meinNetz.addEdge(new Edge(a,c,1));
       meinNetz.addEdge(new Edge(a,d,1));
       meinNetz.addEdge(new Edge(a,f,1));
       meinNetz.addEdge(new Edge(b,d,1));
       meinNetz.addEdge(new Edge(b,e,1));
       meinNetz.addEdge(new Edge(b,g,1));
       meinNetz.addEdge(new Edge(c,d,1));
       meinNetz.addEdge(new Edge(c,f,1));
       meinNetz.addEdge(new Edge(d,g,1));
       meinNetz.addEdge(new Edge(d,f,1));
       meinNetz.addEdge(new Edge(d,e,1));
       meinNetz.addEdge(new Edge(e,g,1));
       meinNetz.addEdge(new Edge(f,g,1));
       meinNetz.addEdge(new Edge(f,h,1));
       meinNetz.addEdge(new Edge(g,h,1));
       meinNetz.addEdge(new Edge(h,i,1));
       meinNetz.addEdge(new Edge(i,j,1));
           
       TIO.prt("Dichte: " + gibDichte());
       TIO.prt("Zentralität: " + gibZentralität(e));
    }
/**
 * Die Methode liefert die Dichte des sozialen Netzwerks.
 */
    
   public int gibDichte()
   {
      int m = size(meinNetz.getEdges());
      int n = size(meinNetz.getVertices());
      int dichte = 2*m/n*(n-1);
   
      return dichte;
   }
   
 /**
 * Die Methode liefert den Zentralitätsgrad des im Parameter angegebenen Knoten pVertex im sozialen Netzwerk.
 */
   public double gibZentralität(Vertex pVertex)
   {
      //BetterList<Edge> edges = (BetterList<Edge>) meinNetz.getEdges(pVertex);
      double d = size(meinNetz.getEdges(pVertex));
      //BetterList<Vertex> vertices = (BetterList<Vertex>) meinNetz.getVertices();
      double n = size(meinNetz.getVertices());
      double zentral = d / (n-1);
      return zentral;
   }
   
   public int size(List pList)
    {
        int count = 0;

        pList.toFirst();
        while(pList.hasAccess())
            {
                count++;
                pList.next();
            }
        return count;
     }
}