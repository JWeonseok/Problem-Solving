package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 211217 금 부등호
 * 
 * 백트랙킹이 핵심인 문제다.
 * 재귀를 통해서 부등호가 성립하는 숫자를 추가하며 
 * 조건에 만족하는 길이로 완성된 것들을 정렬하여 최대값가 최솟값을 산출하는 문제이다.
 * 
 * 반면에 나는 최솟값의 경우 0부터 K개, 최대값의 경우 9부터 K개의 숫자를 고르고
 * 순열을 통해 조건에 맞는 것만 산출했다.
 * 최대값과 최솟값에 해당하는 숫자들만 뽑아서 진행을 하더라도
 * 순열의 과정이 전부 진행되기 때문에 위의 방법에 비해서 시간이 좀 더 걸리는 것 같다.
 * */

public class No2529_부등호 {
	
	static int K;
	static char[] inequality;
	static int[] numsAry, permAry;
	static boolean[] chk;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		inequality = new char[K];
		numsAry = new int[K + 1];
		permAry = new int[K + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < K; i++) {
			inequality[i] = st.nextToken().charAt(0);
		}
		
		for (int i = 0; i < K + 1; i++) {
			numsAry[i] = 9 - i;
		}
		chk = new boolean[K + 1];
		perm(0);
		
		flag = false;
		for (int i = 0; i < K + 1; i++) {
			numsAry[i] = i;
		}
		chk = new boolean[K + 1];
		perm(0);
	}
	
	static void perm(int ind) {
		
		if(ind == K + 1 && !flag && chkInequality()) {
			flag = true;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < K + 1; i++) {
				sb.append(permAry[i]);
			}
			System.out.println(sb.toString());
			return;
		}
		
		for (int i = 0; i < K + 1; i++) {
			if(chk[i]) continue;
			permAry[ind] = numsAry[i];
			chk[i] = true;
			perm(ind + 1);
			chk[i] = false;
		}
	}
	
	static boolean chkInequality() {
		for (int i = 0; i < K; i++) {
			if(inequality[i] == '<' && permAry[i] > permAry[i + 1]) return false;
			else if(inequality[i] == '>' && permAry[i] < permAry[i + 1]) return false;
		}
		return true;
	}
}
