package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class No2151_거울설치 {
	
	static int N, answer = Integer.MAX_VALUE, sx = -1, sy = -1;
	static boolean[][] chk;
	static char[][] map;
	static Queue<Point> q;

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		chk = new boolean[N][N];
		q = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if(map[i][j] == '#' && q.isEmpty()) {
					q.offer(new Point(i, j, 0, 0));
					q.offer(new Point(i, j, 1, 0));
					q.offer(new Point(i, j, 2, 0));
					q.offer(new Point(i, j, 3, 0));
					
					sx = i;
					sy = j;
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
				
				if(chkValid(nx, ny) && map[nx][ny] == '#' && (sx != nx || sy != ny)) answer = Math.min(answer, cur.cnt);
				
				if(chkValid(nx, ny) && map[nx][ny] == '!' && !chk[nx][ny]) {
					if(cur.dir == 0 || cur.dir == 2) {
						q.offer(new Point(nx, ny, 1, cur.cnt + 1));
						q.offer(new Point(nx, ny, 3, cur.cnt + 1));
					}else if(cur.dir == 1 || cur.dir == 3) {
						q.offer(new Point(nx, ny, 0, cur.cnt + 1));
						q.offer(new Point(nx, ny, 2, cur.cnt + 1));
					}
					chk[nx][ny] = true;
				}else if(!chkValid(nx, ny) || map[nx][ny] == '*') break;
			}
		}
		
		System.out.println(answer);

	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) return false;
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
