package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No19237_어른상어 {
	
	static int N, M, K;
	static int[][] map, chk;
	static int[][][] moveInfo;
	static Shark[] sharks;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		chk = new int[N][N];
		moveInfo = new int[M + 1][4][4];
		sharks = new Shark[M + 1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0) {
					sharks[map[i][j]] = new Shark(i, j, 1);
					chk[i][j] = K;
					map[i][j] = -map[i][j];
				}				
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			sharks[i].dir = Integer.parseInt(st.nextToken()) - 1;
		}
		
		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					moveInfo[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}
		
		int time = 0;
		while(time <= 1000) {
			if(chkAnswer()) {
				System.out.println(time);
				return;
			}
			sharkMove();
			time++;
		}
		
		System.out.println(-1);

	}
	
	static void sharkMove() {
		for (int i = 1; i <= M; i++) {
			if(sharks[i].alive == 0) continue;
			
			boolean moveFlag = false;
			for (int j = 0; j < 4; j++) {
				int nx = sharks[i].x + dx[moveInfo[i][sharks[i].dir][j]];
				int ny = sharks[i].y + dy[moveInfo[i][sharks[i].dir][j]];
				
				if(chkValid(nx, ny) && map[nx][ny] == 0) {
					map[sharks[i].x][sharks[i].y] = i;
					sharks[i].x = nx;
					sharks[i].y = ny;
					sharks[i].dir = moveInfo[i][sharks[i].dir][j];
					moveFlag = true;
					break;
				}
			}
			
			if(!moveFlag) {
				for (int j = 0; j < 4; j++) {
					int nx = sharks[i].x + dx[moveInfo[i][sharks[i].dir][j]];
					int ny = sharks[i].y + dy[moveInfo[i][sharks[i].dir][j]];
					
					if(chkValid(nx, ny) && map[nx][ny] == i) {
						map[sharks[i].x][sharks[i].y] = i;
						sharks[i].x = nx;
						sharks[i].y = ny;
						sharks[i].dir = moveInfo[i][sharks[i].dir][j];
						break;
					}
				}
			}
		}
		
		for (int i = 1; i <= M; i++) {
			if(sharks[i].alive == 0) continue;
			
			if(map[sharks[i].x][sharks[i].y] >= 0) {
				map[sharks[i].x][sharks[i].y] = -i;
				chk[sharks[i].x][sharks[i].y] = K + 1;
			}else {
				sharks[i].alive = 0;
			}
		}
		
		deleteSmell();
	}
	
	static void deleteSmell() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(chk[i][j] == 0) continue;
				if(--chk[i][j] == 0) map[i][j] = 0;
			}
		}
	}
	
	static boolean chkAnswer() {
		for (int i = 2; i <= M; i++) {
			if(sharks[i].alive != 0) return false;
		}
		return true;
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) return false;
		return true;
	}
	
	static class Shark {
		int x, y, dir, alive;

		public Shark(int x, int y, int alive) {
			super();
			this.x = x;
			this.y = y;
			this.alive = alive;
		}
	}

}
