package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class KakaoBlind2021_카드짝맞추기 {
	
	private int r, c, mvx, mvy;
	private int max_num, move = 0, answer = Integer.MAX_VALUE;
	private int[][] board;
	private int[] card, order;
	private boolean[] permchk;
	
	private int[] dx = {-1, 0, 1, 0};
	private int[] dy = {0, 1, 0, -1};
	
	public int solution(int[][] board, int r, int c) {
		
		this.board = board;
		this.r = r;
		this.c = c;
		
		max_num = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				max_num = Math.max(max_num, board[i][j]);
			}
		}
		
		card = new int[max_num];
		order = new int[max_num];
		permchk = new boolean[max_num];
		
		for (int i = 1; i <= max_num; i++) {
			card[i-1] = i;
		}
        
		perm(0);
		
		return answer;
    }
	
	public void game() {
		
		move = 0;
		mvx = r;
		mvy = c;
		
		for (int i = 0; i < max_num; i++) {
			for (int j = 0; j < 2; j++) {
				bfs(new card(mvx, mvy, board[mvx][mvy]), order[i], j);
				move++;
			}			
		}
		answer = Integer.min(answer, move);
	}
	
	public void perm(int ind) {
		if(ind == max_num) {
			makeBoard();
			game();
		}
		
		for (int i = 0; i < max_num; i++) {
			if(permchk[i]) continue;
			
			order[ind] = card[i];
			permchk[i] = true;
			perm(ind + 1);
			permchk[i] = false;
		}
	}
	
	public void bfs(card c, int num, int type) {
		
		Queue<card> q = new LinkedList<>();
		boolean[][] chk = new boolean[board.length][board.length];
		
		q.offer(c);
		chk[c.x][c.y] = true;
		
		int len = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				card cur = q.poll();
				
				if(cur.v == num) {					
					if(type == 0) {
						mvx = cur.x;
						mvy = cur.y;
						move += len;
						board[mvx][mvy] = -board[mvx][mvy];
						return;
					}else if(c.x != cur.x || c.y != cur.y) {
						mvx = cur.x;
						mvy = cur.y;
						move += len;
						board[mvx][mvy] = -board[mvx][mvy];
						return;
					}
					
				}
				
				for (int j = 0; j < 4; j++) {
					int nx = cur.x + dx[j];
					int ny = cur.y + dy[j];
					
					if(chkvalid(nx, ny) && !chk[nx][ny]) {
						q.offer(new card(nx, ny, board[nx][ny]));
						chk[nx][ny] = true;
					}
					
					while(true) {
						if(!chkvalid(nx, ny)) {
							nx -= dx[j];
							ny -= dy[j];
							break;
						}else if(board[nx][ny] > 0) {
							break;
						}
						nx += dx[j];
						ny += dy[j];
					}
					
					if(!chk[nx][ny]) {
						q.offer(new card(nx, ny, board[nx][ny]));
						chk[nx][ny] = true;
					}
				}
			}
			len++;			
		}
		
	}
	
	public boolean chkvalid(int x, int y) {
		if(x < 0 || x >= board.length || y < 0 || y >= board.length) return false;
		return true;
	}
	
	public void makeBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if(board[i][j] < 0) board[i][j] = -board[i][j];
			}
		}
	}
	
	
	class card {
		int x, y, v;

		public card(int x, int y, int v) {
			super();
			this.x = x;
			this.y = y;
			this.v = v;
		}
	}
	
	@org.junit.jupiter.api.Test
	public void Test() {
//		System.out.println(solution(new int[][] {{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}}, 1, 0));
		System.out.println(solution(new int[][] {{3,0,0,2},{0,0,1,0},{0,1,0,0},{2,0,0,3}}, 1, 0));		
	}
}
