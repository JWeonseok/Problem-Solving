package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No17471_게리맨더링 {
	
	static int N, answer = Integer.MAX_VALUE;
	static int[] population, A, B;
	static ArrayList<Integer>[] location;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		population = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		location = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			location[i] = new ArrayList<>();
			int cnt = Integer.parseInt(st.nextToken());			
			
			for (int j = 0; j < cnt; j++) {
				location[i].add(Integer.parseInt(st.nextToken()) - 1);
			}
		}
		
		for (int i = 1; i <= N / 2; i++) {
			A = new int[i];
			Combination(0, 0, i);
		}
		
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

	}
	
	static boolean InA(int num) {
		for (int i = 0; i < A.length; i++) {
			if (A[i] == num) return true;
		}
		return false;
	}
	
	static boolean InB(int num) {
		for (int i = 0; i < B.length; i++) {
			if (B[i] == num) return true;
		}
		return false;
	}
	
	static void ChkLocation() {
		B = new int[N - A.length];
		int ind = 0;
		loop: for (int i = 0; i < N; i++) {
			for (int j = 0; j < A.length; j++) {
				if (A[j] == i) continue loop;
			}
			B[ind++] = i;
		}
		
		Queue<Integer> q = new LinkedList<>();
		boolean[] chk = new boolean[N];
		q.offer(A[0]);
		chk[A[0]] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			int size = location[cur].size();
			for (int i = 0; i < size; i++) {
				if (!chk[location[cur].get(i)] && InA(location[cur].get(i))) {
					q.offer(location[cur].get(i));
					chk[location[cur].get(i)] = true;
				}
			}
		}
		
		q.offer(B[0]);
		chk[B[0]] = true;
		while (!q.isEmpty()) {
			int cur = q.poll();
			int size = location[cur].size();
			for (int i = 0; i < size; i++) {
				if (!chk[location[cur].get(i)] && InB(location[cur].get(i))) {
					q.offer(location[cur].get(i));
					chk[location[cur].get(i)] = true;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			if (!chk[i]) return;
		}
		
		int Apop = 0, Bpop = 0;
		for (int i = 0; i < A.length; i++) {
			Apop += population[A[i]];
		}
		for (int i = 0; i < B.length; i++) {
			Bpop += population[B[i]];
		}
		
		answer = Math.min(answer, Math.abs(Apop - Bpop));
	}
	
	static void Combination(int tgtInd, int srcInd, int r) {
		if (tgtInd == r) {
			ChkLocation();
			return;
		}
		
		if (srcInd == N) return;
		
		A[tgtInd] = srcInd;
		Combination(tgtInd + 1, srcInd + 1, r);
		Combination(tgtInd, srcInd + 1, r);
	}

}
