package rev.simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No3709_레이저는어디로 {
	
	static int T, N, R, rx, ry;
	static int[][] map;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			map = new int[N + 2][N + 2];
			
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				map[x][y] = 1;
			}
			
			st = new StringTokenizer(br.readLine());
			rx = Integer.parseInt(st.nextToken());
			ry = Integer.parseInt(st.nextToken());
			
			int dir = FindDir(rx, ry);
			int nx = rx + dx[dir];
			int ny = ry + dy[dir];
			while (true) {
				if (!ChkBoundary(nx, ny)) {
					System.out.println(nx + " " + ny);
					break;
				}
				
				if (map[nx][ny] == 1) {
					dir = (dir + 1) % 4;
					nx += dx[dir];
					ny += dy[dir];
					continue;
				}
				
				nx += dx[dir];
				ny += dy[dir];
			}
		}
		
	}
	
	static boolean ChkBoundary(int x, int y) {
		if (x < 1 || x > N || y < 1 || y > N) return false;
		return true;
	}
	
	static int FindDir(int rx, int ry) {
		if (rx == 0) return 2;
		else if (rx == N + 1) return 0;
		else if (ry == 0) return 1;
		else if (ry == N + 1) return 3;
		return -1;
	}

}
