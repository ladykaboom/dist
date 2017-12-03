package model;

import java.util.ArrayList;

public class Tree {

	private static int numberOfNodes;
	private static ArrayList<Node> nodes = new ArrayList<Node>();
	
	// if there is an edge between nodes A and B then: edges[A][B] = true
	// otherwise it's false
	private static boolean[][] edges;

	public Tree(int numberOfNodes, ArrayList<Node> nodes, boolean[][] edges) {
		this.numberOfNodes = numberOfNodes;
		this.nodes = nodes;
		this.edges = edges;
	}

	public static int getNumberOfNodes() {
		return numberOfNodes;
	}

	public static ArrayList<Node> getNodes() {
		return nodes;
	}

	public static boolean[][] getEdges() {
		return edges;
	}
	
	

}
