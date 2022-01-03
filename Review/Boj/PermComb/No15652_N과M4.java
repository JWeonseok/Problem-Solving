package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No15652_N과M4 {
	
	static int N, M;
	static int[] combAry;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sb = new StringBuilder();
		combAry = new int[M];
		
		comb(0, 1);
		
		System.out.println(sb);

	}
	
	static void comb(int tgtind, int srcind) {
		if(tgtind == M) {
			for (int i = 0; i < M; i++) {
				sb.append(combAry[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for (int i = srcind; i <= N; i++) {
			combAry[tgtind] = i;
			comb(tgtind + 1, i);
		}
	}

}
