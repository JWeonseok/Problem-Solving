package rev.gametheory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No9659_돌게임5 {
	
	static long N;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Long.parseLong(br.readLine());
		
		N -= 3;
		
		if (N % 2 == 0) System.out.println("SK");
		else System.out.println("CY");
	}

}
