import java.util.HashSet;
import java.util.Scanner;

public class AlmostPerfect {

	// Find divisors and calculate their sum
	// Faster way is to find the maximum factor of the number which is
	// around squareroot of that number. This will decrease the time complexity
	public int calcDivisors(long p) {
		//HashSet doesn't allow duplicate which is perfect for this case
		//since we roundup the factor, we might get duplicate
		HashSet<Long> divisors = new HashSet<Long>();
		
		//
		double maxfactor = Math.ceil(Math.sqrt((double) p));
		for (long i = 2; i <= maxfactor; i++) {
			if (p % i == 0) {
				divisors.add(i);
				divisors.add(p / i);
			}
		}
		//Since everything is divisible with 1
		int sum = 1; 
		for (long divisor : divisors)
			sum += divisor;
		return sum;
	}

	public String isPerfect(long p) {
		int sum = calcDivisors(p);
		if (sum == p)
			return p + " perfect";
		if (sum <= p + 2 && sum >= p - 2)
			return p + " almost perfect";
		return p + " not perfect";
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLong()) {
			long p = sc.nextLong();
			System.out.println(isPerfect(p));
		}
		sc.close();

	}

	public static void main(String[] args) {
		AlmostPerfect ap = new AlmostPerfect();
		ap.run();
	}

}
