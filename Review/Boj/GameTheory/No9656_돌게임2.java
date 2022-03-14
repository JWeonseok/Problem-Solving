package rev.gametheory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No9656_돌게임2 {
	
	static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		if (N % 2 == 0) System.out.println("SK");
		else System.out.println("CY");

	}

}

