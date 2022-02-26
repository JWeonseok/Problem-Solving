package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No17135_캐슬디펜스 {
	
	static int N, M, D, answer = Integer.MIN_VALUE, allEnemy = 0, enemyCnt, killEnemy;
	static int[][] map, copyMap;
	static int[] archers;
	
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		archers = new int[3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) allEnemy++;
			}
		}
		
		Combination(0, 0);
		
		System.out.println(answer);

	}
	
	static void printMap() {
		
	}
	
	static void Play() {
		enemyCnt = allEnemy;
		killEnemy = 0;
		copyMap = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		
		while (true) {
			if (enemyCnt == 0) {
				answer = Math.max(answer, killEnemy);
				return;
			}
			
			Attack();
			Move();
		}
	}
	
	static void Move() {
		for (int i = 0; i < M; i++) {
			if (copyMap[N - 1][i] == 1) enemyCnt--;
			for (int j = N - 1; j > 0; j--) {
				copyMap[j][i] = copyMap[j - 1][i];
			}
			copyMap[0][i] = 0;
		}
	}
	
	static void Attack() {
		ArrayList<Point> removeEnemy = new ArrayList<>();
		
		loop: for (int i = 0; i < 3; i++) {
			Queue<Point> q = new LinkedList<>();
			boolean[][] chk = new boolean[N][M];
			q.offer(new Point(N, archers[i]));
			
			int cnt = 0;
			while (!q.isEmpty()) {
				if (cnt == D) break;
				
				int size = q.size();
				for (int j = 0; j < size; j++) {
					Point cur = q.poll();
					
					for (int k = 0; k < 4; k++) {
						int nx = cur.x + dx[k];
						int ny = cur.y + dy[k];
						
						if (ChkValid(nx, ny) && !chk[nx][ny]) {
							if (copyMap[nx][ny] == 1) {
								removeEnemy.add(new Point(nx, ny));
								continue loop;
							}
							q.offer(new Point(nx, ny));
							chk[nx][ny] = true;
						}
					}
				}
				cnt++;
			}
		}
		
		for (Point p : removeEnemy) {
			if (copyMap[p.x][p.y] != 0) {
				copyMap[p.x][p.y] = 0;
				killEnemy++;
				enemyCnt--;
			}			
		}
	}
	
	static void Combination(int tgtInd, int srcInd) {
		if (tgtInd == 3) {
			Play();
			return;
		}
		
		if (srcInd == M) return;
		
		archers[tgtInd] = srcInd;
		Combination(tgtInd + 1, srcInd + 1);
		Combination(tgtInd, srcInd + 1);
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
