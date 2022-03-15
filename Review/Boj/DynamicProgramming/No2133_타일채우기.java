package rev.dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No2133_타일채우기 {
	
	static int N;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		if (N % 2 == 1) {
			System.out.println(0);
			return;
		}
		
		dp = new int[N + 1];
		dp[0] = 1;
		dp[2] = 3;
		
		for (int i = 4; i <= N; i += 2) {
			for (int j = 0; j <= i - 4; j++) {
				dp[i] += dp[j] * 2;
			}
			dp[i] += dp[i - 2] * 3;
		}
		
		System.out.println(dp[N]);

	}

}
