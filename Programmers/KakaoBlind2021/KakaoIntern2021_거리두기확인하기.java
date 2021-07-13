package programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class KakaoIntern2021_거리두기확인하기 {
	
	static char[][] map;
	static boolean[][] chk;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) {

		System.out.println(Arrays.toString(solution(new String[][] {
			{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
			{"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
			{"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
			{"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
			{"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
		})));

	}
	
	static int[] solution(String[][] places) {
		int[] answer = new int[5];
		
		Arrays.fill(answer, 1);
		
		for (int t = 0; t < 5; t++) {
			map = new char[5][5];
			chk = new boolean[5][5];
			
			for (int i = 0; i < 5; i++) {
				map[i] = places[t][i].toCharArray();
			}
			
			loop: for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if(map[i][j] != 'P') continue;
					
					Queue<point> q = new LinkedList<>();
					q.offer(new point(i, j));
					chk[i][j] = true;
					
					for (int k = 0; k < 2; k++) {
						int size = q.size();
						
						for (int l = 0; l < size; l++) {
							point cur = q.poll();
							for (int m = 0; m < 4; m++) {
								int nx = cur.x + dx[m];
								int ny = cur.y + dy[m];
								
								if(chkvalid(nx, ny) && map[nx][ny] != 'X' && !chk[nx][ny]) {
									if(map[nx][ny] == 'P') {
										answer[t] = 0;
										break loop;
									}
									q.offer(new point(nx, ny));
									chk[nx][ny] = true;
								}
							}
						}
					}
				}
			}
		}
		return answer;
	}
	
	static boolean chkvalid(int x, int y) {
		if(x < 0 || x >= 5 || y < 0 || y >= 5) return false;
		return true;
	}
	
	static class point {
		int x, y;

		public point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}		
	}

}
