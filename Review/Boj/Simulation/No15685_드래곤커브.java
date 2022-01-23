package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class No15685_드래곤커브 {
	
	static int N, answer = 0;
	static int ex = -1, ey = -1;
	static int[][] map;
	static LinkedList<Point> q;
	
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[101][101];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			q = new LinkedList<>();
			ex = x + dx[d];
			ey = y + dy[d];
			
			q.offer(new Point(x, y));
			if(chkValid(ex, ey)) q.offer(new Point(ex, ey));
			
			for (int j = 0; j < g; j++) {
				dragonCurve();
			}
			
			while(!q.isEmpty()) {
				Point cur = q.poll();
				map[cur.y][cur.x] = i;
			}
		}
		cntSquare();
		System.out.println(answer);

	}
	
	static void cntSquare() {
		for (int i = 1; i < 101; i++) {
			for (int j = 1; j < 101; j++) {
				if(map[i][j] == 0) continue;
				
				int nx = j + 1;
				int ny = i + 1;
				
				if(chkValid(nx, ny) && (map[i][nx] != 0 && map[ny][j] != 0 && map[ny][nx] != 0)) answer++;
			}
		}
	}
	
	static void dragonCurve() {
		LinkedList<Point> newList = new LinkedList<>();
		int tmpx = -1, tmpy = -1;
		for(Point p : q) {
			if(p.x == ex && p.y == ey) continue;
			int nx = ey - p.y + ex;
			int ny = p.x - ex + ey;
			
			if(chkValid(nx, ny)) {
				if(tmpx == -1 && tmpy == -1) {
					tmpx = nx;
					tmpy = ny;
				}
				newList.add(new Point(nx, ny));
			}
		}
		q.addAll(newList);
		ex = tmpx;
		ey = tmpy;
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x > 100 || y < 0 || y > 100) return false;
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
