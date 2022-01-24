package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No16927_배열돌리기2 {
	
	static int N, M, R;
	static int[][] map;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		rotate(0, 0, 0);
		
		printMap();

	}
	
	static void printMap() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	static void rotate(int x, int y, int cnt) {
		if(Math.min(N, M) < 1) {
			N += 2 * cnt;
			M += 2 * cnt;
			return;
		}
		
		
		int rotate = R % (2 * (N + M - 2));
		if(Math.min(N, M) == 1) rotate = R % (Math.max(N, M));
		
		for (int i = 0; i < rotate; i++) {
			int tmp = map[x][y];
			int d = 0;
			int nx = x;
			int ny = y;
			while(true) {
				if(nx + dx[d] == x && ny + dy[d] == y) {
					map[nx][ny] = tmp;
					break;
				}
				
				if(!chkValid(nx + dx[d], ny + dy[d], cnt)) {
					d = (d + 1) % 4;
					continue;
				}
				
				map[nx][ny] = map[nx + dx[d]][ny + dy[d]];
				nx += dx[d];
				ny += dy[d];
			}
		}
		
		N -= 2;
		M -= 2;
		rotate(x + 1, y + 1, cnt + 1);
	}
	
	static boolean chkValid(int x, int y, int cnt) {
		if(x < 0 + cnt || x >= N + cnt || y < 0 + cnt || y >= M + cnt) return false;
		return true;
	}

}
