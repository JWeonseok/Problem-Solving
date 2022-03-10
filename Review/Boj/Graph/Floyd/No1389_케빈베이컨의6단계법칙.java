package rev.graph.floyd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No1389_케빈베이컨의6단계법칙 {
	
	static int N, M, answer, kevinNum = Integer.MAX_VALUE;
	static int[][] adjMatrix;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjMatrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(adjMatrix[i], 101);
			adjMatrix[i][i] = 0;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken()) - 1;
			int u = Integer.parseInt(st.nextToken()) - 1;
			
			adjMatrix[v][u] = 1;
			adjMatrix[u][v] = 1;
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if (i == k) continue;
				for (int j = 0; j < N; j++) {
					if (i == j || j == k) continue;
					adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			int cur = 0;
			for (int j = 0; j < N; j++) {
				cur += adjMatrix[i][j];
			}
			if (kevinNum > cur) {
				answer = i;
				kevinNum = cur;
			}
		}
		
		System.out.println(answer + 1);

	}

}
