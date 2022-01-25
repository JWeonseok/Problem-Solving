package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class No17140_이차원배열과연산 {
	
	static int r, c, k, answer = 0;
	static int xLength = 3, yLength = 3;
	static int[][] A = new int[101][101];

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= 3; i++) {
			
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(answer <= 100) {
			
			if(A[r][c] == k) break;
			
			if(xLength >= yLength) ROperation();
			else COperation();
			
			answer++;
		}
		
		System.out.println(answer > 100 ? -1 : answer);

	}
	
	static void ROperation() {
		
		int length = Integer.MIN_VALUE;
		for (int i = 1; i <= xLength; i++) {
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			PriorityQueue<Pair> pq = new PriorityQueue<>();
			
			for (int j = 1; j <= yLength; j++) {
				if(A[i][j] == 0) continue;
				if(map.containsKey(A[i][j])) map.put(A[i][j], map.get(A[i][j]) + 1);
				else map.put(A[i][j], 1);
			}
			
			for (Integer in : map.keySet()) pq.offer(new Pair(in, map.get(in)));
			
			int ind = 1;
			while(!pq.isEmpty()) {
				Pair cur = pq.poll();
				A[i][ind++] = cur.num;
				A[i][ind++] = cur.cnt;
			}
			
			length = Math.max(length, ind);
			
			while(ind <= 100) A[i][ind++] = 0;
		}
		yLength = length;
	}
	
	static void COperation() {
		int length = Integer.MIN_VALUE;
		for (int i = 1; i <= yLength; i++) {
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			PriorityQueue<Pair> pq = new PriorityQueue<>();
			
			for (int j = 1; j <= xLength; j++) {
				if(A[j][i] == 0) continue;
				if(map.containsKey(A[j][i])) map.put(A[j][i], map.get(A[j][i]) + 1);
				else map.put(A[j][i], 1);
			}
			
			for (Integer in : map.keySet()) pq.offer(new Pair(in, map.get(in)));
			
			int ind = 1;
			while(!pq.isEmpty()) {
				Pair cur = pq.poll();
				A[ind++][i] = cur.num;
				A[ind++][i] = cur.cnt;
			}
			
			length = Math.max(length, ind);
			
			while(ind <= 100) A[ind++][i] = 0;
		}
		xLength = length;
	}
	
	static class Pair implements Comparable<Pair>{
		int num, cnt;

		public Pair(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Pair o) {
			if(this.cnt == o.cnt) return this.num - o.num;
			return this.cnt - o.cnt;
		}
	}

}
