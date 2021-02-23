import java.util.Scanner;

/*
 * Author:Kevin Hoang Dinh
 * Code test source: https://open.kattis.com/problems/guess
 * 
 * Comment: Guess half value of the sum of min and max bound. Simple algorithm and can be found a lot on 
 * internet. Google Divide And Conquer Algorithm for more information. 
 * 
 * Extra note: Code can be better and more straight forward if written directly in main in a while loop
 * However i like to make a class and then run it in main instead of writing all code in main app
 */

public class GuessTheNumber {

	private int min = 1;
	private int max = 1000;
	private int guesschance = 0;
	private Scanner sc;

	public GuessTheNumber() {
	}

	public void check(String answer, int guess) {
		if(guesschance>=10)
			return;
		if (answer.equals("lower")) {
			max=guess;
			//System.out.println("total guess: "+ guesschance);
			guess = (min+max) / 2;
			System.out.println(guess);
			guesschance++;
			answer = sc.next();
			check(answer, guess);
			return;
		}

		if (answer.equals("higher")) {
			min=guess;
			//System.out.println("total guess: "+ guesschance);
			guess = (min+max+1) / 2;
			System.out.println(guess);
			guesschance++;
			answer = sc.next();
			check(answer, guess);
			return;
		}

		if (answer.equals("correct"))
			return;
	}

	public void run() {
		sc = new Scanner(System.in);
		int guess = 500;
		guesschance++;
		System.out.println(guess);
		String text = sc.next();
		check(text, guess);
		sc.close();
	}

	public static void main(String[] args) {
		GuessTheNumber guess = new GuessTheNumber();
		guess.run();
	}

}
