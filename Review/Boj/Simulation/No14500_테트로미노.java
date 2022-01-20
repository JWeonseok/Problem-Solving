package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No14500_테트로미노 {
	
	static int N, M, answer = Integer.MIN_VALUE;
	static int[][] map;
	
	static int[][] dx = {{0, 0, 0}, {0, 1, 1}, {1, 2, 2}, {1, 1, 2}, {0, 0, 1}};
	static int[][] dy = {{1, 2, 3}, {1, 0, 1}, {0, 0, 1}, {0, 1, 1}, {1, 2, 1}};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < 5; k++) {
					tetris(i, j, k);
				}
			}
		}
		
		System.out.println(answer);

	}
	
	static void tetris(int x, int y, int num) {
		int val = map[x][y];
		if(num == 1) {
			for (int i = 0; i < 3; i++) {
				int nx = x + dx[num][i];
				int ny = y + dy[num][i];
				
				if(!chkValid(nx, ny)) break;
				
				val += map[nx][ny];
			}
			answer = Math.max(answer, val);
		}else if(num == 0) {
			for (int i = 0; i < 3; i++) {
				int nx = x + dx[num][i];
				int ny = y + dy[num][i];
				
				if(!chkValid(nx, ny)) break;
				
				val += map[nx][ny];
			}
			answer = Math.max(answer, val);
			
			val = map[x][y];			
			for (int i = 0; i < 3; i++) {
				int nx = x + dy[num][i];
				int ny = y + dx[num][i];
				
				if(!chkValid(nx, ny)) break;
				
				val += map[nx][ny];
			}
			answer = Math.max(answer, val);
		}else if(num == 4) {
			for (int i = 0; i < 3; i++) {
				int nx = x + dx[num][i];
				int ny = y + dy[num][i];
				
				if(!chkValid(nx, ny)) break;
				
				val += map[nx][ny];
			}
			answer = Math.max(answer, val);
			
			val = map[x][y];
			for (int i = 0; i < 3; i++) {
				int nx = x + dy[num][i];
				int ny = y - dx[num][i];
				
				if(!chkValid(nx, ny)) break;
				
				val += map[nx][ny];
			}
			answer = Math.max(answer, val);
			
			val = map[x][y];
			for (int i = 0; i < 3; i++) {
				int nx = x - dx[num][i];
				int ny = y - dy[num][i];
				
				if(!chkValid(nx, ny)) break;
				
				val += map[nx][ny];
			}
			answer = Math.max(answer, val);
			
			val = map[x][y];
			for (int i = 0; i < 3; i++) {
				int nx = x - dy[num][i];
				int ny = y + dx[num][i];
				
				if(!chkValid(nx, ny)) break;
				
				val += map[nx][ny];
			}
			answer = Math.max(answer, val);
		}else {
			for (int i = 0; i < 3; i++) {
				int nx = x + dx[num][i];
				int ny = y + dy[num][i];
				
				if(!chkValid(nx, ny)) break;
				
				val += map[nx][ny];
			}
			answer = Math.max(answer, val);
			
			val = map[x][y];
			for (int i = 0; i < 3; i++) {
				int nx = x + dy[num][i];
				int ny = y - dx[num][i];
				
				if(!chkValid(nx, ny)) break;
				
				val += map[nx][ny];
			}
			answer = Math.max(answer, val);
			
			val = map[x][y];
			for (int i = 0; i < 3; i++) {
				int nx = x - dx[num][i];
				int ny = y - dy[num][i];
				
				if(!chkValid(nx, ny)) break;
				
				val += map[nx][ny];
			}
			answer = Math.max(answer, val);
			
			val = map[x][y];
			for (int i = 0; i < 3; i++) {
				int nx = x - dy[num][i];
				int ny = y + dx[num][i];
				
				if(!chkValid(nx, ny)) break;
				
				val += map[nx][ny];
			}
			answer = Math.max(answer, val);
			
			val = map[x][y];
			for (int i = 0; i < 3; i++) {
				int nx = x + dy[num][i];
				int ny = y + dx[num][i];
				
				if(!chkValid(nx, ny)) break;
				
				val += map[nx][ny];
			}
			answer = Math.max(answer, val);
			
			val = map[x][y];
			for (int i = 0; i < 3; i++) {
				int nx = x + dx[num][i];
				int ny = y - dy[num][i];
				
				if(!chkValid(nx, ny)) break;
				
				val += map[nx][ny];
			}
			answer = Math.max(answer, val);
			
			val = map[x][y];
			for (int i = 0; i < 3; i++) {
				int nx = x - dy[num][i];
				int ny = y - dx[num][i];
				
				if(!chkValid(nx, ny)) break;
				
				val += map[nx][ny];
			}
			answer = Math.max(answer, val);
			
			val = map[x][y];
			for (int i = 0; i < 3; i++) {
				int nx = x - dx[num][i];
				int ny = y + dy[num][i];
				
				if(!chkValid(nx, ny)) break;
				
				val += map[nx][ny];
			}
			answer = Math.max(answer, val);
		}
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= M) return false;
		return true;
	}

}
