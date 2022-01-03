package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No15663_N과M9 {
	
	static int N, M;
	static ArrayList<String> chkList;
	static int[] permAry, numAry;
	static boolean[] chk;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		chkList = new ArrayList<>();
		permAry = new int[M];
		numAry = new int[N];
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
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < M; i++) {
				str.append(permAry[i]).append(' ');
			}
			if(chkValid(str.toString())) {
				chkList.add(str.toString());
				sb.append(str).append('\n');
			}
			return;
		}
		
		for (int i = 0; i < numAry.length; i++) {
			if(chk[i]) continue;
			
			permAry[ind] = numAry[i];
			chk[i] = true;
			perm(ind + 1);
			chk[i] = false;
		}
		
		
	}
	
	static boolean chkValid(String str) {
		for (int i = 0, size = chkList.size(); i < size; i++) {
			if(chkList.get(i).equals(str)) return false;
		}
		return true;
	}

}
