/*
 * Name: Colleen Rogers 
 * Course: COSC320-Advanced Data Structures and Algorithm Analysis
 * Date: 11/6/14
 * Filename: GraphSearchDFS.java
 * Description: Depth First Search implementation using an adjacency list
 */
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class GraphSearchDFS {
	// Global variable declaration
	private static final int WHITE = 1;
	private static final int GRAY = 2;
	private static final int BLACK = 3;

	/******************************** Vertices/Edges ***********************************************/

	// Vertex class definition
	private static class Vertex {
		char key;
		int color;
		int distance;
		Vertex parent; // dfs specific
		int visitTime; // dfs specific
		int finishTime; // dfs specific
		boolean wasVisited;

		Vertex(char k) {
			key = k;
		}

		public char getKey() {
			return key;
		}

		public String toString() {
			return "" + key;
		}
	}

	// Edge Class definition
	private static class Edge {
		Vertex source;
		Vertex destination;

		Edge(char s, char d) {
			source = new Vertex(s);
			destination = new Vertex(d);
		}

		public String toString() {
			return source + " " + destination;
		}
	}

	/*****************************************************************/
	public static void main(String[] args) throws IOException {
		{
			Scanner file = new Scanner(new File("P:\\bfsFile.txt"));
			int numEdges, numVertices;

			Scanner scan = new Scanner(System.in);

			numVertices = file.nextInt();
			// Vertex class
			Vertex[] vertices = new Vertex[numVertices];
			for (int i = 0; i < vertices.length; i++) {
				vertices[i] = new Vertex(file.next().charAt(0));
			}
			numEdges = file.nextInt();
			// Edge class

			Edge[] edges = new Edge[numEdges];
			for (int i = 0; i < edges.length; i++) {
				edges[i] = new Edge(file.next().charAt(0), file.next()
						.charAt(0)); // new edge
			}

			LinkedList<Vertex>[] AdjList = new LinkedList[numVertices];

			for (int i = 0; i < vertices.length; i++) {
				AdjList[i] = new LinkedList();
				AdjList[i].add(vertices[i]);
			}
			for (int k = 0; k < AdjList.length; k++) {
				for (int j = 0; j < numEdges; j++) {
					if (vertices[k].key == edges[j].source.key) {
						AdjList[k].add(edges[j].destination);
					}
				}
			}
			System.out.println("Vertices: " + Arrays.toString(vertices));
			System.out.println("Edges: " + Arrays.toString(edges));

			System.out.println("\n\nADJACENCY LIST:");
			for (int x = 0; x < AdjList.length; x++) {
				ListIterator iter = AdjList[x].listIterator();
				while (iter.hasNext()) {
					System.out.print(iter.next() + " --> ");
				}
				System.out.println();
			}

			dfs(vertices, AdjList);

			file.close();
		}
	}

	/**************************************************************/

	public static int findKeyPos(Vertex[] vertices, Vertex v) {
		int pos = -1;
		for (int n = 0; n < vertices.length; n++) {
			if (vertices[n].key == v.key) {
				pos = n;
				break;
			}
		}
		return pos;
	}

	/******************************** Depth First Search ******************************/
	public static void dfs(Vertex[] vertices, LinkedList<Vertex>[] list) {
		int time;

		for (int i = 0; i < vertices.length; i++) {

			// assign color distance and parent
			vertices[i].color = WHITE;
			vertices[i].parent = null;
		}
		time = 0;
		for (int j = 0; j < list.length; j++) {
			if (vertices[j].color == WHITE) {
				dfs_visit(list, vertices[j], vertices);
			}

		}
	}

	/******************************** DFS Visit ******************************/
	public static void dfs_visit(LinkedList<Vertex>[] list, Vertex v,
			Vertex[] vertices) {
		v.color = GRAY;
		System.out.println("Visited: " + v.key);

		int vIndex = findKeyPos(vertices, v);

		for (int i = 0; i < list[vIndex].size(); i++) {

			int nodeIndex = findKeyPos(vertices, list[vIndex].get(i));

			if (vertices[nodeIndex].color == WHITE) {
				vertices[nodeIndex].parent = v;
				dfs_visit(list, vertices[nodeIndex], vertices);
			}

		}
		v.color = BLACK;
	}
}