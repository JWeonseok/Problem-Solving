package rev.dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No10422_괄호 {
	
	static int T, max = Integer.MIN_VALUE;
	static int[] L;
	static long[] dp;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		L = new int[T];
		for (int i = 0; i < T; i++) {
			L[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, L[i]);
		}
		
		dp = new long[max + 1];
		dp[0] = 1;
		dp[2] = 1;
		for (int i = 4; i <= max; i += 2) {
			for (int j = 0; j <= i - 2; j += 2) {
				dp[i] += dp[j] * dp[i - 2 - j];
				dp[i] %= 1000000007L;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (Integer in : L) sb.append(dp[in]).append('\n');
		
		System.out.println(sb);

	}

}
