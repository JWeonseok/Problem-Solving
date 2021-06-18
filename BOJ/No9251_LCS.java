package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 210618 금 LCS
 * 
 * LCS(최장 공통 부분 수열)을 구하는 문제이다.
 * 처음에는 LIS처럼 일차원 배열을 통해 DP적으로 접근하다가 해결하지 못했다.
 * 결론은 이차원 배열을 통해서 겹치는 문자의 수를 체킹함으로 문제를 해결할 수 있었다.
 * */

public class No9251_LCS {
	
	static String str1, str2;
	static int N, M;
	static int[][] dp;
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		str1 = br.readLine();
		str2 = br.readLine();
		
		N = str1.length();
		M = str2.length();
		
		dp = new int[M + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if(str1.charAt(i-1) == str2.charAt(j-1)) dp[j][i] = dp[j-1][i-1] + 1;
				else dp[j][i] = Math.max(dp[j-1][i], dp[j][i-1]);
			}
		}
		System.out.println(dp[M][N]);
	}

}
