package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No15653_구슬탈출4 {
	
	static int N, M, cnt = 0;
	static char[][] map;
	static boolean[][][][] chk;
	static Balls balls;
	static Queue<Balls> q;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		chk = new boolean[N][M][N][M];
		q = new LinkedList<>();
		balls = new Balls();
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'R') {
					balls.rx = i;
					balls.ry = j;
					map[i][j] = '.';
				} else if (map[i][j] == 'B') {
					balls.bx = i;
					balls.by = j;
					map[i][j] = '.';
				}
			}
		}
		
		q.offer(balls);
		chk[balls.rx][balls.ry][balls.bx][balls.by] = true;
		
		bfs();
		
		System.out.println(-1);
	}
	
	static void bfs() {
		
		while (!q.isEmpty()) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				balls = q.poll();
				
				if (map[balls.rx][balls.ry] == 'O') {
					System.out.println(cnt);
					System.exit(0);
				}
				
				for (int j = 0; j < 4; j++) {
					Move(j);
				}
			}
			
			cnt++;
		}
		
	}
	
	static void Move(int dir) {
		
		int rnx = balls.rx, rny = balls.ry, bnx = balls.bx, bny = balls.by;	
		
		if (ChkFirstBall(dir)) {
			
			while (true) {
				if (map[rnx + dx[dir]][rny + dy[dir]] == '#') break;
				else if (map[rnx + dx[dir]][rny + dy[dir]] == 'O') {
					rnx += dx[dir];
					rny += dy[dir];
					break;
					
				}
				rnx += dx[dir];
				rny += dy[dir];
			}
			
			while (true) {
				if (map[bnx + dx[dir]][bny + dy[dir]] == '#') break;
				else if (map[bnx + dx[dir]][bny + dy[dir]] == 'O') return;
				else if (bnx + dx[dir] == rnx && bny + dy[dir] == rny) break;
				bnx += dx[dir];
				bny += dy[dir];
			}
			
		} else {
			while (true) {
				if (map[bnx + dx[dir]][bny + dy[dir]] == '#') break;
				else if (map[bnx + dx[dir]][bny + dy[dir]] == 'O') return;
				bnx += dx[dir];
				bny += dy[dir];
			}
			
			while (true) {
				if (map[rnx + dx[dir]][rny + dy[dir]] == '#' || (rnx + dx[dir] == bnx && rny + dy[dir] == bny)) break;
				else if (map[rnx + dx[dir]][rny + dy[dir]] == 'O') {
					rnx += dx[dir];
					rny += dy[dir];
					break;
				}
				rnx += dx[dir];
				rny += dy[dir];
			}
		}
		
		if (!chk[rnx][rny][bnx][bny]) {
			q.offer(new Balls(rnx, rny, bnx, bny));
			chk[rnx][rny][bnx][bny] = true;
			return;
		}
		
		
		
	}
	
	static boolean ChkFirstBall(int dir) {
		if (dir == 0) {
			if (balls.rx <= balls.bx) return true;
			else return false;
		} else if (dir == 1) {
			if (balls.ry >= balls.by) return true;
			else return false;
		} else if (dir == 2) {
			if (balls.rx >= balls.bx) return true;
			else return false;
		} else {
			if (balls.ry <= balls.by) return true;
			else return false;
		}
	}
	
	static class Balls {
		int rx, ry, bx, by;
		
		public Balls() {}
		
		public Balls(int rx, int ry, int bx, int by) {
			super();
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
		}
	}
	
}
