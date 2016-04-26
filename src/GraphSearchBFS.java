/*
 * Name: Colleen Rogers 
 * Course: COSC320-Advanced Data Structures and Algorithm Analysis
 * Date: 10/28/14
 * Filename: GraphSearchBFS.java
 * Description: Breadth First Search implementation using an adjacency matrix
 */

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GraphSearchBFS {

	// Global variable declaration
	private static final int WHITE = 1;
	private static final int GRAY = 2;
	private static final int BLACK = 3;

	// Vertex class definition
	private static class Vertex {
		char key;
		int color;
		int distance;
		Vertex parent; // dfs specific
		int visitTime; // dfs specific
		int finishTime; // dfs specific

		Vertex(char k) {
			key = k;
		}

		public String toString() {
			return "" + key;
		}
	}

	// Edge Class definition
	private static class Edge {
		char source;
		char destination;

		Edge(char s, char d) {
			source = s;
			destination = d;
		}

		public String toString() {
			return source + " " + destination;
		}
	}

	/********************************************************************/
	public static void main(String[] args) throws IOException {
		Scanner file = new Scanner(new File("P:\\bfsFile.txt"));

		// populate the vertex array from the data file
		int numVertices = file.nextInt();
		Vertex[] vertices = new Vertex[numVertices];
		for (int i = 0; i < vertices.length; i++) {
			vertices[i] = new Vertex(file.next().charAt(0));
		}

		// populate the edge array from the data file
		int numEdges = file.nextInt();
		Edge[] edges = new Edge[numEdges];
		for (int i = 0; i < edges.length; i++) {
			edges[i] = new Edge(file.next().charAt(0), file.next().charAt(0));
		}

		// create adjacency matrix based on the vertex and edge arrays
		// all edges are bidirectional
		int[][] matrix = new int[numVertices][numVertices];// automatically
															// initialized to 0
		for (int i = 0; i < edges.length; i++) {
			char source = edges[i].source;
			char des = edges[i].destination;
			int sPos = -1; // source position
			int dPos = -1; // destination position
			for (int j = 0; j < vertices.length; j++) {
				if (vertices[j].key == source) {
					sPos = j;
				}
				if (vertices[j].key == des) {
					dPos = j;
				}
			}
			matrix[sPos][dPos] = 1;
			matrix[dPos][sPos] = 1;
		}
		System.out.println("Vertices: " + Arrays.toString(vertices));
		System.out.println("\nEdges: " + Arrays.toString(edges));
		System.out.println("\nAdjacency Matrix: ");
		for (int q = 0; q < matrix.length; q++) {
			System.out.println(Arrays.toString(matrix[q]));
		}
		System.out.println("\n********SEARCH RESULTS***********");
		bfs(vertices, matrix, 0);
		file.close();
	}

	/******************************************************************/
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

	/*******************************************************************/
	// Perform Breadth First Search
	public static Vertex[] bfs(Vertex[] vertices, int[][] adjacencyMatrix, int s) {
		for (int i = 1; i < vertices.length; i++) {
			// assign color distance and parent
			vertices[i].color = WHITE;
			vertices[i].distance = Integer.MAX_VALUE;
			vertices[i].parent = null;
		}
		vertices[s].color = GRAY;
		vertices[s].distance = 0;
		vertices[s].parent = null;

		// create a queue
		Queue<Vertex> Q = new LinkedList<Vertex>();
		Q.add(vertices[s]);

		// while the queue is not empty
		while (!Q.isEmpty()) {
			Vertex u = Q.peek();
			int uPos = findKeyPos(vertices, u);
			// for each vertex v adjacent to vertex u
			for (int vPos = 0; vPos < adjacencyMatrix.length; vPos++) {
				if (adjacencyMatrix[vPos][uPos] == 1) {
					if (vertices[vPos].color == WHITE) {
						vertices[vPos].color = GRAY;
						vertices[vPos].parent = u;
						vertices[vPos].distance = u.distance + 1;

						Q.add(vertices[vPos]);
					}
				}
			}
			Vertex removed = Q.remove();
			System.out.println("visited: " + removed.key + "\ndistance: "
					+ removed.distance + "\n");
			u.color = BLACK;
		}

		return null;
	}
}