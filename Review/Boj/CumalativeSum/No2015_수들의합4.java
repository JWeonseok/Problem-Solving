package rev.cumalativesum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class No2015_수들의합4 {
	
	static int N, K;
	static long answer = 0;
	static int[] nums;
	static HashMap<Integer, Long> map;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		nums = new int[N + 1];
		map = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			nums[i] = nums[i - 1] + Integer.parseInt(st.nextToken());
			
			if (nums[i] == K) answer++;
			
			if (map.containsKey(nums[i] - K)) answer += map.get(nums[i] - K);
			
			if (map.containsKey(nums[i])) map.put(nums[i], map.get(nums[i]) + 1);
			else map.put(nums[i], 1L);
		}
		
		System.out.println(answer);

	}

}
