import java.util.Scanner;

/*
 * Author:Kevin Hoang Dinh
 * Code test source: https://open.kattis.com/problems/different
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
