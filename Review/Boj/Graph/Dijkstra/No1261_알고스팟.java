package rev.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No1261_알고스팟 {
	
	static int N, M, answer = Integer.MAX_VALUE;
	static int[][] map, distance;
	static Queue<Point> q;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());		
		
		map = new int[N][M];
		distance = new int[N][M];
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = row.charAt(j) - '0';
			}
			Arrays.fill(distance[i], Integer.MAX_VALUE);
		}
		
		q = new LinkedList<>();
		q.offer(new Point(0, 0, 0));
		distance[0][0] = 0;
		
		while (!q.isEmpty()) {
			Point cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (ChkValid(nx, ny) && cur.cnt + map[nx][ny] < distance[nx][ny]) {
					q.offer(new Point(nx, ny, cur.cnt + map[nx][ny]));
					distance[nx][ny] = cur.cnt + map[nx][ny];
				}
			}
		}
		
		System.out.println(distance[N - 1][M - 1]);

	}
	
	static boolean ChkValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M) return false;
		return true;
	}
	
	static class Point {
		int x, y, cnt;

		public Point(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

}
