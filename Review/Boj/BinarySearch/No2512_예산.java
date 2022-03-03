package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No2512_예산 {
	
	static int N, M, answer;
	static int[] budget;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		budget = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = 0, end = 0;
		for (int i = 0; i < N; i++) {
			budget[i] = Integer.parseInt(st.nextToken());
			end = Math.max(end, budget[i]);
		}
		
		M = Integer.parseInt(br.readLine());
		
		while (start <= end) {
			int mid = (start + end) / 2;
			int sum = 0;
			
			for (Integer in : budget) {
				if (in < mid) sum += in;
				else sum += mid;
			}
			
			if (sum <= M) {
				answer = mid;
				start = mid + 1;
			} else end = mid - 1;
		}
		
		System.out.println(answer);

	}

}
