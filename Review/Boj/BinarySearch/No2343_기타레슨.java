package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No2343_기타레슨 {
	
	static int N, M, answer;
	static int[] lessons;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		lessons = new int[N];
		int start = Integer.MAX_VALUE, end = 0;
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			lessons[i] = Integer.parseInt(st.nextToken());
			end += lessons[i];
			start = Math.min(start, lessons[i]);
		}
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			int cnt = 0, sum = 0;
			for (Integer in : lessons) {
				if (sum + in > mid) {
					sum = 0;
					cnt++;
				}
				sum += in;				
			}
			if (sum != 0) cnt++;
			
			if (cnt <= M) {
				answer = mid;
				end = mid - 1;
			} else start = mid + 1;
		}
		
		System.out.println(answer);

	}

}