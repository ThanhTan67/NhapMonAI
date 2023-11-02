package task123456;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		boolean check = false;
		Queue<Node> fontier = new LinkedList<>();
		fontier.add(root);
		//
		List<Node> explored = new ArrayList<>();
		while (!fontier.isEmpty()) {
			Node current = fontier.poll();
			if (current.getLabel().equals(goal))
				return current;
			//
			explored.add(current);
			List<Edge> path = current.getChildren();

			for (Edge e : path) {
				Node endNode = e.getEnd();

				if (check == false) {
					endNode.setPathCost(e.getBegin().getPathCost() + e.getWeight());
					if (endNode.getLabel().equals(goal)) {
						check = true;
						break;
					}
				}
//				System.out.println(endNode.getPathCost()+e.getBegin().getLabel()+"->"+e.getEnd().getLabel());

			}
//			System.out.println("------------------------------------------");

			List<Node> children = current.getChildrenNodes();
			for (Node child : children) {
				//
				if (!fontier.contains(child) && !explored.contains(child)) {
					fontier.add(child);
					child.setParent(current);
				}

			}

		}
		return null;
	}

	// gap nut bat dau start =true
	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		boolean check = false;
		boolean Started = false;
		Queue<Node> fontier = new LinkedList<>();
		fontier.add(root);
		//
		List<Node> explored = new ArrayList<>();
		while (!fontier.isEmpty()) {
			Node current = fontier.poll();
			if (current.getLabel().equals(start)) {
				Started = true;
				fontier.clear();
				explored.clear();
				current.setParent(null);
			}
			if (current.getLabel().equals(goal) && Started == true)
				return current;
			//
			explored.add(current);
			List<Edge> path = current.getChildren();
			for (Edge e : path) {
				Node endNode = e.getEnd();

				if (check == false && Started == true) {
					endNode.setPathCost(e.getBegin().getPathCost() + e.getWeight());
					if (endNode.getLabel().equals(goal)) {
						check = true;
						break;
					}
				}
//				System.out.println(endNode.getPathCost()+e.getBegin().getLabel()+"->"+e.getEnd().getLabel());

			}
			List<Node> children = current.getChildrenNodes();
			for (Node child : children) {
				//
				if (!fontier.contains(child) && !explored.contains(child)) {
					fontier.add(child);
					child.setParent(current);
				}

			}

		}
		return null;
	}

	// TreeSearch
	@Override
	public Node execute_TreeSearch(Node root, String goal) {
		boolean visitedG = false;
		Queue<Node> fontier = new LinkedList<>();
		fontier.add(root);
		//
		while (!fontier.isEmpty()) {
			Node current = fontier.poll();
			if (current.getLabel().equals(goal))
				return current;
			//
			List<Edge> path = current.getChildren();

			for (Edge e : path) {
				Node endNode = e.getEnd();

				if (visitedG == false) {
					endNode.setPathCost(e.getBegin().getPathCost() + e.getWeight());
					if (endNode.getLabel().equals(goal)) {
						visitedG = true;
						break;
					}
				}

			}

			List<Node> children = current.getChildrenNodes();
			ACS_Sort(children);
			for (Node child : children) {
				fontier.add(child);
				if (child.getParent() == null)
					child.setParent(current);
			}

		}

		return null;
	}

	@Override
	public Node execute_TreeSearch(Node root, String start, String goal) {
//		boolean check = false;
		boolean visitedG = false;
		boolean Started = false;
		Queue<Node> fontier = new LinkedList<>();
		fontier.add(root);
		//
		while (!fontier.isEmpty()) {
			Node current = fontier.poll();
			if (current.getLabel().equals(start)) {
				Started = true;
				fontier.clear();
				current.setParent(null);
			}
			if (current.getLabel().equals(goal) && Started == true)
				return current;
			//
			List<Edge> path = current.getChildren();
			for (Edge e : path) {
				Node endNode = e.getEnd();

				if (visitedG == false && Started == true) {
					endNode.setPathCost(e.getBegin().getPathCost() + e.getWeight());
					if (endNode.getLabel().equals(goal)) {
						visitedG = true;
						break;
					}
				}

			}
			List<Node> children = current.getChildrenNodes();
			ACS_Sort(children);
			for (Node child : children) {
				fontier.add(child);
				if (child.getParent() == null)
					child.setParent(current);
			}

		}

		return null;
	}

	public void ACS_Sort(List<Node> children) {
		Collections.sort(children, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o1.getLabel().compareTo(o2.getLabel());
			}
		});
	}

	@Override
	public Node execute(Node root, String goal, int limitedDepth) {
		// TODO Auto-generated method stub
		return null;
	}

}
