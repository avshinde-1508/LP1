package alphabeta;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import alphabeta.Node.MinMax;

public class AlphaBeta {
	public HashMap<String, Node> gameTree = new HashMap<String, Node>();
	public SearchTree tree;
	Node nextMoveNode;
	

	public AlphaBeta(){
		gameTree = new SearchTree().getTree();
		
		alphaBetaAlgorithm();
		
	}

	public void alphaBetaAlgorithm(){
		int bestMove = (int)max_value(gameTree.get("A0"));
	
		System.out.println("Expansions completed");
		printAlphaBetaValues();
		
		System.out.println("\nValue of Max Node A = " + bestMove);
		System.out.println("\nMove that Max Node A would make is " + nextMoveNode.name);
	}

	public double max_value(Node currentNode){	
		if (currentNode.children.size() == 0) // if leaf_node
			return currentNode.value;
		double max = Double.NEGATIVE_INFINITY;
		
		System.out.println("Expanding Max Node " + currentNode.name + ": Alpha = " + currentNode.alpha + ", Beta: " + currentNode.beta);
		
		for (Node child : currentNode.children){
			if (currentNode.alpha >= currentNode.beta){
				System.out.println("pruning expansion of Node " + child.name + " because it cannot make alpha higher");
				if (currentNode.children.indexOf(child) == currentNode.children.size())
					return max;
				else
					continue;
			}
			child.alpha = currentNode.alpha;
			child.beta = currentNode.beta;
			double temp = min_value(child);
			if (temp >= max) {
				max = temp;
				nextMoveNode = child;
				if (temp >= currentNode.alpha)
					currentNode.alpha = temp;
				//				System.out.println("Max Node " + currentNode.name + " updated, value is now: " + max);
				//				System.out.println("Alpha: " + currentNode.alpha + ", Beta: " + currentNode.beta);
			}
		}
		return max;
	}

	public double min_value(Node currentNode){
		if (currentNode.children.size() == 0) // if leaf_node
			return currentNode.value;
		double min = Double.POSITIVE_INFINITY;
		System.out.println("Expanding Min Node " + currentNode.name + ": Alpha = " + currentNode.alpha + ", Beta: " + currentNode.beta);
		for (Node child : currentNode.children){
			if (currentNode.alpha >= currentNode.beta){
				System.out.println("pruning expansion of Node " + child.name + " because it cannot make beta lower.");
				if (currentNode.children.indexOf(child) == currentNode.children.size())
					return min;
				else 
					continue;
			}
			child.alpha = currentNode.alpha;
			child.beta = currentNode.beta;
			double temp = max_value(child);
			if (temp <= min) {
				min = temp;
				if (temp <= currentNode.beta)
					currentNode.beta = temp;
				//				System.out.println("Min Node " + currentNode.name + " updated, value is now: " + min);
				//				System.out.println("Alpha: " + currentNode.alpha + ", Beta: " + currentNode.beta);
			}
		}
		return min;
	}

	public void printAlphaBetaValues(){
		System.out.println("\nFinal Alpha Beta Values for each node:");
		System.out.println("Node A0 - Alpha: " + gameTree.get("A0").alpha + ", Beta: " + gameTree.get("A0").beta);
		System.out.println("Node B0 - Alpha: " + gameTree.get("B0").alpha + ", Beta: " + gameTree.get("B0").beta);
		System.out.println("Node B1 - Alpha: " + gameTree.get("B1").alpha + ", Beta: " + gameTree.get("B1").beta);
		System.out.println("Node C0 - Alpha: " + gameTree.get("C0").alpha + ", Beta: " + gameTree.get("C0").beta);
		System.out.println("Node C1 - Alpha: " + gameTree.get("C1").alpha + ", Beta: " + gameTree.get("C1").beta);
		System.out.println("Node C2 - Alpha: " + gameTree.get("C2").alpha + ", Beta: " + gameTree.get("C2").beta);
		System.out.println("Node C3 - Alpha: " + gameTree.get("C3").alpha + ", Beta: " + gameTree.get("C3").beta);
	
		System.out.println("Node D0 - Alpha: " + gameTree.get("D0").alpha + ", Beta: " + gameTree.get("D0").beta);
		System.out.println("Node D1 - Alpha: " + gameTree.get("D1").alpha + ", Beta: " + gameTree.get("D1").beta);
		System.out.println("Node D2 - Alpha: " + gameTree.get("D2").alpha + ", Beta: " + gameTree.get("D2").beta);
		System.out.println("Node D3 - Alpha: " + gameTree.get("D3").alpha + ", Beta: " + gameTree.get("D3").beta);
		System.out.println("Node D5 - Alpha: " + gameTree.get("D5").alpha + ", Beta: " + gameTree.get("D5").beta);
		System.out.println("Node D7 - Alpha: " + gameTree.get("D7").alpha + ", Beta: " + gameTree.get("D7").beta);

	}

	public static void main(String arg[]) throws IOException{
		new AlphaBeta();
	}
}