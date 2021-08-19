package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No19238_스타트택시 {
	
	static int N, M, fuel;
	static int[][] map;
	static ArrayList<point> start;
	static ArrayList<point> end;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		start = new ArrayList<>();
		end = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) map[i][j] = 40001;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken()) - 1;
		int y = Integer.parseInt(st.nextToken()) - 1;
		point taxi = new point(x, y);
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken()) - 1;
			int sy = Integer.parseInt(st.nextToken()) - 1;
			int ex = Integer.parseInt(st.nextToken()) - 1;
			int ey = Integer.parseInt(st.nextToken()) - 1;
			
			start.add(new point(sx, sy));
			end.add(new point(ex, ey));
		}
		
		while(M > 0) {
			point passenger = findPassenger(taxi);
			taxi = goDestination(passenger);			
		}
		
		System.out.println(fuel);

	}
	
	static point goDestination(point p) {
		Queue<point> q = new LinkedList<>();
		boolean[][] chk = new boolean[N][N];
		
		q.offer(p);
		chk[p.x][p.y] = true;
		
		int len = 0;
		int ind = getStart(p);
		point dest = end.get(ind);
		boolean flag = false;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				point cur = q.poll();
				
				if(cur.x == dest.x && cur.y == dest.y) {
					start.remove(ind);
					end.remove(ind);
					fuel -= len;
					if(fuel < 0) {
						System.out.println(-1);
						System.exit(0);						
					}
					fuel += len * 2;
					M--;
					flag = true;
					return dest;
				}
				
				for (int j = 0; j < 4; j++) {
					int nx = cur.x + dx[j];
					int ny = cur.y + dy[j];
					
					if(chkvalid(nx, ny) && map[nx][ny] != 40001 && !chk[nx][ny]) {
						q.offer(new point(nx, ny));
						chk[nx][ny] = true;
					}
				}
			}
			len++;
		}
		if(!flag) {
			System.out.println(-1);
			System.exit(0);
		}
		return dest;
	}

	static point findPassenger(point p) {
		Queue<point> q = new LinkedList<>();
		boolean[][] chk = new boolean[N][N];
		
		q.offer(p);
		chk[p.x][p.y] = true;
		
		int len = 0, cnt = 0;
		
		int x = N; int y = N;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				point cur = q.poll();
				if(getStart(cur) != -1) {
					if(x == N && y == N) {
						x = cur.x;
						y = cur.y;
						len = cnt;
					}else if(len == cnt) {
						if(x > cur.x) {
							x = cur.x;
							y = cur.y;
							len = cnt;
						}else if(x == cur.x && y > cur.y) {
							x = cur.x;
							y = cur.y;
							len = cnt;
						}
					}
					continue;
				}
				
				for (int j = 0; j < 4; j++) {
					int nx = cur.x + dx[j];
					int ny = cur.y + dy[j];
					if(chkvalid(nx, ny) && map[nx][ny] != 40001 && !chk[nx][ny]) {
						q.offer(new point(nx, ny));
						chk[nx][ny] = true;
					}
				}
				
			}
			
			cnt++;
		}
		if(x != N && y != N) {
			fuel -= len;
			if(fuel <= 0) {
				System.out.println(-1);
				System.exit(0);
			}
		}else {
			System.out.println(-1);
			System.exit(0);
		}
		
		return new point(x, y);
	}
	
	static int getStart(point tgt) {
		for (int i = 0; i < M; i++) {
			point tmp = start.get(i);
			if(tmp.x == tgt.x && tmp.y == tgt.y) return i;
		}
		return -1;
	}
	
	static boolean chkvalid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) return false;
		return true;
	}
	
	static class point {
		int x, y;

		public point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
