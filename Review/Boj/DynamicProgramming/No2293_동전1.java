package rev.dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No2293_동전1 {
	
	static int N, K;
	static int[] dp, coins;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		coins = new int[N];
		dp = new int[K + 1];
		
		for (int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		for (Integer in : coins) {
			if (in > K) continue;
			dp[in]++;
			for (int i = 0; i < K; i++) {
				if (i + in <= K) dp[i + in] += dp[i];
			}
		}
		
		System.out.println(dp[K]);

	}

}
