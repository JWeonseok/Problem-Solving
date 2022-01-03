package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No15656_N과M7 {
	
	static int N, M;
	static int[] numAry, permAry;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sb = new StringBuilder();
		numAry = new int[N];
		permAry = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numAry[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(numAry);
		
		perm(0);
		
		System.out.println(sb);

	}
	
	static void perm(int ind) {
		if(ind == M) {
			for (int i = 0; i < M; i++) {
				sb.append(permAry[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for (int i = 0; i < N; i++) {
			permAry[ind] = numAry[i];
			perm(ind + 1);
		}
	}

}
