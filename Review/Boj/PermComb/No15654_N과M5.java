package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No15654_N과M5 {
	
	static int N, M;
	static int[] numAry, permAry;
	static boolean[] chk;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		numAry = new int[N];
		permAry = new int[M];
		chk = new boolean[N];
		
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
			if(chk[i]) continue;
			
			permAry[ind] = numAry[i];
			chk[i] = true;
			perm(ind + 1);
			chk[i] = false;
		}
	}

}
