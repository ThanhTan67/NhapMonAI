package task123456;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class UniformCostSearchAlgo implements ISearchAlgo {

	PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparator());

	public class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			// TODO Auto-generated method stub
			if (o1.getPathCost() == o2.getPathCost()) {
				return o1.compareTo(o2);
			} else {

				return Double.compare(o1.getPathCost(), o2.getPathCost());
			}
		}
	}

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		double pastCost = 0;
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparator());
		frontier.add(root);
		Set<Node> explored = new HashSet<Node>();
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal))
				return current;
			explored.add(current);
			List<Edge> children = current.getChildren();
			for (Edge edge : children) {
				Node child = edge.getEnd();
				if (!explored.contains(child)) {
					if (!frontier.contains(child)) {
						frontier.add(child);
						child.setParent(current);
						child.setPathCost(current.getPathCost() + edge.getWeight());
					} else {
						frontier.add(current);
						child.setParent(current);
						double newPathCost = current.getPathCost() + edge.getWeight();
						if (child.getPathCost() > newPathCost) {
							child.setPathCost(newPathCost);
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		boolean started = false;
		double pastCost = 0;
		root.setPathCost(pastCost);
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparator());
		frontier.add(root);
		Set<Node> explored = new HashSet<Node>();
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(start)) {
				started = true;
				current.setParent(null);
				current.setPathCost(0);
				frontier.clear();
				explored.clear();
			}
			if (current.getLabel().equals(goal))
				return current;
			explored.add(current);
			List<Edge> children = current.getChildren();
			for (Edge edge : children) {
				Node child = edge.getEnd();
				if (!explored.contains(child)) {
					if (!frontier.contains(child)) {
						frontier.add(child);
						child.setParent(current);
						child.setPathCost(current.getPathCost() + edge.getWeight());
					} else {
						frontier.add(current);
						child.setParent(current);
						double newPathCost = current.getPathCost() + edge.getWeight();
						if (child.getPathCost() > newPathCost) {
							child.setPathCost(newPathCost);
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String goal, int limitedDepth) {
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(root);
		List<Node> explored = new LinkedList<Node>();
		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
			if (current.getLabel().equals(goal))
				return current;
			explored.add(current);
			if (current.getDepth() < limitedDepth) {
				List<Node> children = current.getChildrenNodes();
				Collections.sort(children, new Comparator<Node>() {

					@Override
					public int compare(Node o1, Node o2) {
						// TODO Auto-generated method stub
						return o2.getLabel().compareTo(o1.getLabel());
					}

				});
				for (Node child : children) {
					if (!frontier.contains(child) && !explored.contains(child)) {
						frontier.add(child);
						child.setParent(current);
						child.setDepth(current.getDepth() + 1);
					}
				}
			}
		}
		return null;
	}

	@Override
	public Node execute_TreeSearch(Node root, String goal) {
		// TODO Auto-generated method stub
		double pathCost = 0;
		root.setPathCost(pathCost);
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparator());
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal))
				return current;
			List<Edge> children = current.getChildren();
			for (Edge edge : children) {
				Node child = edge.getEnd();
				if (!frontier.contains(child)) {
					child.setParent(current);
					child.setPathCost(current.getPathCost() + edge.getWeight());
					frontier.add(child);

				} else {
					double newPathCost = current.getPathCost() + edge.getWeight();
					if (child.getPathCost() > newPathCost) {
						child.setPathCost(newPathCost);
						child.setParent(current);
						frontier.add(child);
					}
				}
			}
		}
		return null;
	}

	@Override
	public Node execute_TreeSearch(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		boolean started = false;
		double pathCost = 0;
		root.setPathCost(pathCost);
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparator());
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(start)) {
				started = true;
				current.setParent(null);
				current.setPathCost(0);
				frontier.clear();
			}
			if (current.getLabel().equals(goal) && started == true)
				return current;
			List<Edge> children = current.getChildren();
			for (Edge edge : children) {
				Node child = edge.getEnd();
				if (!frontier.contains(child)) {
					child.setParent(current);
					child.setPathCost(current.getPathCost() + edge.getWeight());
					frontier.add(child);
				} else {
					double newPathCost = current.getPathCost() + edge.getWeight();
					if (child.getPathCost() > newPathCost) {
						child.setPathCost(newPathCost);
						child.setParent(current);
						frontier.add(child);

					}
				}
			}

		}
		return null;
	}
	

}
