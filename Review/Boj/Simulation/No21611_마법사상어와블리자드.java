package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No21611_마법사상어와블리자드 {
	
	static int N, M;
	static int[][] map, ice;
	static int[] ballCnt = new int[4];
	static boolean boomFlag;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		ice = new int[M][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			ice[i][0] = d;
			ice[i][1] = s;
		}
		
		
		for (int i = 0; i < M; i++) {
			IceBreak(i);	
			while(true) {
				BallBoom();
				Gravity();
				if(!boomFlag) break;
			}		
			BallChange();
		}		
		System.out.println(ballCnt[1] + 2 * ballCnt[2] + 3 * ballCnt[3]);
	}
	
	static void printMap() {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println("====================");
	}
	
	static void BallChange() {
		int nx = N / 2;
		int ny = N / 2;
		
		int dir = 0;
		int turnCnt = 0;
		int len = 1;
		
		int ballNum = -1, ballCnt = -1;
		
		Queue<Group> q = new LinkedList<>();
		
		loop: while(true) {
			for (int i = 0; i < len; i++) {
				if(nx == 0 && ny == 0) {
					q.offer(new Group(ballCnt, ballNum));
					break loop;
				}
				if(ballNum != -1 && map[nx][ny] == 0) break loop;
				
				nx += dx[dir];
				ny += dy[dir];
				if(map[nx][ny] == ballNum) ballCnt++;
				else {
					if(ballNum != -1) q.offer(new Group(ballCnt, ballNum));
					ballNum = map[nx][ny];
					ballCnt = 1;
				}
				
			}
			
			if(++turnCnt == 2) {
				turnCnt = 0;
				len++;
			}
			dir = (dir + 1) % 4;
		}
		
		if(q.isEmpty()) return;
		
		nx = N / 2;
		ny = N / 2;
		
		dir = 0;
		turnCnt = 0;
		len = 1;
		int groupCnt = 0;
		Group cur = q.poll();		
		loop: while(true) {
			for (int i = 0; i < len; i++) {
				if(nx == 0 && ny == 0) break loop;
				
				nx += dx[dir];
				ny += dy[dir];
				if(groupCnt == 0) {
					map[nx][ny] = cur.cnt;
					groupCnt++;
				}
				else if(groupCnt == 1) {					
					map[nx][ny] = cur.num;
					if(q.isEmpty()) return;
					cur = q.poll();
					groupCnt = 0;
				}				
			}
			
			if(++turnCnt == 2) {
				turnCnt = 0;
				len++;
			}
			dir = (dir + 1) % 4;
		}
		
		
	}
	
	static void IceBreak(int num) {
		int nx = N / 2;
		int ny = N / 2;
		
		int d = ice[num][0];
		int s = ice[num][1];
		
		int dir = 0;
		if(d == 1) dir = 3;
		else if(d == 2)	dir = 1;
		else if(d == 3)	dir = 0;
		else if(d == 4) dir = 2;
		
		for (int i = 1; i <= s; i++) {
			map[nx + dx[dir] * i][ny + dy[dir] * i] = 0;
		}
		Gravity();
	}
	
	static void BallBoom() {
		int nx = N / 2;
		int ny = N / 2;
		
		int dir = 0;
		int turnCnt = 0;
		int len = 1;
		
		Queue<Ball> boomList = new LinkedList<>();
		boomFlag = false;
		
		while(true) {			
			
			for (int i = 0; i < len; i++) {
				if(nx == 0 && ny == 0) return;
				nx += dx[dir];
				ny += dy[dir];
				if(map[nx][ny] == 0) continue;
				
				boomList.clear();
				boomList.offer(new Ball(nx, ny));
								
				int tmpx = nx;
				int tmpy = ny;
				boolean flag = false;
				
				for (int j = i + 1; j < len; j++) {
					tmpx += dx[dir];
					tmpy += dy[dir];
					if(map[tmpx][tmpy] == map[nx][ny]) {
						boomList.offer(new Ball(tmpx, tmpy));
					}else {
						flag = true;
						break;
					}
				}
				
				if(!flag) {
					int tmpDir = (dir + 1) % 4;;
					int tmpTurnCnt = turnCnt;
					int tmpLen = len;
					
					if(++tmpTurnCnt == 2) {
						tmpLen++;
						tmpTurnCnt = 0;
					}
					
					loop: while(true) {						
						for (int j = 0; j < tmpLen; j++) {
							if(tmpx == 0 && tmpy == 0) return;
							tmpx += dx[tmpDir];
							tmpy += dy[tmpDir];
							if(map[tmpx][tmpy] == map[nx][ny]) {
								boomList.offer(new Ball(tmpx, tmpy));
							}else break loop;
						}
						
						if(++tmpTurnCnt == 2) {
							tmpLen++;
							tmpTurnCnt = 0;
						}
						
						tmpDir = (tmpDir + 1) % 4;
					}
				}
				
				if(boomList.size() >= 4) {
					boomFlag = true;
					ballCnt[map[nx][ny]] += boomList.size();
					while(!boomList.isEmpty()) {
						Ball cur = boomList.poll();
						map[cur.x][cur.y] = 0;
					}
				}
				
			}
			
			if(++turnCnt == 2) {
				len++;
				turnCnt = 0;
			}
			
			dir = (dir + 1) % 4;
		}		
	}
	
	static void Gravity() {
		int nx = N / 2;
		int ny = N / 2;
		
		int dir = 0;
		int turnCnt = 0;
		int len = 1;
		
		while(true) {			
			
			loop: for (int i = 0; i < len; i++) {
				if(nx == 0 && ny == 0) return;
				nx += dx[dir];
				ny += dy[dir];
				if(map[nx][ny] != 0) continue;
				
				// 당기기
				int tmpx = nx;
				int tmpy = ny;
				for (int j = i + 1; j < len; j++) {
					tmpx += dx[dir];
					tmpy += dy[dir];
					if(tmpy == -1) return;
					if(map[tmpx][tmpy] != 0) {
						map[nx][ny] = map[tmpx][tmpy];
						map[tmpx][tmpy] = 0;
						continue loop;
					}
				}				
				
				int tmpDir = (dir + 1) % 4;;
				int tmpTurnCnt = turnCnt;
				int tmpLen = len;
				
				if(++tmpTurnCnt == 2) {
					tmpLen++;
					tmpTurnCnt = 0;
				}
				
				while(true) {
					
					for (int j = 0; j < tmpLen; j++) {
						if(tmpx == 0 && tmpy == 0) return;
						tmpx += dx[tmpDir];
						tmpy += dy[tmpDir];
						if(map[tmpx][tmpy] != 0) {
							map[nx][ny] = map[tmpx][tmpy];
							map[tmpx][tmpy] = 0;
							continue loop;
						}
					}
					
					if(++tmpTurnCnt == 2) {
						tmpLen++;
						tmpTurnCnt = 0;
					}
					
					tmpDir = (tmpDir + 1) % 4;
				}
				
			}
			
			if(++turnCnt == 2) {
				len++;
				turnCnt = 0;
			}
			
			dir = (dir + 1) % 4;
		}
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) return false;
		return true;
	}
	
	static class Group {
		int cnt, num;

		public Group(int cnt, int num) {
			super();
			this.cnt = cnt;
			this.num = num;
		}
	}
	
	static class Ball {
		int x, y;

		public Ball(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
