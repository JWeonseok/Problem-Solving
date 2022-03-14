package rev.gametheory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class No9657_돌게임3 {
	
	static int N;
	static boolean[] dp;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		if (N == 1) {
			System.out.println("SK");
			return;
		}
		
		dp = new boolean[N + 1];
		Arrays.fill(dp, true);		
		dp[0] = false;
		dp[2] = false;
		
		for (int i = 5; i <= N; i++) {
			if (dp[i - 1] && dp[i - 3] && dp[i - 4]) dp[i] = false;
		}
		
		System.out.println(dp[N] ? "SK" : "CY");

	}

}
