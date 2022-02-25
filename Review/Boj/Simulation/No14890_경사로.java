package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class No14890_경사로 {
	
	static int N, L, answer = 0;
	static int[][] map;
	static ArrayList<Road>[] row, col;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		row = new ArrayList[N];
		col = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			row[i] = new ArrayList<>();
			col[i] = new ArrayList<>();
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for (int i = 0; i < N; i++) {
			int num = map[i][0];
			int l = 1;
			for (int j = 1; j < N; j++) {
				if (map[i][j] == num) l++;
				else {
					row[i].add(new Road(num, l));
					num = map[i][j];
					l = 1;
				}
			}
			row[i].add(new Road(num, l));
		}
		
		for (int i = 0; i < N; i++) {
			int num = map[0][i];
			int l = 1;
			for (int j = 1; j < N; j++) {
				if (map[j][i] == num) l++;
				else {
					col[i].add(new Road(num, l));
					num = map[j][i];
					l = 1;
				}
			}
			col[i].add(new Road(num, l));
		}
		
		
		loop: for (int i = 0; i < N; i++) {
			if (row[i].size() == 1) {
				answer++;
				continue;
			}
			
			int size = row[i].size();
			for (int j = 0; j < size - 1; j++) {
				Road prevR = row[i].get(j);
				Road nextR = row[i].get(j + 1);
				if (Math.abs(prevR.h - nextR.h) > 1) continue loop;
				else if (prevR.h < nextR.h) {
					if (prevR.l < L) continue loop;
					prevR.l -= L;
				} else if (prevR.h > nextR.h) {
					if (nextR.l < L) continue loop;
					nextR.l -= L;
				}
				
			}
			answer++;
		}
		
		loop: for (int i = 0; i < N; i++) {
			if (col[i].size() == 1) {
				answer++;
				continue;
			}
			
			int size = col[i].size();
			for (int j = 0; j < size - 1; j++) {
				Road prevR = col[i].get(j);
				Road nextR = col[i].get(j + 1);
				if (Math.abs(prevR.h - nextR.h) > 1) continue loop;
				else if (prevR.h < nextR.h) {
					if (prevR.l < L) continue loop;
					prevR.l -= L;
				} else if (prevR.h > nextR.h) {
					if (nextR.l < L) continue loop;
					nextR.l -= L;
				}
				
			}
			answer++;
		}
		
		System.out.println(answer);

	}
	
	static class Road {
		int h, l;

		public Road(int h, int l) {
			super();
			this.h = h;
			this.l = l;
		}
	}

}
