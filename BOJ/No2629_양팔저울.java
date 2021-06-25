package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No2629_양팔저울 {
	
	static int N, M;
	static int[] chu;
	static int[] balls;
	static boolean[][] dp;
	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		chu = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			chu[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		balls = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			balls[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new boolean[N+1][15001];
		
		dfs(0, 0);
		
		for (int i = 0; i < M; i++) {
			if(balls[i] > 15000) System.out.print("N ");
			else {
				if(dp[N][balls[i]]) System.out.print("Y ");
				else System.out.print("N ");				
			}
		}
		
	}
	
	static void dfs(int cnt, int weight) {
		if(dp[cnt][weight]) return;
		
		dp[cnt][weight] = true;
		
		if(cnt == N) return;
		
		dfs(cnt+1, weight + chu[cnt]);
		dfs(cnt+1, weight);
		dfs(cnt+1, Math.abs(weight - chu[cnt]));
	}

}
