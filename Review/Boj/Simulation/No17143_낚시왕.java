package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No17143_낚시왕 {
	
	static int R, C, M, fisherLoc = -1, answer = 0;
	static int[][] map;
	static int[] sharkInd;
	static boolean[] sharkAlive;
	static ArrayList<Shark> sharkList;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		sharkInd = new int[10001];
		Arrays.fill(sharkInd, -1);
		
		sharkList = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			
			map[x][y] = z;
			
			sharkInd[z] = sharkList.size();
			sharkList.add(new Shark(x, y, s, d, z));
		}
		
		sharkAlive = new boolean[sharkList.size()];
		
		while(fisherLoc < C - 1) {
			Fishing();
			SharkMove();			
		}
		
		System.out.println(answer);

	}
	
	static void SharkMove() {
		for (Shark cur : sharkList) {
			if(sharkAlive[sharkInd[cur.z]]) continue;
			
			for (int j = 0; j < cur.s; j++) {
				if(!ChkValid(cur.x + dx[cur.d], cur.y + dy[cur.d])) {
					if(cur.d == 0) cur.d = 1;
					else if(cur.d == 1) cur.d = 0;
					else if(cur.d == 2) cur.d = 3;
					else if(cur.d == 3) cur.d = 2;
					j--;
				}else {
					cur.x += dx[cur.d];
					cur.y += dy[cur.d];
				}
			}
		}
		
		map = new int[R][C];
		
		for (Shark cur : sharkList) {
			if(sharkAlive[sharkInd[cur.z]]) continue;
			
			if(map[cur.x][cur.y] > cur.z) {
				sharkAlive[sharkInd[cur.z]] = true;
			}else {
				if(map[cur.x][cur.y] != 0) sharkAlive[sharkInd[map[cur.x][cur.y]]] = true;
				map[cur.x][cur.y] = cur.z;
			}
			
		}
	}
	
	static void Fishing() {
		fisherLoc++;
		
		for (int i = 0; i < R; i++) {
			if(map[i][fisherLoc] != 0) {
				answer += map[i][fisherLoc];
				sharkAlive[sharkInd[map[i][fisherLoc]]] = true;
				map[i][fisherLoc] = 0;
				return;
			}
		}
	}
	
	static boolean ChkValid(int x, int y) {
		if(x < 0 || x >= R || y < 0 || y >= C) return false;
		return true;
	}
	
	static class Shark {
		int x, y, s, d, z;

		public Shark(int x, int y, int s, int d, int z) {
			super();
			this.x = x;
			this.y = y;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}

}
