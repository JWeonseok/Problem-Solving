package programmers;

import java.util.Arrays;

public class KakaoBlind2021_합승택시요금 {

	public static void main(String[] args) {

		System.out.println(solution(6, 4, 6, 2, new int[][] {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}}));

	}
	
	static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        
        int[][] adjMatrix = new int[n + 1][n + 1];
        
        for (int i = 1; i < n + 1; i++) {
			Arrays.fill(adjMatrix[i], 2000001);
			adjMatrix[i][i] = 0;	
		}
        
        for (int i = 0; i < fares.length; i++) {
        	adjMatrix[fares[i][0]][fares[i][1]] = fares[i][2];
        	adjMatrix[fares[i][1]][fares[i][0]] = fares[i][2];
		}
        
        for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					adjMatrix[j][k] = Math.min(adjMatrix[j][k], adjMatrix[j][i] + adjMatrix[i][k]);
				}
			}
		}
        
        for (int i = 1; i <= n; i++) {
			answer = Math.min(answer, adjMatrix[s][i] + adjMatrix[i][a] + adjMatrix[i][b]);
		}        
        
        return answer;
    }

}
