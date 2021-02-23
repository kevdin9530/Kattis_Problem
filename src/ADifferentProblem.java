import java.util.Scanner;

/*
 * Author:Kevin Hoang Dinh
 * Code test source: https://open.kattis.com/problems/different
 * 
 * Comment: Simple code test. Mostly let the coder learn about different size of 
 * each data type. Allow user to learn a bit about using input reader, data type conversion.
 * 
 * 
 */
public class ADifferentProblem {

	public ADifferentProblem() {
	}
	
	public long different(long one, long two) {
		return Math.abs(one-two);
	}

	private void run() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			long one = Long.parseLong(sc.next());
			long two = Long.parseLong(sc.next());
			System.out.println(different(one,two));
		}
		sc.close();
	}

	public static void main(String[] args) {
		ADifferentProblem adp = new ADifferentProblem();
		adp.run();
	}
}
