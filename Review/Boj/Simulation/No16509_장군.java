package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No16509_장군 {
	
	static int kingPosx = -1, kingPosy = -1;
	static Queue<Point> q;
	static boolean[][] chk;
	static int[][] dx = {{-1, -1, -1}, {0, -1, -1}, {0, 1, 1}
						,{1, 1, 1}, {1, 1, 1}, {0, 1, 1}
						,{0, -1, -1}, {-1, -1, -1}};
	static int[][] dy = {{0, 1, 1}, {1, 1, 1}, {1, 1, 1}
						,{0, 1, 1}, {0, -1, -1}, {-1, -1, -1}
						,{-1, -1, -1}, {0, -1, -1}};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		q = new LinkedList<>();
		chk = new boolean[10][9];
		
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		q.offer(new Point(x, y));
		chk[x][y] = true;
		
		st = new StringTokenizer(br.readLine());
		kingPosx = Integer.parseInt(st.nextToken());
		kingPosy = Integer.parseInt(st.nextToken());
		
		int moveCnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point cur = q.poll();
				
				if(cur.x == kingPosx && cur.y == kingPosy) {
					System.out.println(moveCnt);
					return;
				}
				
				loop: for (int j = 0; j < 8; j++) {
					int nx = cur.x;
					int ny = cur.y;
					
					for (int k = 0; k < 2; k++) {
						nx += dx[j][k];
						ny += dy[j][k];
						if(nx == kingPosx && ny == kingPosy) continue loop;
					}
					
					nx += dx[j][2];
					ny += dy[j][2];
					
					if(chkValid(nx, ny) && !chk[nx][ny]) {
						q.offer(new Point(nx, ny));
						chk[nx][ny] = true;
					}
				}
			}
			moveCnt++;
		}
		
		System.out.println(-1);			

	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= 10 || y < 0 || y >= 9) return false;
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
