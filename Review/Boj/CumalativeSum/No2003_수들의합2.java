package rev.cumalativesum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No2003_수들의합2 {
	
	static int N, M, answer = 0;
	static int[] nums;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			nums[i] = nums[i - 1] + Integer.parseInt(st.nextToken());
		}
		
		int left = 0, right = 1;		
		while (left < N) {
			int val = nums[right] - nums[left];
			if (val == M) answer++;
			
			if (right == N) {
				left++;
				continue;
			}
			if (val >= M) left++;
			else right++;
		}
		
		System.out.println(answer);

	}

}
