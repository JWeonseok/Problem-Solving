package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No1013_Contact {
	
	static int T;
	static String vega = "(100+1+|01)+";

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			
			String str = br.readLine();
			if(str.matches(vega)) System.out.println("YES");
			else System.out.println("NO");			
		}
	}	

}
