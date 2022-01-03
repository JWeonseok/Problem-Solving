package rev;

import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class No15651_N과M3 {
	
	static int N, M;
	static int[] permAry;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sb = new StringBuilder();
		permAry = new int[M];
		
		perm(0);
		
		System.out.println(sb);

	}
	
	static void perm(int ind) {
		if(ind == M) {
			for (int i = 0; i < M; i++) {
				sb.append(permAry[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			permAry[ind] = i + 1;
			perm(ind + 1);
		}
	}

}
