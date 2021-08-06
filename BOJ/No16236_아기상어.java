package ws;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Ws0317 {
	
	static int N, answer;
	static int[][] grid;
	static int sSize = 2, sEat = 0;
	static boolean[][] chk;
	static Queue<fish> q;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int sx, sy, sd;
	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		grid = new int[N][N];
		chk = new boolean[N][N];
		q = new LinkedList<>();
		answer = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
				if(grid[i][j] == 9) {
					grid[i][j] = 0;
					chk[i][j] = true;
					q.offer(new fish(i, j, 0));
					sx = i;
					sy = j;
					sd = Integer.MAX_VALUE;
				}
			}
		}
		
		while(!q.isEmpty()) {
			if(available()) {
				boolean flag = false;
				int size = q.size();
				for (int i = 0; i < size; i++) {
					fish cur = q.poll();
					for (int j = 0; j < 4; j++) {
						int nx = cur.x + dx[j];
						int ny = cur.y + dy[j];
						if(chkvalid(nx, ny) && !chk[nx][ny] && grid[nx][ny] <= sSize) {
							q.offer(new fish(nx, ny, cur.dist+1));
							chk[nx][ny] = true;
							if(grid[nx][ny] != 0 && grid[nx][ny] < sSize) {
								if(sd > cur.dist + 1) {
									sx = nx;
									sy = ny;
									sd = cur.dist + 1;
								}else if(sd == cur.dist + 1) {
									if(sx > nx) {
										sx = nx;
										sy = ny;
										sd = cur.dist + 1;
									}else if(sx == nx) {
										if(sy > ny) {
											sx = nx;
											sy = ny;
											sd = cur.dist + 1;
										}
									}
								}
								flag = true;
							}
						}
					}
				}
				if(flag) {
					answer += sd;
					q.clear();
					chk = new boolean[N][N];
					grid[sx][sy] = 0;
					q.offer(new fish(sx, sy, 0));
					chk[sx][sy] = true;
					sd = Integer.MAX_VALUE;
					sEat++;
					if(sSize == sEat) {
						sSize+=1;
						sEat = 0;
					}
				}
			}else break;
		}
		
		
		System.out.println(answer);
	}
	
	static boolean available() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(grid[i][j] != 0) return true;
			}
		}
		return false;
	}
	
	static boolean chkvalid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) return false;
		return true;
	}
	
	static class fish{
		int x;
		int y;
		int dist;
		public fish(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
				
	}

}
