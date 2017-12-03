package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Node;
import model.Tree;

/**
 * Read tree data from the file
 * 
 * @author Alicja Gromotowicz
 *
 */
public class TreeService {

	private static int numberOfNodes;
	private static ArrayList<Node> nodes = new ArrayList<Node>();;
	private static boolean[][] edges;
	private static Tree tree;

	public static Tree readDataFromFileAndCreateTree(String fileName) {

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line = br.readLine();
			numberOfNodes = Integer.parseInt(line);
			edges = new boolean[numberOfNodes][numberOfNodes];

			/* create nodes */
			Node NULL_NODE = new Node(-1);
			for (int i = 0; i < numberOfNodes; i++) {
				nodes.add(new Node(i));
				nodes.get(i).setParent(NULL_NODE);
			}

			line = br.readLine();

			/* create edges */
			while (line != null) {
				String[] srcAndDest = line.split(" ");
				System.out.println(srcAndDest[0] + " | " + srcAndDest[1]);
				addEdge(Integer.parseInt(srcAndDest[0]), Integer.parseInt(srcAndDest[1]));
				line = br.readLine();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* create a tree */
		tree = new Tree(numberOfNodes, nodes, edges);

		return tree;
	}

	public static void addEdge(int nodeA, int nodeB) {
		edges[nodeB][nodeA] = (edges[nodeA][nodeB] = true);
	}

	public static ArrayList<Node> findNeighbours(int src, int parent) {
		ArrayList<Node> neighbours = new ArrayList<Node>();
		boolean[] edges = tree.getEdges()[src];
		for (int i = 0; i < tree.getNumberOfNodes(); i++)
			if (edges[i] == true && i != src && i != parent)
				neighbours.add(tree.getNodes().get(i));

		return neighbours;
	}
}
