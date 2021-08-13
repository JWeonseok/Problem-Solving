package programmers;

public class KakaoBlind2020_외벽점검 {
	private int n, answer;
	private int[] weak, dist, spreadWeak;
	private boolean finish;
	
	public int solution(int n, int[] weak, int[] dist) {
		answer = Integer.MAX_VALUE;
		
		this.n = n;
		this.weak = weak;
		this.dist = dist;
		
		makeSpreadWeak();
		finish = false;
		
		for (int i = 1; i <= dist.length; i++) {
			perm(0, new boolean[dist.length], new int[i]);
		}
		
		return answer == Integer.MAX_VALUE ? -1 : answer;    
    }
	
	public void makeSpreadWeak() {
		int len = weak.length;
		spreadWeak = new int[len * 2 - 1];
		for (int i = 0; i < len; i++) {
			spreadWeak[i] = weak[i];
		}
		for (int i = 0; i < len - 1; i++) {
			spreadWeak[i + len] = weak[i] + n;
		}
	}
	
	
	
	public void perm(int d, boolean[] chk, int[] tgt) {
		if(finish) return;
		
		if(d == tgt.length) {
			chkvalid(tgt);
			return;
		}
		
		for (int i = 0; i < dist.length; i++) {
			if(chk[i]) continue;
			
			tgt[d] = dist[i];
			chk[i] = true;
			perm(d + 1, chk, tgt);
			chk[i] = false;
		}
	}
	
	public void chkvalid(int[] tgt) {
		for (int i = 0; i < weak.length; i++) {
			int start = i;
			boolean flag = true;
			for (int j = 0; j < tgt.length; j++) {
				for (int k = i; k < i + weak.length; k++) {
					if(spreadWeak[k] - spreadWeak[start] > tgt[j]) {
						start = k;
						j++;
						if(j == tgt.length) {
							flag = false;
							break;
						}
					}
				}
				if(flag) {
					answer = j + 1;
					finish = true;					
					return;
				}
			}
		}
	}
	
	@org.junit.jupiter.api.Test
	public void Test() {
		System.out.println(solution(12, new int[] {1, 5, 6, 10}, new int[] {1, 2, 3, 4}));
		System.out.println(solution(12, new int[] {1, 3, 4, 9, 10}, new int[] {3, 5, 7}));
	}

}
