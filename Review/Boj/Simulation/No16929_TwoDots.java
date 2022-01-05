package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No16929_TwoDots {
	
	static int N, M;
	static int sx = 0, sy = 0;
	static boolean answer;
	static char[][] map;
	static boolean[][] chk;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		chk = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(answer) {
					System.out.println("Yes");
					return;
				}
				sx = i;
				sy = j;
				chk = new boolean[N][M];
				chk[i][j] = true;
				dfs(i, j, 1);
			}
		}
		
		System.out.println("No");
	}
	
	static void dfs(int x, int y, int cnt) {
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(chkValid(nx, ny) && map[nx][ny] == map[sx][sy]) {
				if(cnt >= 4 && sx == nx && sy == ny) {
					answer = true;
					return;
				}else if(!chk[nx][ny]) {
					chk[nx][ny] = true;
					dfs(nx, ny, cnt + 1);
				}
			}
		}
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= M) return false;
		return true;
	}
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
