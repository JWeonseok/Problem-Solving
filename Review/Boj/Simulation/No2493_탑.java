package rev.simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class No2493_탑 {
	
	static int N;
	static int[] towers, lazerInd;
	static Stack<Tower> stack;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		stack = new Stack<>();
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int height = Integer.parseInt(st.nextToken());
			if (stack.isEmpty()) {
				answer.append('0').append(' ');
				stack.push(new Tower(i, height));
				continue;
			}
			
			while (true) {
				if (stack.isEmpty()) {
					answer.append('0').append(' ');
					stack.push(new Tower(i, height));
					break;
				}
				
				Tower cur = stack.peek();
				
				if (cur.h > height) {
					answer.append(cur.ind).append(' ');
					stack.push(new Tower(i, height));
					break;
				} else stack.pop();
			}
		}
		
		System.out.println(answer);

	}
	
	static class Tower {
		int ind, h;

		public Tower(int ind, int h) {
			super();
			this.ind = ind;
			this.h = h;
		}
		
	}

}
