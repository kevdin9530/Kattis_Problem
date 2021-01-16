
/*
 * Author:Kevin Hoang Dinh
 * Code test link: https://open.kattis.com/problems/succession
 * Test descripstion: 
 	The king in Utopia has died without an heir.  
 Now several nobles in the country claim the throne. 
The country law states that if the ruler has no heir, 
the person who is most related to the founder of the country should rule.
To determine who is most related we measure the amount of blood in the veins of a claimant 
that comes from the founder. A person gets half the blood from the father and the other half from the mother.
A child to the founder would have 1/2 royal blood, that child’s child with another parent 
who is not of royal lineage would have 1/4 royal blood, and so on. 
The person with the most blood from the founder is the one most related.

 */
import java.util.ArrayList;
import java.util.Scanner;

public class Succession {
	
	//Create a class to define family relationship
	class Family{
		String child;
		String parentA;
		String parentB;
		public Family(String child, String parentA, String parentB) {
			super();
			this.child = child;
			this.parentA = parentA;
			this.parentB = parentB;
		}		
	}
	private String king; //Keep track who is the king
	ArrayList<Family> fam = new ArrayList<Family>(); //A list to keep family relationship in check
	ArrayList<String> candidates = new ArrayList<String>();
	//Method to read input
	public void readInput() {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		king = sc.next();
		//Loop to read family tree and add them to the list
		while(N-->0) {
			fam.add(new Family(sc.next(),sc.next(),sc.next()));
		}
		//Loop to add all candidate to list
		while(M-->0) {
			candidates.add(sc.next());
		}
		sc.close();
	}
	//A recursive function to calculate blood relation to the king
	public double relationCalc(String name) {
		if(name.equals(null))//In case unknow parent then they have 0 relation to the king
			return 0;
		if(name.equals(king))//The king is pure blood 
			return 1;
		//Loop the check candidate in family tree
		for(Family family:fam) {
			//if the name belongs in a family tree, calculate him/her blood according to their parents
			if(family.child.equals(name)) {
				return (relationCalc(family.parentA) +relationCalc(family.parentB))/2;
			}
		}//if doesn't belong to any family then him/her has 0 relation to the king
		return 0;
	}
	//Loop through candidate list and use recursive function to calculate their relation to the king
	public String findbestCandidate() {
		double mostRelated = -1;
		String successor = "";
		for(String candidate:candidates) {
			double relation = relationCalc(candidate);
			if(relation>mostRelated) {
				mostRelated = relation;
				successor = candidate;
			}
		}
		return successor;
	}
	
	//function to run the program
	public void run() {
		readInput();
		System.out.println(findbestCandidate());
	}
	
	public static void main(String[]args) {
		Succession s = new Succession();
		s.run();
	}
}
