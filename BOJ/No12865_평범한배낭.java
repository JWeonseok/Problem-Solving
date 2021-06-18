package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 210618 금 평범한 배낭
 * 
 * DP의 가장 대표적인 문제
 * DP를 처음 만날 때 배우는 knapsack problem의 문제이다.
 * 이차원 배열을 이용해서 가방에 담을 때 물건의 무게 조건이 충족이 될때
 * 가치를 계산해서 마지막으로 최적의 물건을 담은 가치를 구하는 대표적인 DP 문제이다.
 * */

public class No12865_평범한배낭 {
	
	static int N, K;
	static int[][] item, dp;

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		item = new int[N+1][2];
		dp = new int[N+1][K+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			item[i][0] = Integer.parseInt(st.nextToken());
			item[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if(item[i][0] <= j) {
					dp[i][j] = Math.max(dp[i-1][j-item[i][0]] + item[i][1], dp[i-1][j]);
				}else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[N][K]);

	}

}
