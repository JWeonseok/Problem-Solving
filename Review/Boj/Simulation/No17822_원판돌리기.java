package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No17822_원판돌리기 {
	
	static int N, M, T, answer = 0;
	static int[][] rounds, rotates;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		rounds = new int[N][M];
		rotates = new int[T][3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				rounds[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				rotates[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < T; i++) {
			Rotates(i);
			RemoveNumber();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (rounds[i][j] == -1) continue;
				answer += rounds[i][j];
			}
		}
		
		System.out.println(answer);

	}
	
	static void RemoveNumber() {
		
		boolean removeFlag = false;
		double sum = 0, cnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (rounds[i][j] == -1) continue;
				
				sum += rounds[i][j];
				cnt++;
				
				boolean sameFlag = false;
				Queue<Point> q = new LinkedList<>();
				boolean[][] chk = new boolean[N][M];
				q.offer(new Point(i, j));
				chk[i][j] = true;
				
				while (!q.isEmpty()) {
					Point cur = q.poll();
					
					for (int k = 0; k < 4; k++) {
						int nx = cur.x + dx[k];
						int ny = (cur.y + dy[k] + M) % M;
						
						if (ChkValid(nx, ny) && rounds[cur.x][cur.y] == rounds[nx][ny] && !chk[nx][ny]) {
							q.offer(new Point(nx, ny));
							chk[nx][ny] = true;
							sameFlag = true;
							removeFlag = true;
						}
					}
					
					if (sameFlag) rounds[cur.x][cur.y] = -1;
				}
			}
		}
		
		if (!removeFlag) {
			double avg = sum / cnt;
			System.out.println(sum + " " + cnt + " " + avg);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (rounds[i][j] == -1) continue;
					
					if (rounds[i][j] > avg) rounds[i][j]--;
					else if (rounds[i][j] < avg) rounds[i][j]++;
				}
			}
		}
		
	}
	
	static void Rotates(int num) {
		int x = rotates[num][0];
		int d = rotates[num][1];
		int k = rotates[num][2];
		
		int ind = 1;
		
		while (true) {
			if (ind * x - 1 >= N) break;
			
			int[] round = rounds[ind * x - 1];
			
			if (d == 0) {
				for (int i = 0; i < k; i++) {
					int tmp = round[M - 1];				
					for (int j = M - 1; j > 0; j--) {
						round[j] = round[j - 1];
					}
					round[0] = tmp;
				}
				
			} else if (d == 1) {
				for (int i = 0; i < k; i++) {
					int tmp = round[0];
					for (int j = 0; j < M - 1; j++) {
						round[j] = round[j + 1];
					}
					round[M - 1] = tmp;
				}
			}
			
			ind++;
		}
	}
	
	static boolean ChkValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M) return false;
		return true;
	}
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
