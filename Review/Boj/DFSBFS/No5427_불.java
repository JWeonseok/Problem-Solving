package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No5427_불 {
	
	static int T, N, M;
	static char[][] map;
	static Queue<Point> q1, q2;
	static boolean[][] chk1, chk2;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		loop: for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());	
			M = Integer.parseInt(st.nextToken());		
			N = Integer.parseInt(st.nextToken());
			
			map = new char[N][M];
			q1 = new LinkedList<>();
			q2 = new LinkedList<>();
			chk1 = new boolean[N][M];
			chk2 = new boolean[N][M];
			
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < M; j++) {					
					if (map[i][j] == '*') {
						q1.offer(new Point(i, j));
						chk1[i][j] = true;						
					} else if (map[i][j] == '@') {
						q2.offer(new Point(i, j));
						chk2[i][j] = true;
						map[i][j] = '.';
					}
				}
			}
			
			int cnt = 0;
			while (!q2.isEmpty()) {
				cnt++;
				int size = q1.size();				
				for (int i = 0; i < size; i++) {
					Point cur = q1.poll();
					
					for (int j = 0; j < 4; j++) {
						int nx = cur.x + dx[j];
						int ny = cur.y + dy[j];
						
						if (ChkValid(nx, ny) && map[nx][ny] == '.' && !chk1[nx][ny]) {
							q1.offer(new Point(nx, ny));
							chk1[nx][ny] = true;
							map[nx][ny] = '*';
						}
					}
				}
				
				size = q2.size();				
				for (int i = 0; i < size; i++) {
					Point cur = q2.poll();
					for (int j = 0; j < 4; j++) {
						int nx = cur.x + dx[j];
						int ny = cur.y + dy[j];
						if (!ChkValid(nx, ny)) {
							System.out.println(cnt);			
							continue loop;
						} else if (map[nx][ny] == '.' && !chk2[nx][ny]) {
							q2.offer(new Point(nx, ny));
							chk2[nx][ny] = true;
						}
					}
				}
				
				
				
				
			}
			
			System.out.println("IMPOSSIBLE");
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
