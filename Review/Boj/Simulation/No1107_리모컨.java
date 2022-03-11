package rev.simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class No1107_리모컨 {
	
	static int target;
	static int N, curNum, pressCnt;
	static HashSet<Character> breakNum;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		target = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		breakNum = new HashSet<>();
		
		if (N > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				breakNum.add(st.nextToken().charAt(0));
			}
		}		
		
		int answer = Math.abs(target - 100);
		for (int i = 0; i <= 999999; i++) {
			String str = Integer.toString(i);
			int len = str.length();
			boolean flag = false;
			for (int j = 0; j < len; j++) {
				if (breakNum.contains(str.charAt(j))) {
					flag = true;
					break;
				}
			}
			if (!flag) {				
				int cur = Math.abs(target - i) + len;
				answer = Math.min(answer, cur);
			}
		}
		
		System.out.println(answer);

	}
}


