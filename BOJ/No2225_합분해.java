package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No2225_합분해 {
	
	static int N, K;
	static int[][] dp;

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new int[N+1][K+1];
		
		Arrays.fill(dp[0], 1);
		dp[0][0] = 0;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000000;
			}
		}
		
		System.out.println(dp[N][K]);
	}

}
