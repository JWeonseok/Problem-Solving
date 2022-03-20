package rev.simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No20005_보스몬스터전리품 {
	
	static int N, M, P, hp, players = 0;
	static char[][] map;
	static Queue<Point> q;
	static boolean[][] visited;
	static int[] users;
	static boolean[] visitedUsers;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		q = new LinkedList<>();
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'B') {
					q.offer(new Point(i, j));
					visited[i][j] = true;
					map[i][j] = '.';
				}
			}
		}
		
		users = new int[P];
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			char id = st.nextToken().charAt(0);
			int dps = Integer.parseInt(st.nextToken());
			
			users[i] = dps;
		}
		
		hp = Integer.parseInt(br.readLine());
		visitedUsers = new boolean[P];
		while (!q.isEmpty()) {
			if (hp <= 0) break;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point cur = q.poll();
				if (map[cur.x][cur.y] != '.' && map[cur.x][cur.y ] != 'X') {
					players++;
					visitedUsers[map[cur.x][cur.y] - 'a'] = true;
				}
								
				for (int j = 0; j < 4; j++) {
					int nx = cur.x + dx[j];
					int ny = cur.y + dy[j];
					
					if (ChkBoundary(nx, ny) && map[nx][ny] != 'X' && !visited[nx][ny]) {
						q.offer(new Point(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
			
			for (int i = 0; i < P; i++) {
				if (visitedUsers[i]) hp -= users[i];
			}
		}	

		System.out.println(players);

	}
	
	static boolean ChkBoundary(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M) return false;
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
