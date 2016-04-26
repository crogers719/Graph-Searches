import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

 
public class dfs 
{
    /********************************Vertices/Edges***********************************************/
	//Global variable declaration
		private static final int WHITE=1;
		private static final int GRAY=2;
		private static final int BLACK=3;

	//Vertex class definition	
		private static class Vertex{
			char key;
			int color;
			int distance;
			Vertex parent; //dfs specific
			int visitTime; //dfs specific
			int finishTime; //dfs specific
			boolean wasVisited;

			Vertex(char k){
				key=k;
				wasVisited=false;
			}
			public String toString(){
				return ""+key;
			}
		}
	//Edge Class definition
		private static class Edge{
			char source;
			char destination; 
			Edge(char s, char d){
				source=s;
				destination=d;
			}
			public String toString(){
				return source+ " "+ destination;
			}
		}
    /********************************Adjacency List***********************************************/
	private static class GraphAdjacencyList{
   /* Makes use of Map collection to store the adjacency list for each vertex.*/
    private  Map<Vertex, List<Vertex>> Adjacency_List;	
   /*
    * Initializes the map to with size equal to number of vertices in a graph
    * Maps each vertex to a given List Object 
    */
    public GraphAdjacencyList(int number_of_vertices)
    {
        Adjacency_List = new HashMap<Vertex, List<Vertex>>();	
        for (int i = 1 ; i <= number_of_vertices ; i++)
        { 
        	Vertex v=new Vertex (' ');
        	Adjacency_List.put(v, new LinkedList<Vertex>());
        }
    }
 
 
    /* Adds nodes in the Adjacency list for the corresponding vertex */
    public void setEdge(char source , char destination)
    {
      
       List<Vertex> slist = Adjacency_List.get(source);
       Vertex sVer=new Vertex (destination);
       slist.add(sVer);
       List<Vertex> dlist = Adjacency_List.get(destination);
       Vertex dVer= new Vertex (source);
       dlist.add(dVer);
   }
 
   /* Returns the List containing the vertex joining the source vertex */		
    public List<Vertex> getEdge(int source)
    {
        if (source > Adjacency_List.size())
        {
            System.out.println("the vertex entered is not present2");
            return null;
        }
        return Adjacency_List.get(source);
    }
}
    
    /********************************change the main***********************************************/
	public static void main(String[] args) throws IOException {
         {
            Scanner file=new Scanner(new File("P:\\bfsFile.txt")); 
    	     int numEdges,numVertices;
             
             Scanner scan = new Scanner(System.in);
              
                 numVertices = file.nextInt();
                 //Vertex class
                 Vertex [] vertices =new Vertex [numVertices];
                 for (int i=0; i<vertices.length; i++){
                	 vertices[i]=new Vertex(file.next().charAt(0)); //...=new Vertex(file.next.....)
                 }
                 numEdges = file.nextInt();
                 //Edge class
                 GraphAdjacencyList adjacencyList = new GraphAdjacencyList(numVertices);
                 Edge [] edges=new Edge[numEdges];
                 for (int i=0;i<edges.length; i++){
                	 edges[i]=new Edge(file.next().charAt(0), file.next().charAt(0)); //new edge
                 }
     
                 Vertex[] AdjList=new Vertex[100]; 
                            
                 for (int i=0; i<edges.length; i++){
                	 
                 }
     
                 /* Prints the adjacency List representing the graph.*/
                 System.out.println("Vertices: "+ Arrays.toString(vertices));
          		System.out.println("\nEdges: "+ Arrays.toString(edges));
          	
                 System.out.println ("the given Adjacency List for the graph \n");
                 for (int i = 1 ; i <= numVertices ; i++)
                 {
                     System.out.print(i+"->");
                     List<Vertex> edgeList = adjacencyList.getEdge(i);
                     for (int j = 1 ; ; j++ )
                     {
                         if (j != edgeList.size())
                         {
                             System.out.print(edgeList.get(j - 1 )+"->");
                         }
                         else
                         {
                             System.out.print(edgeList.get(j - 1 ));
                             break;
                         }						 
                     }
                     System.out.println();					
                  }
          		System.out.println("\n********SEARCH RESULTS***********");
         		//dfs (vertices, matrix, 0);
         		file.close();
         }
}
	
    

    /********************************Depth First Search***********************************************/ 

   
}

