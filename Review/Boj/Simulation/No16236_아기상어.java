package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No16236_아기상어 {
	
	static int N, answer = 0, sharkSize = 2, cnt = 0, eatCnt = 0;
	static int px = -1, py = -1;
	static int[][] map;
	static boolean[][] chk;
	static Queue<Point> q;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		q = new LinkedList<>();
		chk = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num == 9) {
					q.offer(new Point(i, j));
					chk[i][j] = true;
					map[i][j] = 0;
				}
			}
		}
		
		while(true) {
			while(!q.isEmpty()) {
				
				int size = q.size();
				
				for (int i = 0; i < size; i++) {
					Point cur = q.poll();
					
					for (int j = 0; j < 4; j++) {
						int nx = cur.x + dx[j];
						int ny = cur.y + dy[j];
						
						if(chkValid(nx, ny) && map[nx][ny] <= sharkSize && !chk[nx][ny]) {
							q.offer(new Point(nx, ny));
							chk[nx][ny] = true;
							if(map[nx][ny] != 0 && map[nx][ny] < sharkSize) {
								if(px == -1 && py == -1) {
									px = nx;
									py = ny;
								}else if(px > nx) {
									px = nx;
									py = ny;
								}else if(px == nx && py > ny) {
									px = nx;
									py = ny;
								}
							}
						}
					}
				}
				cnt++;
				if(px != -1 && py != -1) break;
			}
			if(px == -1 && py == -1) break;
			else {
				map[px][py] = 0;
				q.clear();
				q.offer(new Point(px, py));
				chk = new boolean[N][N];
				chk[px][py] = true;
				answer += cnt;
				cnt = 0;
				eatCnt++;
				px = -1;
				py = -1;
				if(eatCnt == sharkSize) {
					eatCnt = 0;
					sharkSize++;
				}				
			}
		}
		System.out.println(answer);

	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N ) return false;
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
