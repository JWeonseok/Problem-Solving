package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class No11559_PuyoPuyo {
	
	static int answer = 0;
	static char[][] map = new char[12][6];
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 12; i++) {
			String line = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		while(true) {
			LinkedList<Point> puyo = new LinkedList<>();
			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					if(map[i][j] == '.') continue;
					
					Queue<Point> q = new LinkedList<>();					
					boolean[][] chk = new boolean[12][6];
					q.offer(new Point(i, j));
					puyo.add(new Point(i, j));
					chk[i][j] = true;
					
					int cnt = 1;
					while(!q.isEmpty()) {
						Point cur = q.poll();
						
						for (int k = 0; k < 4; k++) {
							int nx = cur.x + dx[k];
							int ny = cur.y + dy[k];
							
							if(chkValid(nx, ny) && map[nx][ny] == map[i][j] && !chk[nx][ny]) {
								q.offer(new Point(nx, ny));
								puyo.add(new Point(nx, ny));
								chk[nx][ny] = true;
								cnt++;
							}
						}
					}
					
					if(cnt < 4) {
						for (int k = 0; k < cnt; k++) {
							puyo.removeLast();
						}
					}
				}
			}
			
			if(puyo.size() == 0) break;			
			for(Point p : puyo) {
				map[p.x][p.y] = '.';
			}
			downPuyo();
			answer++;
		}
		System.out.println(answer);

	}
	
	static void downPuyo() {
		for (int i = 0; i < 6; i++) {
			for (int j = 11; j >= 0; j--) {
				if(map[j][i] == '.') {
					if(!endPoint(j, i)) break;
					while(map[j][i] == '.') {
						for (int k = j; k > 0; k--) {
							map[k][i] = map[k - 1][i]; 
						}
						map[0][i] = '.';
					}
				}
			}
		}
	}
	
	static boolean endPoint(int x, int y) {
		for (int i = x; i >= 0; i--) {
			if(map[i][y] != '.') return true;
		}
		return false;
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= 12 || y < 0 || y >= 6) return false;
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
