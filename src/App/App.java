package App;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Node;
import model.Tree;
import service.NodeService;
import service.TreeService;

/**
 * Tree structure is taken from a file which is stored in the 'src' folder.
 * First line is number of nodes and rest of them defines edges. i.e: 3 - 3
 * nodes 0 1 - edge between '0' and '1' 0 2 - edge between '0' and '2' 1 2 -
 * edge between '1' and '2'
 * 
 * The MST is just displayed in a console.
 * 
 * @author Alicja Gromotowicz
 *
 */
public class App {

	private static Tree tree;

	public static void main(String[] args) {

		/* create Tree from the file */
		TreeService tService = new TreeService();
		tree = tService.readDataFromFileAndCreateTree("tree.txt");

		/* create services for each node and run them as a thread */
		List<NodeService> ns = new ArrayList<NodeService>();
		for (Node n : tree.getNodes()) {
			ns.add(new NodeService(n, tService));
		}

		for (NodeService nodeSer : ns)
			new Thread(nodeSer).start();

		/*
		 * set first node as a root (it will start the alghotitm for finding MST)
		 */
		ns.get(0).setRoot();

	}

}