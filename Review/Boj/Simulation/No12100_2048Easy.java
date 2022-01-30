package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No12100_2048Easy {
	
	static int N, answer = 0;
	static int[][] map;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
				
		System.out.println(answer);

	}
	
	static void chkMaxVal() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer = Math.max(answer, map[i][j]);
			}
		}
	}
	
	static void dfs(int cnt) {
		if(cnt == 5) {
			chkMaxVal();
			return;
		}
		
		int[][] copyMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		
		for (int i = 0; i < 4; i++) {
			Gravity(i);
			CombineBlock(i);
			Gravity(i);
			
			dfs(cnt + 1);
			
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					map[j][k] = copyMap[j][k];
				}
			}
		}
		
		
	}
	
	static void CombineBlock(int num) {
		if(num == 0) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					if(map[j][i] == 0) continue;
					
					if(map[j][i] == map[j + 1][i]) {
						map[j][i] *= 2;
						map[j + 1][i] = 0;
					}
				}
			}
		}else if(num == 1) {
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j > 0; j--) {
					if(map[i][j] == 0) continue;
					
					if(map[i][j] == map[i][j - 1]) {
						map[i][j] *= 2;
						map[i][j - 1] = 0;
					}
				}
			}			
		}else if(num == 2) {
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j > 0; j--) {
					if(map[j][i] == 0) continue;
					
					if(map[j][i] == map[j - 1][i]) {
						map[j][i] *= 2;
						map[j - 1][i] = 0;
					}
				}
			}
		}else if(num == 3) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					if(map[i][j] == 0) continue;
					
					if(map[i][j] == map[i][j + 1]) {
						map[i][j] *= 2;
						map[i][j + 1] = 0;
					}
				}
			}
		}
	}
	
	static void Gravity(int num) {
		
		if(num == 0) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					if(map[j][i] != 0) continue;
					
					for (int k = j + 1; k < N; k++) {
						if(map[k][i] == 0) continue;
						
						map[j][i] = map[k][i];
						map[k][i] = 0;
						break;
					}
				}
			}
		}else if(num == 1) {
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j > 0; j--) {
					if(map[i][j] != 0) continue;
					
					for (int k = j - 1; k >= 0; k--) {
						if(map[i][k] == 0) continue;
						
						map[i][j] = map[i][k];
						map[i][k] = 0;
						break;
					}
				}
			}			
		}else if(num == 2) {
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j > 0; j--) {
					if(map[j][i] != 0) continue;
					
					for (int k = j - 1; k >= 0; k--) {
						if(map[k][i] == 0) continue;
						
						map[j][i] = map[k][i];
						map[k][i] = 0;
						break;
					}
				}
			}
		}else if(num == 3) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					if(map[i][j] != 0) continue;
					
					for (int k = j + 1; k < N; k++) {
						if(map[i][k] == 0) continue;
						
						map[i][j] = map[i][k];
						map[i][k] = 0;
						break;
					}
				}
			}
		}
		
	}
}
