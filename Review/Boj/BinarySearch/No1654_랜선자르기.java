package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No1654_랜선자르기 {
	
	static int K, N;
	static long answer = 0;
	static int[] lines;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		lines = new int[K];
		long start = 1, end = 0;
		for (int i = 0; i < K; i++) {
			lines[i] = Integer.parseInt(br.readLine());
			end = Math.max(end, lines[i]);
		}
		
		while (start <= end) {
			long mid = (start + end) / 2;
			int cnt = 0;
			
			for (Integer in : lines) {
				cnt += (in / mid);
			}
			
			if (cnt >= N) {
				answer = mid;
				start = mid + 1;
			} else end = mid - 1;
		}
		
		System.out.println(answer);

	}

}
