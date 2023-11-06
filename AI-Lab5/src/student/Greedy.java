package student;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Greedy implements IPuzzleAlgo {
//	PriorityQueue<Node> frontier = new PriorityQueue<Node>();
//	ArrayList<Node> explorer = new ArrayList<Node>();
	@Override
	public Node execute(Puzzle model) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(PuzzleUtils.HeuristicComparatorByH);
		List<Node> explored = new ArrayList<Node>();
		frontier.add(model.getInitialState());
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getH() == 0) {
				return current;
			} else {
				explored.add(current);
				List<Node> children = model.getSuccessors(current);
				for (Node child : children) {
					if (!frontier.contains(child) && !explored.contains(child)) {
						child.setG(current.getG() + 1);

						frontier.add(child);
					}
				}
			}
		}

		return null;
	}
}
