package rev.simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No1937_욕심쟁이판다 {
	
	static int N, answer = Integer.MIN_VALUE;
	static int[][] map;
	static int[][] visited;

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer = Math.max(answer, dfs(i, j));
			}
		}
		
		System.out.println(answer);

	}
	
	static int dfs(int x, int y) {
		if (visited[x][y] != 0) return visited[x][y];
		
		visited[x][y] = 1;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (ChkValid(nx, ny) && map[x][y] < map[nx][ny]) {
				visited[x][y] = Math.max(visited[x][y], dfs(nx, ny) + 1);
				dfs(nx, ny);
			}
		}
		
		return visited[x][y];
	}
	
	static boolean ChkValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) return false;
		return true;
	}

}
