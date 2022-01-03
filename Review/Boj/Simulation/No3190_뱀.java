package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No3190_뱀 {
	
	static int N, dist = 1, answer = 0;
	static LinkedList<Point> snake;
	static int[][] map;
	static Queue<Move> q;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		int appleCnt = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < appleCnt; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			
			map[x][y] = -1;
		}
		
		map[0][0] = 1;
		
		q = new LinkedList<>();
		int moveCnt = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < moveCnt; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			q.offer(new Move(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
		}
		
		snake = new LinkedList<>();
		snake.offer(new Point(0, 0));
		
		while(true) {
			answer++;
			
			int nx = snake.peekLast().x + dx[dist];
			int ny = snake.peekLast().y + dy[dist];
			
			if(chkValid(nx, ny) && map[nx][ny] != 1) {				
				if(map[nx][ny] != -1) {
					Point tail = snake.pollFirst();
					map[tail.x][tail.y] = 0;
				}
				map[nx][ny] = 1;
				snake.offer(new Point(nx, ny));
			}else break;			
			
			if(!q.isEmpty() && answer == q.peek().t) {
				Move move = q.poll();
				if(move.d == 'L') dist = (dist + 3) % 4;
				else dist = (dist + 1) % 4;
			}
		}

		System.out.println(answer);
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) return false;
		return true;
	}
	
	static class Point{
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static class Move{
		int t;
		char d;
		public Move(int t, char d) {
			super();
			this.t = t;
			this.d = d;
		}
	}

}
