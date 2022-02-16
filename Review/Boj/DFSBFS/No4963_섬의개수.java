package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No4963_섬의개수 {
	
	static int w, h;
	static int[][] map;
	static Queue<Point> q;
	static boolean[][] chk;
	
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		
		while (!(str = br.readLine()).equals("0 0")) {
			
			StringTokenizer st = new StringTokenizer(str);
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			map = new int[h][w];
			chk = new boolean[h][w];
			
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int islandCnt = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 0 || chk[i][j]) continue;
					bfs(i, j);
					islandCnt++;
				}
			}
			
			System.out.println(islandCnt);
			
		}

	}
	
	static void bfs(int x, int y) {
		q = new LinkedList<>();
		q.offer(new Point(x, y));
		chk[x][y] = true;
		
		while (!q.isEmpty()) {
			Point cur = q.poll();
			
			for (int i = 0; i < 8; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(ChkValid(nx, ny) && map[nx][ny] == 1 && !chk[nx][ny]) {
					q.offer(new Point(nx, ny));
					chk[nx][ny] = true;
				}
			}
		}
	}
	
	static boolean ChkValid(int x, int y) {
		if (x < 0 || x >= h || y < 0 || y >= w) return false;
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
