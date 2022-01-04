package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No16234_인구이동 {
	
	static int N, L, R, answer = 0;
	static boolean endPoint;
	static int[][] map;
	static boolean[][] chk;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			chk = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(chk[i][j]) continue;
					
					int sum = 0;
					Queue<Point> q = new LinkedList<>();
					ArrayList<Point> contList = new ArrayList<>();
					q.offer(new Point(i, j));
					contList.add(new Point(i, j));
					chk[i][j] = true;
					
					while(!q.isEmpty()) {
						Point cur = q.poll();
						sum += map[cur.x][cur.y];
						
						for (int k = 0; k < 4; k++) {
							int nx = cur.x + dx[k];
							int ny = cur.y + dy[k];
							
							if(chkValid(nx, ny) && chkBound(map[cur.x][cur.y], map[nx][ny]) && !chk[nx][ny]) {
								if(!endPoint) endPoint = true;
								q.offer(new Point(nx, ny));
								chk[nx][ny] = true;
								contList.add(new Point(nx, ny));
							}
						}
					}
					
					int div = contList.size();
					for(Point p : contList) {
						map[p.x][p.y] = sum / div;
					}
					
				}
			}
			if(!endPoint) break;
			else endPoint = false;
			answer++;
		}
		
		System.out.println(answer);

	}
	
	static boolean chkBound(int num1, int num2) {
		int abs = (int)Math.abs(num1 - num2);
		if(L <= abs && abs <= R) return true;
		return false;
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) return false;
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
