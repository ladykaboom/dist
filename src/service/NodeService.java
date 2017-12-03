package service;

import java.util.List;

import App.App;
import model.Node;
import model.Node.NODE_STATE;

/**
 * Each Node is running the same algorithm to find their childrens.
 * 
 * @author Alicja Gromotowicz
 *
 */
public class NodeService implements Runnable {

	/* The node for which this service is running */
	private Node node;

	/*
	 * Tree service which contains data of all edges (neighbours) in this tree
	 */
	private TreeService tService;

	public NodeService(Node node, TreeService tService) {
		this.node = node;
		this.tService = tService;
	}

	@Override
	public void run() {
		/* wait for a root/a node for which a parent has been set */
		while (this.node.hasNullParent() && !this.node.isRoot())
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}

		/* find all children for this node and set this node as a parent */
		findChildren();

		/* wait until the node has not been finished */
		while (!this.node.isFinished())
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}

		/* set this node to the finished state */
		this.node.getParent().finished(this.node);

		/* just display MST */
		if (this.node.isRoot()) {
			System.out.println(this.node.displayMST(new StringBuilder(), 0));
		}

	}

	public void findChildren() {
		List<Node> neigbours = tService.findNeighbours(this.node.getId(), this.node.getParent().getId());
		for (Node child : neigbours) {
			this.node.getNeighbours().put(child, NODE_STATE.UNFINISHED);
			/*
			 * set this node as a parent to the founded child (if this child has
			 * no parent yet)
			 */
			synchronized (this) {
				if (child.hasNullParent()) {
					child.setParent(this.node);
					this.node.putChild(child);
				} else {
					// this child has already a parent, so this node will not be
					// a parent of this child in MST
					this.node.notChild(child);
				}
			}
		}

	}

	public void setRoot() {
		this.node.setParent(new Node(-1));
		this.node.setIsRoot(true);
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

}
