package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No12904_A와B {
	
	static String S;

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = br.readLine();
		StringBuilder sb = new StringBuilder(br.readLine());
		
		
		while(S.length() < sb.length()) {
			if(sb.charAt(sb.length()-1) == 'A') sb.deleteCharAt(sb.length()-1);
			else {
				sb.deleteCharAt(sb.length()-1);
				sb.reverse();
			}
		}
		
		if(sb.toString().equals(S)) System.out.println(1);
		else System.out.println(0);
		
	}

}
