package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No13458_시험감독 {
	
	static long answer = 0;
	static int N, B, C;
	static int[] A;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		A = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			if(A[i] <= B) {
				answer++;
				continue;
			}else {
				int p = (A[i] - B) / C;
				int r = (A[i] - B) % C;
				
				answer += (p + 1);
				if(r != 0) answer++;
			}
		}
		
		System.out.println(answer);

	}

}
