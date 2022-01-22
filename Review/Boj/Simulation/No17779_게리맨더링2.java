package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No17779_게리맨더링2 {
	
	static int N, answer = Integer.MAX_VALUE;
	static int[][] map;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int d1 = 1; d1 < N; d1++) {
					if(j - d1 < 1) break;
					for (int d2 = 1; d2 < N; d2++) {
						if(j + d2 > N || i + d1 + d2 > N) break;
						cntPopulation(i, j, d1, d2);
					}
				}
			}
		}
		
		System.out.println(answer);

	}
	
	static void cntPopulation(int x, int y, int d1, int d2) {
		int[] pop = new int[6];
		
		boolean[][] chk = new boolean[N + 1][N + 1];
		
		for (int i = 0; i <= d1; i++) {
			chk[x + i][y - i] = true;
			chk[x + d2 + i][y + d2 - i] = true;
		}
		
		for (int i = 0; i <= d2; i++) {
			chk[x + i][y + i] = true;
			chk[x + d1 + i][y - d1 + i] = true;
		}
		
		for (int i = 1; i <= N; i++) {
			int sind = -1, eind = -1;
			for (int j = 1; j <= N; j++) {
				if(chk[i][j]) {
					if(sind == -1) sind = j;
					else eind = j;
				}
			}
			if(sind != -1 && eind != -1) {
				for (int j = sind; j < eind; j++) {
					chk[i][j] = true;
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(chk[i][j]) pop[5] += map[i][j];
				else {
					if(1 <= i && i < x + d1 && 1 <= j && j <= y) pop[1] += map[i][j];
					else if(1 <= i && i <= x + d2 && y < j && j <= N) pop[2] += map[i][j];
					else if(x + d1 <= i && i <= N && 1 <= j && j < y - d1 + d2) pop[3] += map[i][j];
					else if(x + d2 < i && i <= N && y - d1 + d2 <= j && j <= N) pop[4] += map[i][j];
				} 
			}
		}
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= 5; i++) {
			min = Math.min(min, pop[i]);
			max = Math.max(max, pop[i]);			
		}
		
		answer = Math.min(answer, max - min);
	}

}
