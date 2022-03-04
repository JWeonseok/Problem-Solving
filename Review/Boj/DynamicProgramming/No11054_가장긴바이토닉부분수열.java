package rev.dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No11054_가장긴바이토닉부분수열 {
	
	static int N;
	static int[] nums, dp1, dp2;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		dp1 = new int[N];
		dp2 = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			dp1[i] = 1;			
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) dp1[i] = Math.max(dp1[i], dp1[j] + 1);
			}
		}
		
		for (int i = N - 1; i >= 0; i--) {
			dp2[i] = 1;
			for (int j = N - 1; j > i; j--) {
				if (nums[i] > nums[j]) dp2[i] = Math.max(dp2[i], dp2[j] + 1);
			}
		}
		
		int answer = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) answer = Math.max(answer, dp1[i] + dp2[i] - 1);
		
		System.out.println(answer);

	}

}
