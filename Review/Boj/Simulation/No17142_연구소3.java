package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No17142_연구소3 {
	
	static int N, M, answer = Integer.MAX_VALUE;
	static int[][] map, copyMap;
	static int[] tgt;
	static ArrayList<Point> virus;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		copyMap = new int[N][N];
		tgt = new int[M];
		virus = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					map[i][j] = -1;
					virus.add(new Point(i, j));
				}
			}
		}
		
		comb(0, 0);
		
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
		
	}
	
	static void spread() {
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		
		Queue<Point> q = new LinkedList<>();
		int cnt = 0;
		
		for (int i = 0; i < M; i++) {
			q.offer(virus.get(tgt[i]));
		}
		
		while(!q.isEmpty()) {
			int size = q.size();
			cnt++;
			
			if(chkMap()) {
				answer = Math.min(answer, cnt - 1);
				break;
			}
			
			for (int i = 0; i < size; i++) {
				Point cur = q.poll();
				
				for (int j = 0; j < 4; j++) {
					int nx = cur.x + dx[j];
					int ny = cur.y + dy[j];
					
					if(chkValid(nx, ny) && copyMap[nx][ny] <= 0) {
						q.offer(new Point(nx, ny));
						copyMap[nx][ny] = cnt;
					}
				}
			}
		}
		
		
	}
	
	static boolean chkMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(copyMap[i][j] == 0) return false;
			}
		}
		return true;
	}
	
	static void comb(int tgtind, int srcind) {
		if(tgtind == M) {
			spread();
			return;
		}
		
		if(srcind == virus.size()) return;
		
		tgt[tgtind] = srcind;
		comb(tgtind + 1, srcind + 1);
		comb(tgtind, srcind + 1);
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) return false;
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
