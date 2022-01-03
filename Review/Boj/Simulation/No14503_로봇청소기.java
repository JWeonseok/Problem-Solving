package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No14503_로봇청소기 {
	
	static int N, M, answer = 0;
	static int[][] map;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		Robot robot = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		loop: while(true) {
			if(map[robot.x][robot.y] == 0) {
				map[robot.x][robot.y] = -1;
				answer++;
			}
			
			int dist = robot.d;
			for (int i = 0; i < 4; i++) {
				dist = (dist + 3) % 4;
				int nx = robot.x + dx[dist];
				int ny = robot.y + dy[dist];
				
				if(chkValid(nx, ny) && map[nx][ny] == 0) {
					robot.x = nx;
					robot.y = ny;
					robot.d = dist;
					continue loop;
				}
			}
			
			int back = (robot.d + 2) % 4;
			int bx = robot.x + dx[back];
			int by = robot.y + dy[back];
			
			if(chkValid(bx, by) && map[bx][by] != 1) {
				robot.x = bx;
				robot.y = by;
			}else break;
			
		}
		
		System.out.println(answer);

	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= M) return false;
		return true;
	}
	
	static class Robot{
		int x, y, d;

		public Robot(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

}
