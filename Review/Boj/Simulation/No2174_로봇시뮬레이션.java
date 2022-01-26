package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class No2174_로봇시뮬레이션 {
	
	static int A, B, N, M;
	static ArrayList<Robot> robots;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		robots = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			char distChar = st.nextToken().charAt(0); 
			int dist = 0;
			
			if(distChar == 'N') dist = 0;
			else if(distChar == 'E') dist = 1;
			else if(distChar == 'S') dist = 2;
			else if(distChar == 'W') dist = 3;
			
			robots.add(new Robot(x, y, dist));
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int robotNum = Integer.parseInt(st.nextToken()) - 1;
			char opType = st.nextToken().charAt(0);
			int iter = Integer.parseInt(st.nextToken());
			
			if(opType == 'F') {
				Robot cur = robots.get(robotNum);
				int nx = cur.x;
				int ny = cur.y;
				for (int j = 0; j < iter; j++) {
					nx += dx[cur.d];
					ny += dy[cur.d];
					

					if(!chkValid(nx, ny)) {
						System.out.println(nx + " " + ny);
						System.out.println("Robot " + (int)(robotNum + 1) + " crashes into the wall");
						return;
					}else {
						int res = chkRobots(nx, ny);
						if(res != -1) {
							System.out.println("Robot " + (int)(robotNum + 1) + " crashes into robot " + res);
							return;
						}
					}
				}
				robots.get(robotNum).x = nx;
				robots.get(robotNum).y = ny;
			}else {
				iter %= 4;
				int dist = robots.get(robotNum).d;
				for (int j = 0; j < iter; j++) {
					if(opType == 'R') {
						 dist = (dist + 1) % 4;
					}else {
						dist = (dist + 3) % 4;
					}
				}
				robots.get(robotNum).d = dist;
			}
		}

		System.out.println("OK");
	}
	
	static int chkRobots(int x, int y) {
		for (int i = 0; i < robots.size(); i++) {
			Robot cur = robots.get(i);
			if(cur.x == x && cur.y == y) return i + 1;
		}
		return -1;
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 1 || x > A || y < 1 || y > B) return false;
		return true;
	}
	
	static class Robot {
		int x, y, d;

		public Robot(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

}
