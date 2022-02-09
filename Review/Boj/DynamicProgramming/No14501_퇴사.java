package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No14501_퇴사 {
	
	static int N, answer = Integer.MIN_VALUE;
	static int[][] table;
	static int[] dp;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		table = new int[2][N + 1];
		dp = new int[N + 2];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			table[0][i] = Integer.parseInt(st.nextToken());
			table[1][i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			dp[i] = Math.max(dp[i], dp[i - 1]);
			if (table[0][i] + i <= N + 1) dp[table[0][i] + i] = Math.max(dp[i] + table[1][i], dp[table[0][i] + i]);
			answer = Math.max(answer, dp[i]);
		}
		
		System.out.println(answer > dp[N + 1] ? answer : dp[N + 1]);

	}

}
