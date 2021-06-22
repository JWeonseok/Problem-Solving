package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No12907_동물원 {
	
	static int T, N;
	static int[] animals;

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			animals = new int[41];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				animals[Integer.parseInt(st.nextToken())]++;
			}
			
			int cnt = 0;
			boolean chk = false;
			int num = 2;
			for (int i = 0; i < 41; i++) {
				if(animals[i] > num) {
					chk = true;
					break;
				}
				if(animals[i] == 2) cnt++;
				if(animals[i] == 1 && num != animals[i]) cnt++;
				num = animals[i];
			}
			
			if(chk) System.out.println("#" + t + " " + 0);
			else System.out.println("#" + t + " " + (int)Math.pow(2, cnt));
		}
		
		
		

	}

}
