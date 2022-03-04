package rev.dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No11053_가장긴증가하는부분수열 {
	
	static int N;
	static int[] nums, dp;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		dp = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
			}
		}
		
		int answer = Integer.MIN_VALUE;
		for (Integer in : dp) answer = Math.max(answer, in);
		System.out.println(answer);

	}

}
