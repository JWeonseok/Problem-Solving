package rev.gametheory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No9660_돌게임6 {
	
	static long N;
	static boolean[] dp;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Long.parseLong(br.readLine());
		
		N %= 7;
		if (N == 0 || N == 2) System.out.println("CY");
		else System.out.println("SK");

	}

}
