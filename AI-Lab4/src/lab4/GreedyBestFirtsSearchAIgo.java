package lab4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class GreedyBestFirtsSearchAIgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByGn());
		frontier.add(root);
		List<Node> explored = new ArrayList<>();
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal))
				return current;
			List<Edge> children = current.getChildren();
			for (Edge edge : children) {
				Node child = edge.getEnd();
				if (!frontier.contains(child) && !explored.contains(child)) {
					child.setParent(current);
					frontier.add(child);
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		boolean started = false;
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByGn());
		List<Node> explored = new ArrayList<>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(start)) {
				started = true;
				frontier.clear();
				explored.clear();
				current.setParent(null);
			}
			if (current.getLabel().equals(goal) && started == true)
				return current;
			explored.add(current);
			List<Edge> children = current.getChildren();
			for (Edge edge : children) {
				Node child = edge.getEnd();
				if (!frontier.contains(child) && !explored.contains(child)) {
					child.setParent(current);
					frontier.add(child);
				}
			}
		}
		return null;
	}

	class NodeComparatorByGn implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			// TODO Auto-generated method stub
			Double h1 = o1.getH();
			Double h2 = o2.getH();
			int result = h1.compareTo(h2);
			if (result == 0)
				return o1.getLabel().compareTo(o2.getLabel());
			else
				return result;
		}
	}
}
