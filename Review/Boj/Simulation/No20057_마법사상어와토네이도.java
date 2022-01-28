package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No20057_마법사상어와토네이도 {
	
	static int N, answer = 0;
	static int[][] map;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];		
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Tornado();
		System.out.println(answer);
	}
	
	static void Tornado() {
		int dir = 0;
		int len = 1;
		int turnCnt = 0;
		
		int nx = N / 2;
		int ny = N / 2;
		
		loop: while(true) {			
			for (int i = 0; i < len; i++) {
				nx += dx[dir];
				ny += dy[dir];
				
				if(!chkValid(nx, ny)) break loop;
				spread(nx, ny, dir);
			}
			
			if(++turnCnt == 2) {
				len++;
				turnCnt = 0;
			}
			
			dir = (dir + 1) % 4;
		}
	}
	
	static void spread(int x, int y, int dir) {
		
		int remain = map[x][y];
		
		int val = (int)(map[x][y] * 0.05);
		int nx = x + dx[dir] * 2;
		int ny = y + dy[dir] * 2;		
		if(chkValid(nx, ny)) {
			map[nx][ny] += val;
		}else answer += val;
		remain -= val;
		
		val = (int)(map[x][y] * 0.07);
		nx = x + dy[dir];
		ny = y + dx[dir];
		if(chkValid(nx, ny)) {
			map[nx][ny] += val;
		}else answer += val;
		remain -= val;
		
		nx = x - dy[dir];
		ny = y - dx[dir];
		if(chkValid(nx, ny)) {
			map[nx][ny] += val;
		}else answer += val;
		remain -= val;
		
		val = (int)(map[x][y] * 0.02);
		nx = x + dy[dir] * 2;
		ny = y + dx[dir] * 2;
		if(chkValid(nx, ny)) {
			map[nx][ny] += val;
		}else answer += val;
		remain -= val;
		
		nx = x - dy[dir] * 2;
		ny = y - dx[dir] * 2;
		if(chkValid(nx, ny)) {
			map[nx][ny] += val;
		}else answer += val;
		remain -= val;
		
		if(dir == 0 || dir == 2) {
			val = (int)(map[x][y] * 0.1);
			nx = x + 1;
			ny = y + dy[dir];
			if(chkValid(nx, ny)) {
				map[nx][ny] += val;
			}else answer += val;
			remain -= val;
			
			nx = x - 1;
			ny = y + dy[dir];
			if(chkValid(nx, ny)) {
				map[nx][ny] += val;
			}else answer += val;
			remain -= val;
			
			val = (int)(map[x][y] * 0.01);
			nx = x + 1;
			ny = y - dy[dir];
			if(chkValid(nx, ny)) {
				map[nx][ny] += val;
			}else answer += val;
			remain -= val;
			
			nx = x - 1;
			ny = y - dy[dir];
			if(chkValid(nx, ny)) {
				map[nx][ny] += val;
			}else answer += val;
			remain -= val;
			
		}else {
			val = (int)(map[x][y] * 0.1);
			nx = x + dx[dir];
			ny = y + 1;
			if(chkValid(nx, ny)) {
				map[nx][ny] += val;
			}else answer += val;
			remain -= val;
			
			nx = x + dx[dir];
			ny = y - 1;
			if(chkValid(nx, ny)) {
				map[nx][ny] += val;
			}else answer += val;
			remain -= val;
			
			val = (int)(map[x][y] * 0.01);
			nx = x - dx[dir];
			ny = y + 1;
			if(chkValid(nx, ny)) {
				map[nx][ny] += val;
			}else answer += val;
			remain -= val;
			
			nx = x - dx[dir];
			ny = y - 1;
			if(chkValid(nx, ny)) {
				map[nx][ny] += val;
			}else answer += val;
			remain -= val;
		}
		
		nx = x + dx[dir];
		ny = y + dy[dir];
		if(chkValid(nx, ny)) {
			map[nx][ny] += remain;
		}else answer += remain;
		
		map[x][y] = 0;
		
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) return false;
		return true;
	}

}
