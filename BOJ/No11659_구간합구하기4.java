package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 211213 월 구간 합 구하기 4
 * 
 * 누적합을 응용하는 기본 문제
 * N과 M의 최대 범위가 100000이기 때문에 그냥 구간 합을 구하면 시간 초과가 발생한다.
 * */

public class No11659_구간합구하기4 {
	
	static int N, M;
	static int[] nums;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 2; i <= N; i++) {
			nums[i] += nums[i - 1];
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			
			System.out.println(nums[e] - nums[s - 1]);
		}

	}

}
