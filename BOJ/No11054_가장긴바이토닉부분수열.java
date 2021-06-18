package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 210618 금 가장 긴 바이토닉 부분 수열
 * 
 * DP를 이용하는 최장 증가 부분 수열(LIS)의 응용 문제
 * LIS가 말 그대로 가장 긴 증가 부분 수열을 구하는 것이라면
 * S1 < S2 < ... Sk-1 < Sk > Sk+1 > ... SN-1 > SN 의 구조를 가지는
 * 가장 긴 수열을 구하는 문제이다.
 * 따라서 LIS와 역순의 LIS를 구한 후에 위의 조건을 만족하는 수열을 구함으로 해결할 수 있었다.
 * */

public class No11054_가장긴바이토닉부분수열 {

	static int N;
	static int[] ary, LIS1, LIS2;
	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		ary = new int[N];
		LIS1 = new int[N];
		LIS2 = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			LIS1[i] = 1; LIS2[N-1-i] = 1;
			for (int j = 0; j < i; j++) {
				if(ary[j] < ary[i] && LIS1[i] < LIS1[j] + 1) {
					LIS1[i] = LIS1[j] + 1;
				}
			}
			for (int j = N-1; j > N-1-i; j--) {
				if(ary[j] < ary[N-1-i] && LIS2[N-1-i] < LIS2[j] + 1) {
					LIS2[N-1-i] = LIS2[j] + 1;
				}
			}
			if(i >= N/2) {
				max = Math.max(max, Math.max(LIS1[i] + LIS2[i], LIS1[N-1-i] + LIS2[N-1-i]));
			}
		}
		System.out.println(max-1);

	}

}
