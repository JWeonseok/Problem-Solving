package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No15469_N과M1 {
	
	static int N, M;
	static int[] tgt;
	static boolean[] chk;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sb = new StringBuilder();
		tgt = new int[M];
		chk = new boolean[N];
		
		perm(0);
		
		System.out.println(sb);
	}
	
	static void perm(int ind) {
		if(ind == M) {
			for (int i = 0; i < M; i++) {
				sb.append(tgt[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(chk[i]) continue;
			
			tgt[ind] = i + 1;
			chk[i] = true;
			perm(ind + 1);
			chk[i] = false;
		}
	}
	
	

}
