package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class No13706_제곱근 {
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BigInteger N = new BigInteger(br.readLine());
		
		BigInteger start = new BigInteger("1");
		BigInteger end = N;
		
		while (start.compareTo(end) <= 0) {
			BigInteger mid = start.add(end).divide(new BigInteger("2"));
			
			int res = mid.multiply(mid).compareTo(N);
			
			if (res < 0) start = mid.add(new BigInteger("1"));
			else if (res > 0) end = mid.subtract(new BigInteger("1"));
			else {
				System.out.println(mid);
				break;
			}
		}

	}

}
