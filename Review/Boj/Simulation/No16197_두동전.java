package rev.simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No16197_두동전 {
	
	static int N, M, c1x = -1, c1y = -1, c2x, c2y;
	static char[][] map;
	static Queue<Coins> q;
	static boolean[][][][] visited;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'o') { 
					if (c1x == -1 && c1y == -1) {
						c1x = i;
						c1y = j;
					} else {
						c2x = i;
						c2y = j;
					}
				}
			}
		}
		
		q = new LinkedList<>();
		visited = new boolean[N][M][N][M];
		q.offer(new Coins(c1x, c1y, c2x, c2y));
		visited[c1x][c1y][c2x][c2y] = true;
		
		int cnt = 0;
		while (!q.isEmpty()) {
			if (cnt > 10) break;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Coins cur = q.poll();
				
				for (int j = 0; j < 4; j++) {
					int n1x = cur.c1x + dx[j];
					int n1y = cur.c1y + dy[j];
					int n2x = cur.c2x + dx[j];
					int n2y = cur.c2y + dy[j];
					
					if (!ChkBoundary(n1x, n1y) && !ChkBoundary(n2x, n2y)) continue;
					if (!ChkBoundary(n1x, n1y) || !ChkBoundary(n2x, n2y)) {
						System.out.println(cnt + 1);
						return;
					}
					
					if (map[n1x][n1y] == '#') {
						n1x -= dx[j];
						n1y -= dy[j];
					}
					if (map[n2x][n2y] == '#') {
						n2x -= dx[j];
						n2y -= dy[j];
					}
					
					if (!visited[n1x][n1y][n2x][n2y]) {
						visited[n1x][n1y][n2x][n2y] = true;
						q.offer(new Coins(n1x, n1y, n2x, n2y));
					}
				}
			}
			cnt++;
		}
		
		System.out.println(-1);

	}
	
	static boolean ChkBoundary(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M) return false;
		return true;
	}
	
	static class Coins {
		int c1x, c1y, c2x, c2y;
		
		public Coins(int c1x, int c1y, int c2x, int c2y) {
			super();
			this.c1x = c1x;
			this.c1y = c1y;
			this.c2x = c2x;
			this.c2y = c2y;
		}
	}

}
