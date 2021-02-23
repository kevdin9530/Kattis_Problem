import java.util.ArrayList;
import java.util.Scanner;

/*
 * Author:Kevin Hoang Dinh
 * Code test source: https://open.kattis.com/problems/8queens
 * 
 * Comment: For every time I find a queen, I must check if the queen can be there by using
 * an array list that includes invalid X, Y and diagonal move. If it's valid, i will update
 * the array list all the invalid place to put. My way of thinking might not be the best but 
 * that the only way I have come up so far. 
 * 
 * Extra note: the test also trick you about checking the total count of queens. If the queens count
 * isn't up to 8 then it's invalid. This took me like half an hour wondering what is the problem
 * with my code haha.
 */

public class EightQueens {
	
	private ArrayList<Integer> invalidX;
	private ArrayList<Integer> invalidY;
	private ArrayList<Integer> invalidXY;
	private ArrayList<Integer> invalidYX;
	boolean isValid=true;
	private int X=0;
	private int Y=0;
	private int queencount = 0;
	public EightQueens() {
	}
	
	public boolean check(char a) {
		if(a=='*') {
			queencount++;
			if(invalidX.contains(X))
				return false;
			else
				invalidX.add(X);
			if(invalidY.contains(Y))
				return false;
			else
				invalidY.add(Y);
			if(invalidXY.contains(Y-X))
				return false;
			else
				invalidXY.add(Y-X);
			if(invalidYX.contains((9-X-Y)))
				return false;
			else
				invalidYX.add((9-X-Y));
		}
		return true;
	}

	private void run() {
		Scanner sc = new Scanner(System.in);
		invalidX = new ArrayList<>();
		invalidY = new ArrayList<>();
		invalidXY = new ArrayList<>();
		invalidYX = new ArrayList<>();
		while(Y<8) {
			X=0;
			String line = sc.nextLine();
			while(X<8) {
				if(!check(line.toCharArray()[X])) {
					isValid=false;
					//System.out.println("Invalid at " + (X+1) + " " + (Y+1));
					break;
				}
				X++;
			}
			if(!isValid)
				break;
			Y++;
		}
		if(isValid && queencount ==8)
			System.out.println("valid");
		else
			System.out.println("invalid");
		sc.close();
	}
	
	public static void main(String[] args) {
		EightQueens eq = new EightQueens();
		eq.run();
	}
	
}
