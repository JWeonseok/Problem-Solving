package rev.cumalativesum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No1821_수들의합6 {
	
	static int N, F;
	static int[] nums, tgt;
	static boolean[] chk;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		nums = new int[N];
		tgt = new int[N];
		chk = new boolean[N];
		for (int i = 0; i < N; i++) {
			nums[i] = i + 1;
		}
		
		perm(0);

	}
	
	static void perm(int ind) {
		if (ind == N) {
			Find();
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (chk[i]) continue;
			tgt[ind] = nums[i];
			chk[i] = true;
			perm(ind + 1);
			chk[i] = false;
		}
	}
	
	static void Find() {
		int[] tmp = new int[N];
		for (int i = 0; i < N; i++) {
			tmp[i] = tgt[i];
		}
		
		int start = 0;
		while (true) {
			if (start == N - 1) break;
			for (int i = N - 1; i > start; i--) {
				tmp[i] += tmp[i - 1];
			}
			start++;
			
		}
		if (tmp[N - 1] == F) {
			for (Integer in : tgt) {
				System.out.print(in + " ");
			}
			System.exit(0);
		}
	}

}
