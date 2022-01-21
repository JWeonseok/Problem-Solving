package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No17070_파이프옮기기1 {
	
	static int N, answer = 0;
	static int[][] map;
	static Queue<Pipe> q;
	
	static int[] dx = {0, 1, 1};
	static int[] dy = {1, 0, 1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		q = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if(map[N - 1][N - 1] == 1) {
			System.out.println(0);
			return;
		}
		
		q.offer(new Pipe(0, 1, 0));
		
		loop: while(!q.isEmpty()) {
			Pipe cur = q.poll();
			
			if(cur.x == N - 1 && cur.y == N - 1) {
				answer++;
				continue loop;
			}
			
			if(cur.d == 0) {
				int nx = cur.x + dx[0];
				int ny = cur.y + dy[0];
				if(chkValid(nx, ny) && map[nx][ny] == 0) q.offer(new Pipe(nx, ny, 0));
				
				for (int i = 0; i < 3; i++) {
					nx = cur.x + dx[i];
					ny = cur.y + dy[i];
					
					if(!chkValid(nx, ny) || map[nx][ny] == 1) continue loop;
				}
				
				q.offer(new Pipe(cur.x + 1, cur.y + 1, 2));
			}else if(cur.d == 1) {
				int nx = cur.x + dx[1];
				int ny = cur.y + dy[1];
				if(chkValid(nx, ny) && map[nx][ny] == 0) q.offer(new Pipe(nx, ny, 1));
				
				for (int i = 0; i < 3; i++) {
					nx = cur.x + dx[i];
					ny = cur.y + dy[i];
					
					if(!chkValid(nx, ny) || map[nx][ny] == 1) continue loop;
				}
				
				q.offer(new Pipe(cur.x + 1, cur.y + 1, 2));
			}else {
				int nx = cur.x + dx[0];
				int ny = cur.y + dy[0];
				if(chkValid(nx, ny) && map[nx][ny] == 0) q.offer(new Pipe(nx, ny, 0));
				
				nx = cur.x + dx[1];
				ny = cur.y + dy[1];
				if(chkValid(nx, ny) && map[nx][ny] == 0) q.offer(new Pipe(nx, ny, 1));
				
				for (int i = 0; i < 3; i++) {
					nx = cur.x + dx[i];
					ny = cur.y + dy[i];
					
					if(!chkValid(nx, ny) || map[nx][ny] == 1) continue loop;
				}
				
				q.offer(new Pipe(cur.x + 1, cur.y + 1, 2));
			}
		}
		
		System.out.println(answer);

	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) return false;
		return true;
	}
	
	static class Pipe {
		int x, y, d;

		public Pipe(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

}
