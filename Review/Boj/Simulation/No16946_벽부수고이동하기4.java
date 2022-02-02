package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No16946_벽부수고이동하기4 {
	
	static int N, M;
	static int[][] map, cntMap, group;
	static boolean[][] chk;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		cntMap = new int[N][M];
		group = new int[N][M];
		chk = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		int groupCnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] != 0 || chk[i][j]) continue;
				
				Queue<Point> q = new LinkedList<>();
				ArrayList<Point> cntList = new ArrayList<>();
				
				q.offer(new Point(i, j));
				cntList.add(new Point(i, j));
				chk[i][j] = true;
				
				int cnt = 1;
				while(!q.isEmpty()) {
					Point cur = q.poll();
					
					for (int k = 0; k < 4; k++) {
						int nx = cur.x + dx[k];
						int ny = cur.y + dy[k];
						
						if(chkValid(nx, ny) && map[nx][ny] == 0 && !chk[nx][ny]) {
							q.offer(new Point(nx, ny));
							cntList.add(new Point(nx, ny));
							chk[nx][ny] = true;
							cnt++;
						}
					}
				}
				
				for(Point p : cntList) {
					cntMap[p.x][p.y] = cnt;
					group[p.x][p.y] = groupCnt;
				}
				groupCnt++;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					sb.append('0');
					continue;
				}
				
				HashSet<Integer> set = new HashSet<>();
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if(chkValid(nx, ny) && !set.contains(group[nx][ny])) {
						map[i][j] += cntMap[nx][ny];
						set.add(group[nx][ny]);
					}
				}
				
				sb.append(map[i][j] % 10);
			}
			sb.append('\n');
		}
		
		System.out.println(sb);

	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= M) return false;
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
