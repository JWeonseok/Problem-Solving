package rev.graph.floyd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class No2660_회장뽑기 {
	
	static int N, chairmanScore = Integer.MAX_VALUE;
	static int[][] adjMatrix;
	static ArrayList<Integer> chairman;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		chairman = new ArrayList<>();
		adjMatrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(adjMatrix[i], 50);
			adjMatrix[i][i] = 0;
		}
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			
			if (v == -1) break;
			
			adjMatrix[v - 1][u - 1] = 1;
			adjMatrix[u - 1][v - 1] = 1;
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if (k == i) continue;
				for (int j = 0; j < N; j++) {
					if (i == j || k == j) continue;
					adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
				}
			}
		}
		
		loop: for (int i = 0; i < N; i++) {
			int score = Integer.MIN_VALUE;
			for (int j = 0; j < N; j++) {
				if (adjMatrix[i][j] == Integer.MAX_VALUE) continue loop;
				score = Math.max(score, adjMatrix[i][j]);
			}
			if (score > 0) {
				if (chairmanScore >= score) {
					if (chairmanScore > score) chairman.clear();
					chairmanScore = score;
					chairman.add(i + 1);
				}
			}
		}
		
		Collections.sort(chairman);
		StringBuilder answer = new StringBuilder();
		answer.append(chairmanScore).append(' ').append(chairman.size()).append('\n');
		for (Integer in : chairman) {
			answer.append(in).append(' ');
		}
		
		System.out.println(answer);

	}

}
