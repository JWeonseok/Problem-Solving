package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No20058_마법사상어와파이어스톰 {
	
	static int N, Q, size, iceSum = 0, iceSize = 0;
	static int[][] map;
	static int[] L;

	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		size = (int)Math.pow(2, N);
		map = new int[size][size];
		L = new int[Q];
		
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			L[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < Q; i++) {
			Tornado(L[i]);
			MeltIce();
		}
		
		CntIce();
		
		System.out.println(iceSum);
		System.out.println(iceSize);

	}
	
	static void CntIce() {
		
		boolean[][] chk = new boolean[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				iceSum += map[i][j];
				
				if(map[i][j] == 0 || chk[i][j]) continue;
				
				int cnt = 1;
				Queue<Ice> q = new LinkedList<>();
				q.offer(new Ice(i, j));
				chk[i][j] = true;
				
				while(!q.isEmpty()) {
					Ice cur = q.poll();
					
					for (int k = 0; k < 4; k++) {
						int nx = cur.x + dx[k];
						int ny = cur.y + dy[k];
						
						if(chkValid(nx, ny) && map[nx][ny] != 0 && !chk[nx][ny]) {
							q.offer(new Ice(nx, ny));
							chk[nx][ny] = true;
							cnt++;
						}
					}
				}
				
				iceSize = Math.max(iceSize, cnt);
			}
		}
	}
	
	static void MeltIce() {
		ArrayList<Ice> iceList = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(map[i][j] == 0) continue;
				int cnt = 0;
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					
					if(chkValid(nx, ny) && map[nx][ny] != 0) cnt++;
				}
				
				if(cnt < 3) {
					iceList.add(new Ice(i, j));
				}
			}
		}
		
		for(Ice ice : iceList) map[ice.x][ice.y]--;
	}
	
	static void Tornado(int L) {
		if(L == 0) return;
		int s = (int)Math.pow(2, L);
		
		for (int i = 0; i < size; i += s) {
			for (int j = 0; j < size; j += s) {
				rotate(i, j, s);
			}
		}
	}
	
	static void rotate(int x, int y, int cnt) {
		if(cnt == 0) return;
		
		for (int i = 0; i < cnt - 1; i++) {
			int nx = x;
			int ny = y;
			int dir = 0;
			int tmp = map[x][y];
			
			while(true) {
				if(nx == x && ny == y + 1) break;				
				if(nx + dx[dir] >= x + cnt || ny + dy[dir] >= y + cnt || nx + dx[dir] < x || ny + dy[dir] < y) {
					dir = (dir + 1) % 4;
					continue;
				}
				
				map[nx][ny] = map[nx + dx[dir]][ny + dy[dir]];
				nx += dx[dir];
				ny += dy[dir];
			}
			
			map[x][y + 1] = tmp;
		}
		
		rotate(x + 1, y + 1, cnt - 2);
		
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= size || y < 0 || y >= size) return false;
		return true;
	}
	
	static class Ice {
		int x, y;

		public Ice(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
