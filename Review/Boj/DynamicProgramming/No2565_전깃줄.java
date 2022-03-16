package rev.dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No2565_전깃줄 {
	
	static int N;
	static int[] dp;
	static Line[] lines;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		lines = new Line[N];
		dp = new int[N];		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());		
			int Anum = Integer.parseInt(st.nextToken());
			int Bnum = Integer.parseInt(st.nextToken());
			lines[i] = new Line(Anum, Bnum);
		}
		
		Arrays.sort(lines);
		
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (lines[i].Bnum > lines[j].Bnum) dp[i] = Math.max(dp[i], dp[j] + 1);
			}
		}
		
		int max = Integer.MIN_VALUE;
		for (Integer in : dp) max = Math.max(max, in);
		
		System.out.println(N - max);

	}
	
	static class Line implements Comparable<Line>{
		int Anum, Bnum;

		public Line(int anum, int bnum) {
			super();
			Anum = anum;
			Bnum = bnum;
		}

		@Override
		public int compareTo(Line o) {
			return this.Anum - o.Anum;
		}
	}

}
