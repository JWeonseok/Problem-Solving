package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No2110_공유기설치 {
	
	static int N, C, answer = 0;
	static int[] house;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		house = new int[N];
		for (int i = 0; i < N; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(house);
		int start = 1, end = house[house.length - 1] - house[0];
		
		while (start <= end) {
			int mid = (start + end) / 2;
			int cur = house[0];
			int cnt = 1;
			
			for (Integer in : house) {
				if (in - cur >= mid) {
					cur = in;
					cnt++;
				}
			}
			
			if (cnt >= C) {
				answer = mid;
				start = mid + 1;
			} else end = mid - 1;
		}
		
		System.out.println(answer);

	}

}
