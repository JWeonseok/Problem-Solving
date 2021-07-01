package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No20410_추첨상사수대작전 {

	static int m, seed, x1, x2;
	static int a, c;
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		seed = Integer.parseInt(st.nextToken());
		x1 = Integer.parseInt(st.nextToken());
		x2 = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				if(x1 == ((i*seed)+j) % m && x2 == ((i*x1)+j) % m) {
					a = i;
					c = j;
				}
			}
		}
		System.out.println(a + " " + c);
	}

}
