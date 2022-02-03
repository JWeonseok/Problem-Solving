package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No6087_레이저통신 {
	
	static int W, H, answer = Integer.MAX_VALUE;
	static char[][] map;
	static boolean[][] chk;
	static Queue<Point> q;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new char[H][W];
		chk = new boolean[H][W];
		q = new LinkedList<>();
		
		for (int i = 0; i < H; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < W; j++) {
				if(map[i][j] == 'C' && q.isEmpty()) {
					q.offer(new Point(i, j, 0, 0));
					q.offer(new Point(i, j, 1, 0));
					q.offer(new Point(i, j, 2, 0));
					q.offer(new Point(i, j, 3, 0));
					
					chk[i][j] = true;
				}
			}
		}
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			int nx = cur.x;
			int ny = cur.y;
			
			while(true) {
				nx += dx[cur.dir];
				ny += dy[cur.dir];
				
				if(!chkValid(nx, ny) || map[nx][ny] == '*') break;				
				else if(!chk[nx][ny]) {
					if(map[nx][ny] == 'C') answer = Math.min(answer, cur.cnt);
					else if(map[nx][ny] == '.') {
						if(cur.dir == 0 || cur.dir == 2) {
							q.offer(new Point(nx, ny, 1, cur.cnt + 1));
							q.offer(new Point(nx, ny, 3, cur.cnt + 1));
						}else if(cur.dir == 1 || cur.dir == 3) {
							q.offer(new Point(nx, ny, 0, cur.cnt + 1));
							q.offer(new Point(nx, ny, 2, cur.cnt + 1));
						}
						chk[nx][ny] = true;
					}
				}
			}
		}

		System.out.println(answer);
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= H || y < 0 || y >= W) return false;
		return true;
	}
	
	static class Point {
		int x, y, dir, cnt;

		public Point(int x, int y, int dir, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}
	}

}
