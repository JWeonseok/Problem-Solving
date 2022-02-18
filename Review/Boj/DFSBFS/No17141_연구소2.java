package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No17141_연구소2 {
	
	static int N, M, answer = Integer.MAX_VALUE;
	static int[][] map, copyMap;
	static ArrayList<Point> virusList;
	static int[] tgt;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		map = new int[N][N];
		copyMap = new int[N][N];
		virusList = new ArrayList<>();
		tgt = new int[M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					map[i][j] = 0;
					virusList.add(new Point(i, j));
				}
			}
		}
		
		comb(0, 0);
		
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

	}
	
	static void bfs() {
		copyMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		
		Queue<Point> q = new LinkedList<>();
		boolean[][] chk = new boolean[N][N];
		
		for (int i = 0; i < M; i++) {
			Point p = virusList.get(tgt[i]);
			q.offer(p);
			copyMap[p.x][p.y] = 2;
			chk[p.x][p.y] = true;
		}
		
		int cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				Point cur = q.poll();
				
				for (int j = 0; j < 4; j++) {
					int nx = cur.x + dx[j];
					int ny = cur.y + dy[j];
					if (ChkValid(nx, ny) && copyMap[nx][ny] == 0 && !chk[nx][ny]) {
						q.offer(new Point(nx, ny));
						chk[nx][ny] = true;
						copyMap[nx][ny] = 2;
					}
				}
			}
			cnt++;
		}
		
		if (ChkSpread()) answer = Math.min(answer, cnt - 1);
	}
	
	static boolean ChkSpread() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (copyMap[i][j] == 0) return false;
			}
		}
		return true;
	}
	
	static void comb(int tgtind, int srcind) {
		if (tgtind == M) {
			bfs();
			return;
		}
		
		if (srcind == virusList.size()) return;
		
		tgt[tgtind] = srcind;
		comb(tgtind + 1, srcind + 1);
		comb(tgtind, srcind + 1);
	}
	
	static boolean ChkValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) return false;
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
