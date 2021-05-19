
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
           
       
            
    }
/**
 * Die Methode liefert die Dichte des sozialen Netzwerks.
 */
    
   public int gibDichte()
   {
      return 0;
   }
   
 /**
 * Die Methode liefert den Zentralitätsgrad des im Parameter angegebenen Knoten pVertex im sozialen Netzwerk.
 */
   public int gibZentralität(Vertex pVertex)
   {
       //hier entsteht die Methode, die den Zentralitätsgrad des Knoten pVertex zurückliefert
       return 0;
   }
   
   
}
