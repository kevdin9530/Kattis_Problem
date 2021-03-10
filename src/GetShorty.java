import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * Author:Kevin Hoang Dinh
 * Code test source: https://open.kattis.com/problems/getshorty 
 * Comment: This is another problem of finding the shortest path. 
 * Simple use Dijkstra's algorithm and modify it to find 
 * the maximum factor. In my case I use priority queue and hashtable
 * to solve it. 
 * Further explanation of my implementation: 
 * The Hashtable<Integer,ArrayList<Node>>
 * - Integer is the start destination and ArrayList<Node> is the list of
 * next destination and their factor. This adjacentList would usually be 
 * prefered as Edge according to Dijkstra's algorithm but I simply to
 * lazy to add another class.
 * As for priority Queue implementation, you can simple find it on 
 * Geeksforgeeks or any coding site. 
 * Extra comment: this is almost the same as one of the small project during 
 * my university 1st year in Data structure and algorithm course. 
 */

public class GetShorty {
	private Hashtable<Integer, ArrayList<Node>> adjacentList = new Hashtable<>();
	private int m, n;

	public class Node {
		private int node;
		private double factor;

		public Node(int node, double factor) {
			this.node = node;
			this.factor = factor;
		}
	}

	public GetShorty() {
	}

	public double dijkstrasAlgo(PriorityQueue<Node> prioQueue) {
		double[] factor = new double[n];
		// Fill all unvisited factor with 0.0
		Arrays.fill(factor, 0.0);
		// Add starting position with factor 1.0
		factor[0] = 1.0;
		prioQueue.add(new Node(0, 1.0));

		while (!prioQueue.isEmpty()) {
			Node topNode = prioQueue.poll();
			
			for (Node n : adjacentList.get(topNode.node)) {
				// if the multiplicative factor is higher then replace it
				// remove the current destination from the queue, combine and add 2 destination together
				// back to the queue
				if (topNode.factor * n.factor > factor[n.node]) {
					factor[n.node] = topNode.factor * n.factor;
					prioQueue.remove(n);
					prioQueue.add(new Node(n.node, factor[n.node]));
				}
			}
		}
		return factor[n - 1];

	}

	private void run() {
		Scanner sc = new Scanner(System.in);
		/*
		 * Write code according to the test
		 */
		// Need a comperator for the priority Queue to sort out
		Comparator<Node> comp = new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return Double.compare(o2.factor, o1.factor);
			}
		};
		while (sc.hasNext()) {
			double maxfactor = 0;
			n = Integer.parseInt(sc.next());
			m = Integer.parseInt(sc.next());
			// clear the list and create new prioQueue after every test case is done
			adjacentList.clear();
			PriorityQueue<Node> prioQueue = new PriorityQueue<>(comp);
			if (n == 0 && m == 0)
				break;
			for (int i = 0; i < m; i++) {
				int x = Integer.parseInt(sc.next());
				int y = Integer.parseInt(sc.next());
				double factor = Double.parseDouble(sc.next());
				// Add route both forward and backward
				if (!adjacentList.containsKey(x)) {
					ArrayList<Node> newList = new ArrayList<Node>();
					newList.add(new Node(y, factor));
					adjacentList.put(x, newList);
				} else {
					adjacentList.get(x).add(new Node(y, factor));
				}
				if (!adjacentList.containsKey(y)) {
					ArrayList<Node> newList = new ArrayList<Node>();
					newList.add(new Node(x, factor));
					adjacentList.put(y, newList);
				} else {
					adjacentList.get(y).add(new Node(x, factor));
				}
			}
			Locale currentLocale = Locale.getDefault();
			DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
			otherSymbols.setDecimalSeparator('.');
			otherSymbols.setGroupingSeparator('.');
			DecimalFormat df = new DecimalFormat("#0.0000", otherSymbols);
			System.out.println(df.format(dijkstrasAlgo(prioQueue)));
		}
		sc.close();
	}

	public static void main(String[] args) {
		GetShorty template = new GetShorty();
		template.run();
	}

}
