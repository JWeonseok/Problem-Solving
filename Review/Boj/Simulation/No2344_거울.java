package rev.simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No2344_거울 {
	
	static int N, M;
	static int[][] map;
	static int[] answer;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N + 2][M + 2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) map[i][j] = -1;
			}
		}
		
		answer = new int[2 * N + 2 * M];
		Numbering();
		Mirroring();
		
		StringBuilder sb = new StringBuilder();
		for (Integer in : answer) sb.append(in).append(' ');
		System.out.println(sb);

	}
	
	static void Mirroring() {
		int ind = 0, len = answer.length;
		int nx = 1, ny = 0, dir = 2, d = 0;
		
		while (true) {
			if (ind >= len) return;
			
			if (ny == 0) d = 1;
			else if (nx == N + 1) d = 0;
			else if (ny == M + 1) d = 3;
			else if (nx == 0) d = 2;

			answer[ind++] = GoLight(nx, ny, d);
			
			nx += dx[dir];
			ny += dy[dir];
			
			if (!ChkBoundary(nx + dx[dir], ny + dy[dir])) {
				dir = (dir + 3) % 4;
				nx += dx[dir];
				ny += dy[dir];
			}
			
		}
	}
	
	static int GoLight(int x, int y, int d) {
		int nx = x, ny = y, dir = d;
		
		while (true) {
			if (!ChkBoundary(nx + dx[dir], ny + dy[dir])) return map[nx][ny];
			
			nx += dx[dir];
			ny += dy[dir];
			
			if (map[nx][ny] == -1) {
				if (dir == 0) dir = 1;
				else if (dir == 1) dir = 0;
				else if (dir == 2) dir = 3;
				else if (dir == 3) dir = 2;
			}
		}
	}
	
	
	static void Numbering() {
		int num = 1, dir = 2;
		int nx = 1, ny = 0;
		
		while (true) {
			if (num > 2 * N + 2 * M) break;

			if (!ChkBoundary(nx + dx[dir], ny + dy[dir])) {
				dir = (dir + 3) % 4;
				nx += dx[dir];
				ny += dy[dir];
				continue;
			}
			
			map[nx][ny] = num++;
			nx += dx[dir];
			ny += dy[dir];
		}
	}
	
	static boolean ChkBoundary(int x, int y) {
		if (x < 0 || x >= N + 2 || y < 0 || y >= M + 2) return false;
		return true;
	}

}
