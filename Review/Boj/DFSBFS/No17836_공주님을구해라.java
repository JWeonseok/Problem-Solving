package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No17836_공주님을구해라 {
	
	static int N, M, T;
	static int[][] map;
	static boolean[][][] chk;
	static Queue<Point> q;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		chk = new boolean[2][N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		q = new LinkedList<>();
		q.offer(new Point(0, 0, false));
		chk[0][0][0] = true;
		chk[1][0][0] = true;
		
		int cnt = 0;
		
		while (!q.isEmpty()) {
			int size = q.size();
			
			if (cnt > T) {
				System.out.println("Fail");
				return;
			}
			
			for (int i = 0; i < size; i++) {
				Point cur = q.poll();
				
				if (cur.x == N - 1 && cur.y == M - 1) {
					System.out.println(cnt);
					return;
				}
				
				for (int j = 0; j < 4; j++) {
					int nx = cur.x + dx[j];
					int ny = cur.y + dy[j];
					
					if (cur.gram) {
						if (ChkValid(nx, ny) && !chk[1][nx][ny]) {
							q.offer(new Point(nx, ny, true));
							chk[1][nx][ny] = true;
						}
					} else {
						if (ChkValid(nx, ny) && map[nx][ny] == 0 && !chk[0][nx][ny]) {
							q.offer(new Point(nx, ny, false));
							chk[0][nx][ny] = true;
						} else if (ChkValid(nx, ny) && map[nx][ny] == 2 && !chk[1][nx][ny]) {
							q.offer(new Point(nx, ny, true));
							chk[1][nx][ny] = true;
						}
					}
				}
			}			
			cnt++;
		}
		
		System.out.println("Fail");

	}
	
	static boolean ChkValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M) return false;
		return true;
	}
	
	static class Point {
		int x, y;
		boolean gram;

		public Point(int x, int y, boolean gram) {
			super();
			this.x = x;
			this.y = y;
			this.gram = gram;
		}
	}

}
