package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No1963_소수경로 {
	
	static int T;
	static String start, target;
	static Queue<Number> q;
	static boolean[] chk;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			start = st.nextToken();
			target = st.nextToken();
			
			bfs();
		}

	}
	
	static void bfs() {
		q = new LinkedList<>();
		chk = new boolean[10000];
		
		q.offer(new Number(start.charAt(0) - '0', start.charAt(1) - '0', start.charAt(2) - '0', start.charAt(3) - '0'));
		chk[Integer.parseInt(start)] = true;
		
		int cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				Number cur = q.poll();
				
				if (ChkTarget(cur)) {
					System.out.println(cnt);
					return;
				}
				
				for (int j = 1; j < 10; j++) {
					int num = ConvertInt(j, cur.n2, cur.n3, cur.n4);					
					if (isPrime(num) && !chk[num]) {
						q.offer(new Number(j, cur.n2, cur.n3, cur.n4));
						chk[num] = true;
					}
				}
				
				for (int j = 0; j < 10; j++) {
					int num = ConvertInt(cur.n1, j, cur.n3, cur.n4);					
					if (isPrime(num) && !chk[num]) {
						q.offer(new Number(cur.n1, j, cur.n3, cur.n4));
						chk[num] = true;
					}
				}
				
				for (int j = 0; j < 10; j++) {
					int num = ConvertInt(cur.n1, cur.n2, j, cur.n4);					
					if (isPrime(num) && !chk[num]) {
						q.offer(new Number(cur.n1, cur.n2, j, cur.n4));
						chk[num] = true;
					}
				}
				
				for (int j = 0; j < 10; j++) {
					int num = ConvertInt(cur.n1, cur.n2, cur.n3, j);					
					if (isPrime(num) && !chk[num]) {
						q.offer(new Number(cur.n1, cur.n2, cur.n3, j));
						chk[num] = true;
					}
				}
			}
			cnt++;
		}
		
		System.out.println("Impossible");
		
	}
	
	static int ConvertInt(int n1, int n2, int n3, int n4) {
		return n1 * 1000 + n2 * 100 + n3 * 10 + n4;
	}
	
	static boolean ChkTarget(Number number) {
		String tgt = "";
		tgt += Integer.toString(number.n1);
		tgt += Integer.toString(number.n2);
		tgt += Integer.toString(number.n3);
		tgt += Integer.toString(number.n4);
		
		if (tgt.equals(target)) return true;
		return false;
	}
	
	static boolean isPrime(int num) {		
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0) return false;
		}
		return true;
	}
	
	static class Number {
		int n1, n2, n3, n4;

		public Number(int n1, int n2, int n3, int n4) {
			super();
			this.n1 = n1;
			this.n2 = n2;
			this.n3 = n3;
			this.n4 = n4;
		}
	}

}
