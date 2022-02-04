package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No2933_미네랄 {
	
	static int R, C, N, mark;
	static char[][] map;
	static int[][] markMap;
	static int[] sticks;

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		N = Integer.parseInt(br.readLine());
		sticks = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			sticks[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			Shot(i, sticks[i]);			
			Mark();
			Gravity();
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
		
		
	}
	
	static void Gravity() {
		int[] grav = new int[mark + 1];		
		Arrays.fill(grav, Integer.MAX_VALUE);
		
		for (int i = 0; i < C; i++) {
			int ind = R;
			int tmp = -1;
			for (int j = R - 1; j >= 0; j--) {
				if(markMap[j][i] != 0) {					
					if(tmp != markMap[j][i]) grav[markMap[j][i]] = Math.min(grav[markMap[j][i]], ind - j - 1);
					ind = j;
					tmp = markMap[j][i];
				}
			}
		}
				
		for (int i = 0; i < C; i++) {
			for (int j = R - 1; j >= 0; j--) {
				if(markMap[j][i] == 0 || grav[markMap[j][i]] == 0) continue;
				
				map[j + grav[markMap[j][i]]][i] = 'x';
				map[j][i] = '.';
			}
		}
	}
	
	static void Mark() {
		markMap = new int[R][C];
		mark = 0;
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == '.' || markMap[i][j] != 0) continue;
				
				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(i, j));
				markMap[i][j] = ++mark;
				
				while(!q.isEmpty()) {
					Point cur = q.poll();
					
					for (int k = 0; k < 4; k++) {
						int nx = cur.x + dx[k];
						int ny = cur.y + dy[k];
						if(ChkValid(nx, ny) && map[nx][ny] == 'x' && markMap[nx][ny] == 0) {
							q.offer(new Point(nx, ny));
							markMap[nx][ny] = mark;
						}
					}
				}
			}
		}
	}
	
	static void Shot(int turn, int height) {
		if(turn % 2 == 0) {
			for (int i = 0; i < C; i++) {
				if(map[R - height][i] == 'x') {
					map[R - height][i] = '.';
					return;
				}
			}
		}else {
			for (int i = C - 1; i >= 0; i--) {
				if(map[R - height][i] == 'x') {
					map[R - height][i] = '.';
					return;
				}
			}
		}
	}
	
	static boolean ChkValid(int x, int y) {
		if(x < 0 || x >= R || y < 0 || y >= C) return false;
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
