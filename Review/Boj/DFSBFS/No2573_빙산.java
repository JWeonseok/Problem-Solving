package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No2573_빙산 {
	
	static int N, M;
	static int[][] map;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int year = 0;
		while (true) {
			year++;
			MeltIce();
			int res = CntPiece();
			if (res != 1) {
				System.out.println(res > 0 ? year : 0);
				return;
			}	
		}

	}
	
	static int CntPiece() {
		int cnt = 0;
		boolean[][] chk = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0 || chk[i][j]) continue;
				
				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(i, j));
				chk[i][j] = true;
				
				while (!q.isEmpty()) {
					Point cur = q.poll();
					
					for (int k = 0; k < 4; k++) {
						int nx = cur.x + dx[k];
						int ny = cur.y + dy[k];
						
						if (ChkValid(nx, ny) && map[nx][ny] != 0 && !chk[nx][ny]) {
							q.offer(new Point(nx, ny));
							chk[nx][ny] = true;
						}
					}
				}
				cnt++;
			}
		}
		
		return cnt;
	}
	
	static void MeltIce() {
		int[][] meltInfo = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) continue;

				int cnt = 0;
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					
					if (ChkValid(nx, ny) && map[nx][ny] == 0) cnt++;
				}
				
				meltInfo[i][j] = cnt;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = Math.max(0, map[i][j] - meltInfo[i][j]);
			}
		}
	}
	
	static boolean ChkValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M) return false;
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
