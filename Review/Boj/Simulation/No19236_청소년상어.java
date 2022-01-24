package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No19236_청소년상어 {
	
	static int answer = 0;
	static int[][] map;
	static Fish[] fishes;
	
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[4][4];
		fishes = new Fish[17];
		
		
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken()) - 1;
				fishes[num] = new Fish(i, j, d, 1);
				map[i][j] = num;
			}
		}
		
		int val = map[0][0];
		int initDist = fishes[map[0][0]].d;
		fishes[map[0][0]].alive = 0;		
		map[0][0] = -1;
		
		sharkMove(0, 0, initDist, val);
		
		System.out.println(answer);

	}
	
	static void sharkMove(int x, int y, int d, int val) {
		answer = Math.max(answer, val);
		
		int nx = x;
		int ny = y;
		
		int[][] copyMap = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		
		Fish[] copyFishes = new Fish[17];
		for (int i = 1; i <= 16; i++) {
			copyFishes[i] = new Fish(fishes[i].x, fishes[i].y, fishes[i].d, fishes[i].alive);
		}
		
		fishMove();
		
		while(true) {
			nx += dx[d];
			ny += dy[d];
			
			if(!chkValid(nx, ny)) break;
			if(map[nx][ny] == 0) continue;
		
			map[x][y] = 0;
			int num = map[nx][ny];
			fishes[num].alive = 0;
			map[nx][ny] = -1;
			
			sharkMove(nx, ny, fishes[num].d, val + num);
			
			fishes[num].alive = 1;
			map[x][y] = -1;
			map[nx][ny] = num;
			
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = copyMap[i][j];
			}
		}
		
		for (int i = 1; i <= 16; i++) {
			fishes[i] = new Fish(copyFishes[i].x, copyFishes[i].y, copyFishes[i].d, copyFishes[i].alive);
		}
	}
	
	static void fishMove() {
		
		for (int i = 1; i <= 16; i++) {
			if(fishes[i].alive == 0) continue;
			
			int dist = fishes[i].d;
			
			for (int j = 0; j < 8; j++) {
				int nx = fishes[i].x + dx[(dist + j) % 8];
				int ny = fishes[i].y + dy[(dist + j) % 8];				
				
				if(chkValid(nx, ny) && map[nx][ny] != -1) {
					fishes[i].d = (dist + j) % 8;
					
					int x = fishes[i].x;
					int y = fishes[i].y;
					
					fishes[i].x = nx;
					fishes[i].y = ny;
					
					if(map[nx][ny] != 0) {
						fishes[map[nx][ny]].x = x;
						fishes[map[nx][ny]].y = y;
					}
					
					int tmp = map[nx][ny];
					map[nx][ny] = map[x][y];
					map[x][y] = tmp;
					break;
				}
			}
		}
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= 4 || y < 0 || y >= 4) return false;
		return true;
	}
	
	static class Fish {
		int x, y, d, alive;

		public Fish(int x, int y, int d, int alive) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.alive = alive;
		}
	}

}
