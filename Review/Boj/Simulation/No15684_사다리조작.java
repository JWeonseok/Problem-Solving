package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No15684_사다리조작 {
	
	static int N, M, H, answer = -1;
	static int[][] map;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][N - 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			map[a][b] = 1;					
		}
		
		for (int i = 0; i <= 3; i++) {
			if(answer != -1) break;
			dfs(0, 0, 0, i);
		}
		
		System.out.println(answer);

	}
	
	static void dfs(int h, int w, int cnt, int min) {
		if(cnt == min) {
			if(chkLadder()) answer = min;
			return;
		}
		
		if(h == H) return;
		
		if(map[h][w] == 1 || 
				(((w + 1) < N - 1) && map[h][w + 1] == 1) || 
				(((w - 1) >= 0) && map[h][w - 1] == 1)) dfs(h + (w + 1) / (N - 1), (w + 1) % (N - 1), cnt, min);
		else {
			map[h][w] = 1;
			dfs(h + (w + 1) / (N - 1), (w + 1) % (N - 1), cnt + 1, min);
			map[h][w] = 0;
			dfs(h + (w + 1) / (N - 1), (w + 1) % (N - 1), cnt, min);
		}		
	}
	
	static boolean chkLadder() {
		for (int i = 0; i < N; i++) {
			int num = i;
			
			for (int j = 0; j < H; j++) {
				if(num < N - 1 && map[j][num] == 1) num++;
				else if(num - 1 >= 0 && map[j][num - 1] == 1) num--;
			}
			if(num != i) return false;
		}
		return true;
	}

}
