package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Node {

	/* possible states of a node (in order to another nodes) */
	public enum NODE_STATE {
		UNFINISHED, // has others children and they are not finished
		FINISHED, // has no more children
		NOT_A_CHILD; // it was a child of some node, but it will not be in MST
	}

	/* node id */
	private int id;

	/* synchronized variables */
	private volatile Node parent;
	private volatile boolean isRoot;
	private volatile HashMap<Node, NODE_STATE> neighbours = new HashMap<Node, NODE_STATE>();

	public Node(int id) {
		this.id = id;
	}

	public boolean hasNullParent() {
		if (parent.getId() == -1)
			return true;
		return false;
	}

	/*
	 * methods which change state of a node
	 */
	public void putChild(Node child) {
		neighbours.put(child, NODE_STATE.UNFINISHED);
	}

	public void notChild(Node child) {
		neighbours.put(child, NODE_STATE.NOT_A_CHILD);
	}

	public void finished(Node child) {
		neighbours.put(child, NODE_STATE.FINISHED);
	}

	public boolean isFinished() {
		for (NODE_STATE relation : neighbours.values())
			if (relation.equals(NODE_STATE.UNFINISHED))
				return false;
		return true;
	}

	// displaying hashmap (of neighbours) - from stackoverflow :)
	public String displayMST(StringBuilder sb, int index) {
		sb.append("\n---");
		for (int i = 0; i < index; i++)
			sb.append("----");
		sb.append(this.id);
		Iterator it = neighbours.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (pair.getValue().equals(NODE_STATE.FINISHED)) {
				((Node) pair.getKey()).displayMST(sb, index + 1);
			}
		}
		return sb.toString();
	}

	/* getters and setters */
	public int getId() {
		return this.id;
	}

	public boolean isRoot() {
		return isRoot;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public HashMap<Node, NODE_STATE> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(HashMap<Node, NODE_STATE> neighbours) {
		this.neighbours = neighbours;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIsRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

}