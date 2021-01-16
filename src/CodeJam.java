import java.math.*;
import java.util.*;


public class CodeJam {
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		//for(long counter = 1000000000; counter >1; counter--){

		while (sc.hasNext()) {
			long num = sc.nextLong();
			double maxfactor = Math.ceil(Math.sqrt((double)num));
			HashSet<Long> factors = new HashSet<>();
			
			for (long i = 2; i <= maxfactor; i++) {
				if (num % i == 0) {
					factors.add(i);
					factors.add(num / i);
				}
			}
			
			//add up all factors
			long totalfactors = 1; //everthing is divisible by 1
			for (Long factor : factors) {
				totalfactors += factor;
			}
			
			if (totalfactors == num) {
				System.out.println(num + " perfect");
			} else if (Math.abs(totalfactors - num) <= 2) {
				System.out.println(num + " almost perfect");
			} else {
				System.out.println(num + " not perfect");
			}
		}
	}

}