/*	Author:Kevin Hoang Dinh
 * 	Testcode: https://open.kattis.com/problems/watersheds
 *	Not solved yet. Bad algorith, test case 
 */

import java.util.ArrayList;
import java.util.Scanner;



public class Watersheds {
	class theFlow {
		int differences, x, y;

		public theFlow(int differences, int x, int y) {
			this.differences = differences;
			this.x = x;
			this.y = y;
		}

	}

	// ASCII for a is 97
	int T, H, W;
	int character = 97;
	int[][] altitudes;
	char[][] flow;

	public void readInput() {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int i = 1;
		while (i <= T) {
			H = sc.nextInt();
			W = sc.nextInt();
			altitudes = new int[H][W];
			for (int j = 0; j < H; j++)
				for (int k = 0; k < W; k++)
					altitudes[j][k] = sc.nextInt();

			// Do something about the
			drawFlow();
			
			System.out.println("Case #"+i+":");
			for (int k = 0; k < H; k++) {
				for (int j = 0; j < W; j++) {
					System.out.print(Character.toString(flow[k][j])+ " ");
				}
				System.out.println();
			}
			i++;
			character = 97;
		}
		
		sc.close();
	}

	public void findNeighbor(int x, int y) {
		ArrayList<theFlow> tflow = new ArrayList<theFlow>();
		// check north
		if (!(y - 1 < 0)) {
			tflow.add(new theFlow(altitudes[x][y - 1], x, y - 1));
		}
		// check west
		if (!(x - 1 < 0)) {
			tflow.add(new theFlow(altitudes[x - 1][y], x - 1, y));
		}
		// check east
		if (!(x + 1 >= H)) {
			tflow.add(new theFlow(altitudes[x + 1][y], x + 1, y));
		}
		// check south
		if (!(y + 1 >= W)) {
			tflow.add(new theFlow(altitudes[x][y + 1], x, y + 1));
		}
		int compare = 99;
		int index = 0;
		// find the lowest altitudes differences
		for (int i = 0; i < tflow.size(); i++) {
			if (compare > tflow.get(i).differences) {
				compare = tflow.get(i).differences;
				index = i;
			}
		}

		if (compare < altitudes[x][y]) {
			flow[tflow.get(index).x][tflow.get(index).y] = flow[x][y];
			findNeighbor(tflow.get(index).x,tflow.get(index).y);
		}
	}

	public void findNeighbor2(int x, int y) {
		ArrayList<theFlow> tflow = new ArrayList<theFlow>();
		// check north
		if (!(y - 1 < 0)) {
			tflow.add(new theFlow(altitudes[x][y - 1], x, y - 1));
		}
		// check west
		if (!(x - 1 < 0)) {
			tflow.add(new theFlow(altitudes[x - 1][y], x - 1, y));
		}
		// check east
		if (!(x + 1 >= H)) {
			tflow.add(new theFlow(altitudes[x + 1][y], x + 1, y));
		}
		// check south
		if (!(y + 1 >= W)) {
			tflow.add(new theFlow(altitudes[x][y + 1], x, y + 1));
		}
		int compare = 99;
		int index = 0;
		// find the lowest altitudes differences
		for (int i = 0; i < tflow.size(); i++) {
			if (compare > tflow.get(i).differences) {
				compare = tflow.get(i).differences;
				index = i;
			}
		}

		if (compare < altitudes[x][y]) {
			if(flow[tflow.get(index).x][tflow.get(index).y] < 97) {
				findNeighbor2(tflow.get(index).x, tflow.get(index).y);
				flow[x][y]=flow[tflow.get(index).x][tflow.get(index).y];
			}
			else
				flow[x][y]=flow[tflow.get(index).x][tflow.get(index).y];
		} else {
			character++;
			flow[x][y]=(char)character;
		}
	}

	public void drawFlow() {
		flow = new char[H][W];
		flow[0][0] = 'a';

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if ((int) flow[i][j] >= 97) {
					findNeighbor(i, j);
				} else {
					findNeighbor2(i, j);
				}
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Watersheds w = new Watersheds();
		w.readInput();
	}

}
