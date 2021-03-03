import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * Author:Kevin Hoang Dinh
 * Code test source: https://open.kattis.com/problems/joinstrings
 * Comment: 
 * This isn't the faster run but the code should work just fine. 
 * The problem isn't just about concatenation, it's also included time complexity.
 * To increase input/output speed. I borrowed input class FastRead from
 * GeekForGeeks. Source: https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/ 
 * As for the output I use output stream. 
 * To decrease the time, I also use StringBuilder because it require less time to join string.
 * As for the algorithm, I use a HashMap to store index a as the key and b as a value in an ArrayList.
 * Using the relation of key and value of hashmap I can do a recursive print to get the result. 
 */

public class JoinString {

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}

	private String[] text;
	private HashMap<Integer, ArrayList<Integer>> pointer = new HashMap<Integer, ArrayList<Integer>>();
	private int lastA;
	private StringBuilder sb = new StringBuilder();
	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

	public JoinString() {
	}

	public void printRecursive(int index) throws IOException {
		output.write(text[index]);
		if (pointer.get(index) != null)
			for (int i = 0; i < pointer.get(index).size(); i++)
				printRecursive(pointer.get(index).get(i));
	}

	private void run() {
		FastReader s = new FastReader();
		/*
		 * Write code according to the test
		 */
		int stringline = Integer.parseInt(s.next());
		text = new String[stringline];
		for (int i = 0; i < stringline; i++) {
			text[i] = s.next();
		}

		for (int i = 0; i < stringline - 1; i++) {
			int a = Integer.parseInt(s.next());
			int b = Integer.parseInt(s.next());
			a -= 1;
			b -= 1;
			if (!pointer.containsKey(a)) {
				ArrayList<Integer> newlist = new ArrayList<>();
				newlist.add(b);
				pointer.put(a, newlist);
			} else {
				pointer.get(a).add(b);
			}
			// joinString(a,b);
			lastA = a;
		}

		try {
			printRecursive(lastA);
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		JoinString template = new JoinString();
		template.run();
	}

}
