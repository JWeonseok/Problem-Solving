package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class No21610_마법사상어와비바라기 {
	
	static int N, M, answer = 0;
	static int[][] map;
	static LinkedList<Cloud> clouds;
	
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		clouds = new LinkedList<>();
		clouds.offer(new Cloud(N - 1, 0));
		clouds.offer(new Cloud(N - 1, 1));
		clouds.offer(new Cloud(N - 2, 0));
		clouds.offer(new Cloud(N - 2, 1));
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken()) % N;
			
			moveCloud(d, s);
			copyBug();
			makeCloud();
		}
		

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer += map[i][j];
			}
		}
		
		System.out.println(answer);
	}
	
	static void makeCloud() {

		LinkedList<Cloud> newClouds = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] < 2) continue;
				
				boolean flag = false;
				for(Cloud c : clouds) {
					if(c.x == i && c.y == j) {
						flag = true;
						break;
					}
				}
				if(!flag) {
					newClouds.offer(new Cloud(i, j));
					map[i][j] -= 2;
				}
				
			}
		}
		
		clouds.clear();
		clouds.addAll(newClouds);
	}
	
	static void copyBug() {
		for(Cloud c : clouds) {
			int cnt = 0;
			for (int i = 1; i < 8; i += 2) {
				int nx = c.x + dx[i];
				int ny = c.y + dy[i];
				
				if(chkValid(nx, ny) && map[nx][ny] > 0) cnt++;
			}
			map[c.x][c.y] += cnt;
		}
	}
	
	static void moveCloud(int d, int s) {
		int size = clouds.size();
		
		for (int i = 0; i < size; i++) {
			Cloud cur = clouds.poll();
			
			int nx = (cur.x + dx[d] * s + N) % N;
			int ny = (cur.y + dy[d] * s + N) % N;
			
			clouds.offer(new Cloud(nx, ny));
			map[nx][ny]++;
		}
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) return false;
		return true;
	}
	
	static class Cloud {
		int x, y;

		public Cloud(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
