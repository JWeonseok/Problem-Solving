package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No13460_구슬탈출2 {
	
	static int N, M;
	static char[][] map;
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
		
		q = new LinkedList<>();
		q.offer(balls);
		
		int cnt = 0;
		while(!q.isEmpty()) {
			if (cnt > 10) break;
			
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				balls = q.poll();
				
				if (map[balls.bx][balls.by] == 'O') continue;
				else if (map[balls.rx][balls.ry] == 'O') {
					System.out.println(cnt);
					return;
				}
				
				for (int j = 0; j < 4; j++) Move(j);
			}
			cnt++;
		}
		
		System.out.println(-1);
		
	}
	
	static void Move(int dir) {		
		int rx = balls.rx;
		int ry = balls.ry;
		int bx = balls.bx;
		int by = balls.by;
		
		if (dir == 0) {
			if (rx <= bx) {
				while(true) {
					if (!ChkValid(rx + dx[dir], ry + dy[dir]) || map[rx + dx[dir]][ry + dy[dir]] == '#') break;
					if (map[rx][ry] == 'O') break;
					rx += dx[dir];
					ry += dy[dir];
				}
				while(true) {
					if (!ChkValid(bx + dx[dir], by + dy[dir]) || map[bx + dx[dir]][by + dy[dir]] == '#') break;
					if (map[bx + dx[dir]][by + dy[dir]] == 'O') {
						bx += dx[dir];
						by += dy[dir];
						break;
					}
					if (bx + dx[dir] == rx && by + dy[dir] == ry) break;					
					bx += dx[dir];
					by += dy[dir];
				}
			} else {
				while(true) {
					if (!ChkValid(bx + dx[dir], by + dy[dir]) || map[bx + dx[dir]][by + dy[dir]] == '#') break;
					if (map[bx][by] == 'O') break;
					bx += dx[dir];
					by += dy[dir];
				}
				while(true) {
					if (!ChkValid(rx + dx[dir], ry + dy[dir]) || map[rx + dx[dir]][ry + dy[dir]] == '#') break;
					if (map[rx + dx[dir]][ry + dy[dir]] == 'O') {
						rx += dx[dir];
						ry += dy[dir];
						break;
					}
					if (rx + dx[dir] == bx && ry + dy[dir] == by) break;
					rx += dx[dir];
					ry += dy[dir];
				}
			}			
		} else if (dir == 1) {
			if (ry >= by) {
				while(true) {
					if (!ChkValid(rx + dx[dir], ry + dy[dir]) || map[rx + dx[dir]][ry + dy[dir]] == '#') break;
					if (map[rx][ry] == 'O') break;
					rx += dx[dir];
					ry += dy[dir];
				}
				while(true) {
					if (!ChkValid(bx + dx[dir], by + dy[dir]) || map[bx + dx[dir]][by + dy[dir]] == '#') break;
					if (map[bx + dx[dir]][by + dy[dir]] == 'O') {
						bx += dx[dir];
						by += dy[dir];
						break;
					}
					if (bx + dx[dir] == rx && by + dy[dir] == ry) break;
					bx += dx[dir];
					by += dy[dir];
				}
			} else {
				while(true) {
					if (!ChkValid(bx + dx[dir], by + dy[dir]) || map[bx + dx[dir]][by + dy[dir]] == '#') break;		
					if (map[bx][by] == 'O') break;
					bx += dx[dir];
					by += dy[dir];
				}
				while(true) {
					if (!ChkValid(rx + dx[dir], ry + dy[dir]) || map[rx + dx[dir]][ry + dy[dir]] == '#') break;
					if (map[rx + dx[dir]][ry + dy[dir]] == 'O') {
						rx += dx[dir];
						ry += dy[dir];
						break;
					}
					if (rx + dx[dir] == bx && ry + dy[dir] == by) break;
					rx += dx[dir];
					ry += dy[dir];
				}
			}
		} else if (dir == 2) {
			if (rx >= bx) {
				while(true) {
					if (!ChkValid(rx + dx[dir], ry + dy[dir]) || map[rx + dx[dir]][ry + dy[dir]] == '#') break;
					if (map[rx][ry] == 'O') break;
					rx += dx[dir];
					ry += dy[dir];
				}
				while(true) {
					if (!ChkValid(bx + dx[dir], by + dy[dir]) || map[bx + dx[dir]][by + dy[dir]] == '#') break;
					if (map[bx + dx[dir]][by + dy[dir]] == 'O') {
						bx += dx[dir];
						by += dy[dir];
						break;
					}
					if (bx + dx[dir] == rx && by + dy[dir] == ry) break;
					bx += dx[dir];
					by += dy[dir];
				}
			} else {
				while(true) {
					if (!ChkValid(bx + dx[dir], by + dy[dir]) || map[bx + dx[dir]][by + dy[dir]] == '#') break;		
					if (map[bx][by] == 'O') break;
					bx += dx[dir];
					by += dy[dir];
				}
				while(true) {
					if (!ChkValid(rx + dx[dir], ry + dy[dir]) || map[rx + dx[dir]][ry + dy[dir]] == '#') break;
					if (map[rx + dx[dir]][ry + dy[dir]] == 'O') {
						rx += dx[dir];
						ry += dy[dir];
						break;
					}
					if (rx + dx[dir] == bx && ry + dy[dir] == by) break;
					rx += dx[dir];
					ry += dy[dir];
				}
			}
		} else if (dir == 3) {
			if (ry <= by) {
				while(true) {
					if (!ChkValid(rx + dx[dir], ry + dy[dir]) || map[rx + dx[dir]][ry + dy[dir]] == '#') break;
					if (map[rx][ry] == 'O') break;
					rx += dx[dir];
					ry += dy[dir];
				}
				while(true) {
					if (!ChkValid(bx + dx[dir], by + dy[dir]) || map[bx + dx[dir]][by + dy[dir]] == '#') break;
					if (map[bx + dx[dir]][by + dy[dir]] == 'O') {
						bx += dx[dir];
						by += dy[dir];
						break;
					}
					if (bx + dx[dir] == rx && by + dy[dir] == ry) break;
					bx += dx[dir];
					by += dy[dir];
				}
			} else {
				while(true) {
					if (!ChkValid(bx + dx[dir], by + dy[dir]) || map[bx + dx[dir]][by + dy[dir]] == '#') break;			
					if (map[bx][by] == 'O') break;
					bx += dx[dir];
					by += dy[dir];
				}
				while(true) {
					if (!ChkValid(rx + dx[dir], ry + dy[dir]) || map[rx + dx[dir]][ry + dy[dir]] == '#') break;
					if (map[rx][ry] == 'O') break;
					if (rx + dx[dir] == bx && ry + dy[dir] == by) break;
					rx += dx[dir];
					ry += dy[dir];
				}
			}
		}
		
		if (!(balls.rx == rx && balls.ry == ry && balls.bx == bx && balls.by == by)) q.offer(new Balls(rx, ry, bx, by));
	}
	
	static boolean ChkValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M) return false;
		return true;
	}
	
	static class Balls {
		int rx, ry, bx, by;
		
		public Balls() {
			super();
		}
		
		public Balls(int rx, int ry, int bx, int by) {
			super();
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
		}	
	}

}
