package task123456;

public class Test {

	public static void print(Node n) {
		for (String string : NodeUtils.printPath(n)) {
			System.out.print(" " + string + " ");
		}
		System.out.println();
		System.out.print(n + ",");

	}

	public static void main(String[] args) {

		Node nodeS = new Node("S");
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E");
		Node nodeF = new Node("F");
		Node nodeG = new Node("G");
		Node nodeH = new Node("H");
		nodeS.addEdge(nodeA, 5);
		nodeS.addEdge(nodeB, 2);
		nodeS.addEdge(nodeC, 4);
		nodeA.addEdge(nodeD, 9);
		nodeA.addEdge(nodeE, 4);
		nodeB.addEdge(nodeG, 6);
		nodeC.addEdge(nodeF, 2);
		nodeD.addEdge(nodeH, 7);
		nodeE.addEdge(nodeG, 6);
		nodeF.addEdge(nodeG, 1);

		// BFS graph search S->G
		BreadthFirstSearchAlgo bf1 = new BreadthFirstSearchAlgo();
		Node n1 = bf1.execute(nodeS, "G");
		print(n1);
		// BFS graph search bat ki->G
		BreadthFirstSearchAlgo bf2 = new BreadthFirstSearchAlgo();
		Node n2 = bf2.execute(nodeS, "A", "G");
		print(n2);
		// DFS graph search S->G
		DepthFirstSearchAlgo df1 = new DepthFirstSearchAlgo();
		Node n3 = df1.execute(nodeS, "G");
		print(n3);
		// DFS graph search bat ki->G
		DepthFirstSearchAlgo df2 = new DepthFirstSearchAlgo();
		Node n4 = df2.execute(nodeS, "A", "G");
		print(n4);

		// BFS Tree search S->G
		BreadthFirstSearchAlgo bfs1 = new BreadthFirstSearchAlgo();
		Node n_btr1 = bfs1.execute_TreeSearch(nodeS, "G");
		print(n_btr1);

		// BFS Tree search bat ki->G
		BreadthFirstSearchAlgo bfs2 = new BreadthFirstSearchAlgo();
		Node n_btr2 = bfs2.execute(nodeS, "A", "G");
		print(n_btr2);

		// DFS tree search S->G
		DepthFirstSearchAlgo dfs1 = new DepthFirstSearchAlgo();
		Node n33 = dfs1.execute_TreeSearch(nodeS, "G");
		print(n33);
		// DFS tree search bat ki->G
		DepthFirstSearchAlgo dfs2 = new DepthFirstSearchAlgo();
		Node n44 = dfs2.execute_TreeSearch(nodeS, "A", "G");
		print(n44);

		// Depth-limited search
		ISearchAlgo algoUni = new UniformCostSearchAlgo();
		Node result5 = algoUni.execute(nodeS, "G", 4);
		System.out.println("Depth-limited search:");
		for (String string : NodeUtils.printPath(result5)) {
			System.out.print(string + " ");
		}
		System.out.println();
		System.out.println(result5.getDepth());
	}
}
