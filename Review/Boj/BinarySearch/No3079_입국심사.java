package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No3079_입국심사 {
	
	static int N, M;
	static int[] T;
	static long answer = Long.MAX_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		T = new int[N];
		long start = 0, end = 0;
		for (int i = 0; i < N; i++) {
			T[i] = Integer.parseInt(br.readLine());
			end = Math.max(end, T[i]);
		}
		end *= M;
		
		while (start <= end) {
			long mid = (start + end) / 2;
			long sum = 0;
			
			for (Integer in : T) {
				sum += mid / in;
				if (sum >= M) break;
			}
			
			if (sum >= M) {
				answer = Math.min(answer, mid);
				end = mid - 1;
			} else start = mid + 1;
		}
		
		System.out.println(answer);
		
		

	}

}
