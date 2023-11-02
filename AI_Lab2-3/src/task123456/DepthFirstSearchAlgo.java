package task123456;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		boolean check = false;
		Stack<Node> fontier = new Stack<>();

		fontier.add(root);
		//

		List<Node> explored = new ArrayList<>();
		while (!fontier.isEmpty()) {
			Node current = fontier.pop();
			if (current.getLabel().equals(goal))
				return current;
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
			}
			//
			explored.add(current);
			List<Node> children = current.getChildrenNodes();
			DESC_Sort(children);
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

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		boolean check = false;
		boolean Started = false;
		Stack<Node> fontier = new Stack<>();
		fontier.add(root);
		//
		List<Node> explored = new ArrayList<>();
		while (!fontier.isEmpty()) {
			Node current = fontier.pop();
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
			DESC_Sort(children);
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

	// ---------------------------------------------------------------------------------

	@Override
	public Node execute_TreeSearch(Node root, String goal) {
		boolean visitedG = false;
		Stack<Node> fontier = new Stack<>();
		fontier.add(root);
		//
		while (!fontier.isEmpty()) {
			Node current = fontier.pop();
			// kiểm tra có là goal hay ko
			if (current.getLabel().equals(goal))
				return current;
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
			// Lấy ra danh sách các node con
			List<Node> children = current.getChildrenNodes();
			DESC_Sort(children);// xếp theo chiều giảm dần
			for (Node child : children) {
				//
				fontier.add(child);
				if (child.getParent() == null)
					child.setParent(current);
			}
		}
		return null;
	}

	@Override
	public Node execute_TreeSearch(Node root, String start, String goal) {
		boolean Started = false, visitedG = false;
		Stack<Node> fontier = new Stack<>();
		fontier.add(root);
		//
		while (!fontier.isEmpty()) {
			Node current = fontier.pop();
			if (current.getLabel().equals(start)) {
				Started = true;
				fontier.clear();
				current.setParent(null);
			}
			if (current.getLabel().equals(goal) && Started == true)
				return current;
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
//				System.out.println(endNode.getPathCost()+e.getBegin().getLabel()+"->"+e.getEnd().getLabel());

			}
			List<Node> children = current.getChildrenNodes();
			DESC_Sort(children);
			for (Node child : children) {
				fontier.add(child);
				if (child.getParent() == null)
					child.setParent(current);
			}

		}
		return null;
	}

	public void DESC_Sort(List<Node> children) {
		Collections.sort(children, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o2.getLabel().compareTo(o1.getLabel());
			}
		});
	}

	@Override
	public Node execute(Node root, String goal, int limitedDepth) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
