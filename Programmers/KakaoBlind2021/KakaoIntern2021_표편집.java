package programmers;

import java.util.Stack;

public class KakaoIntern2021_표편집 {
	
	static Stack<Integer> stack = new Stack<>();

	public static void main(String[] args) {
		
		System.out.println(solution(8, 2, new String[] {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"}));
		System.out.println(solution(8, 2, new String[] {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"}));
		
	}
	
	static String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder();
        int size = n;
        
        int pointer = k;
        
        for (int i = 0; i < cmd.length; i++) {
			String[] c = cmd[i].split(" ");
			if(c[0].equals("U")) {
				pointer -= Integer.parseInt(c[1]);
			}else if (c[0].equals("D")){
				pointer += Integer.parseInt(c[1]);
			}else if (c[0].equals("C")) {
				stack.push(pointer);
				size--;
				if(pointer == size) pointer--;
			}else {
				int res = stack.pop();
				if(res <= pointer) pointer++;
				size++;
			}
		}
        
        for (int i = 0; i < size; i++) {
			sb.append("O");
		}
        while(!stack.isEmpty()) {
        	sb.insert(stack.pop(), "X");
        }
        return sb.toString();
    }
	
	static class delete {
		int ind, val;

		public delete(int ind, int val) {
			super();
			this.ind = ind;
			this.val = val;
		}		
	}
}
