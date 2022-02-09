package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No2186_문자판 {
	
	static int N, M, K, answer = 0;
	static char[] word;
	static char[][] map;
	static int[][][] dp;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		word = br.readLine().toCharArray();
		dp = new int[word.length + 1][N][M];
		
		for (int i = 0; i < word.length; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != word[0]) continue;
				
				answer += dfs(i, j, 0);
			}
		}
		
		System.out.println(answer);

	}
	
	static int dfs(int x, int y, int cnt) {
		if (cnt == word.length - 1) return dp[cnt][x][y] = 1;		
		if (dp[cnt][x][y] != -1) return dp[cnt][x][y];

		dp[cnt][x][y] = 0;
		
		for (int i = 0; i < 4; i++) {
			int nx = x;
			int ny = y;
			
			for (int j = 0; j < K; j++) {
				nx += dx[i];
				ny += dy[i];
				
				if (ChkValid(nx, ny) && map[nx][ny] == word[cnt + 1]) {
					dp[cnt][x][y] += dfs(nx, ny, cnt + 1);
				}
			}
		}
		
		return dp[cnt][x][y];
	}
	
	static boolean ChkValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M) return false;
		return true;
	}

}
