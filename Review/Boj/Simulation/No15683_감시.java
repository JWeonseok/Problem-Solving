package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class No15683_감시 {
	
	static int N, M, answer = Integer.MAX_VALUE;
	static int[][] map;
	static ArrayList<Point> cctv;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		cctv = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0 && map[i][j] != 6) cctv.add(new Point(i, j));
			}
		}
		
		chkSight(0);
		
		System.out.println(answer);

	}
	
	static void chkSight(int ind) {
		if(ind == cctv.size()) {
			chkArea();
			return;
		}
		
		Point cur = cctv.get(ind);
		
		if(map[cur.x][cur.y] == 1) {
			for (int i = 0; i < 4; i++) {
				onCCTV(cur.x, cur.y, i, -1);
				chkSight(ind + 1);
				onCCTV(cur.x, cur.y, i, 1);
			}
		}else if(map[cur.x][cur.y] == 2) {
			for (int i = 0; i < 2; i++) {
				onCCTV(cur.x, cur.y, i, -1);
				onCCTV(cur.x, cur.y, i + 2, -1);
				chkSight(ind + 1);
				onCCTV(cur.x, cur.y, i, 1);
				onCCTV(cur.x, cur.y, i + 2, 1);
			}
		}else if(map[cur.x][cur.y] == 3) {
			for (int i = 0; i < 4; i++) {
				onCCTV(cur.x, cur.y, i, -1);
				onCCTV(cur.x, cur.y, (i + 1) % 4, -1);
				chkSight(ind + 1);
				onCCTV(cur.x, cur.y, i, 1);
				onCCTV(cur.x, cur.y, (i + 1) % 4, 1);
			}
		}else if(map[cur.x][cur.y] == 4) {
			for (int i = 0; i < 4; i++) {
				onCCTV(cur.x, cur.y, i, -1);
				onCCTV(cur.x, cur.y, (i + 1) % 4, -1);
				onCCTV(cur.x, cur.y, (i + 3) % 4, -1);
				chkSight(ind + 1);
				onCCTV(cur.x, cur.y, i, 1);
				onCCTV(cur.x, cur.y, (i + 1) % 4, 1);
				onCCTV(cur.x, cur.y, (i + 3) % 4, 1);
			}
		}else if(map[cur.x][cur.y] == 5) {
			for (int i = 0; i < 4; i++) {
				onCCTV(cur.x, cur.y, i, -1);
			}
			chkSight(ind + 1);
			for (int i = 0; i < 4; i++) {
				onCCTV(cur.x, cur.y, i, 1);
			}
		}
	}
	
	static void onCCTV(int x, int y, int d, int val) {		
		int nx = x;
		int ny = y;
		
		while(true) {
			nx += dx[d];
			ny += dy[d];
			
			if(!chkValid(nx, ny) || map[nx][ny] == 6) break;
			if(1 <= map[nx][ny] && map[nx][ny] <= 5) continue;
			
			map[nx][ny] += val;
		}
	}
	
	static void chkArea() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) cnt++;
			}
		}
		answer = Math.min(answer, cnt);
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= M) return false;
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
