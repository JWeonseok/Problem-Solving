package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No17144_미세먼지안녕 {
	
	static int R, C, T, answer = 0;
	static int up = -1, down = -1;
	static int[][] map;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					if(up == -1) up = i;
					else down = i;
				}
			}
		}
		
		for (int i = 0; i < T; i++) {
			spreadDust();
			airCleaner();
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				answer += map[i][j];
			}
		}
		
		System.out.println(answer + 2);

	}
	
	static void airCleaner() {
		int nx = up;
		int ny = 0;
		int dist = 0;
		while(true) {
			nx += dx[dist];
			ny += dy[dist];
			if(!chkValid(nx, ny) || nx > up) {
				nx -= dx[dist];
				ny -= dy[dist];
				dist = (dist + 1) % 4;
				continue;
			}
			if(nx == up && ny == 0) {
				map[nx - dx[dist]][ny - dy[dist]] = 0;
				break;				
			}
			
			map[nx - dx[dist]][ny - dy[dist]] = map[nx][ny];
		}
		
		map[up][0] = -1;
		
		nx = down;
		ny = 0;
		dist = 0;
		
		while(true) {
			nx -= dx[dist];
			ny += dy[dist];
			if(!chkValid(nx, ny) || nx < down) {
				nx += dx[dist];
				ny -= dy[dist];
				dist = (dist + 1) % 4;
				continue;
			}
			if(nx == down && ny == 0) {
				map[nx + dx[dist]][ny - dy[dist]] = 0;
				break;				
			}
			
			map[nx + dx[dist]][ny - dy[dist]] = map[nx][ny];
		}
		
		map[down][0] = -1;
	}
	
	static void spreadDust() {
		Queue<Point> q = new LinkedList<>();
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == 0 || map[i][j] == -1) continue;
				q.offer(new Point(i, j, map[i][j]));
			}
		}
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			int cnt = 0;
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(chkValid(nx, ny) && map[nx][ny] != -1) {
					map[nx][ny] += cur.val / 5;
					cnt++;
				}
			}
			
			map[cur.x][cur.y] -= (cur.val / 5) * cnt;
		}
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= R || y < 0 || y >= C) return false;
		return true;
	}
	
	static class Point {
		int x, y, val;

		public Point(int x, int y, int val) {
			super();
			this.x = x;
			this.y = y;
			this.val = val;
		}
	}

}
