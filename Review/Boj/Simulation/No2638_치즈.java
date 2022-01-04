package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No2638_치즈 {
	
	static int N, M, answer = 0, cheese = 0;
	static int[][] map;
	static Queue<Point> q;
	
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
				if(map[i][j] == 1) cheese++;
			}
		}
		
		while(cheese > 0) {
			
			makeBoundary();			
			q = new LinkedList<>();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] != 1) continue;
					
					int cnt = 0;
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						
						if(chkValid(nx, ny) && map[nx][ny] == -1) cnt++;
					}
					
					if(cnt >= 2) q.offer(new Point(i, j));
					
				}
			}
			
			cheese -= q.size();
			while(!q.isEmpty()) {
				Point melt = q.poll();
				map[melt.x][melt.y] = 0;
			}
			
			answer++;
		}
		
		System.out.println(answer);		

	}
	
	static void makeBoundary() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] chk = new boolean[N][M];
		q.offer(new Point(0, 0));
		map[0][0] = -1;
		chk[0][0] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(chkValid(nx, ny) && map[nx][ny] != 1 && !chk[nx][ny]) {
					q.offer(new Point(nx, ny));
					map[nx][ny] = -1;
					chk[nx][ny] = true;
				}
			}
		}
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= M) return false;
		return true;
	}
	
	static class Point{
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
