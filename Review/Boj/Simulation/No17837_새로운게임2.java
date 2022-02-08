package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class No17837_새로운게임2 {
	
	static int N, K, cnt = 0;
	static int[][] map;
	static ArrayList<Integer>[][] chessMap;
	static ArrayList<Chess> chessList;
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		chessMap = new ArrayList[N][N];
		chessList = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				chessMap[i][j] = new ArrayList<>();
			}
		}
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()) - 1;
			
			chessMap[x][y].add(i);
			chessList.add(new Chess(x, y, dir, false));
		}
		
		while(cnt <= 1000) {
			cnt++;
			
			for (int i = 0; i < K; i++) {
				
				Chess chess = chessList.get(i);
				
				int nx = chess.x + dx[chess.dir];
				int ny = chess.y + dy[chess.dir];
				
				if(!ChkValid(nx, ny) || map[nx][ny] == 2) {
					if(chess.flag) {
						chess.flag = false;
						continue;					
					}
					
					if(chess.dir == 0) chess.dir = 1;
					else if(chess.dir == 1) chess.dir = 0;
					else if(chess.dir == 2) chess.dir = 3;
					else if(chess.dir == 3) chess.dir = 2;
					
					chess.flag = true;
					i--;
				}else {
					chess.flag = false;
					boolean flag = false;
					ArrayList<Integer> moveList = new ArrayList<>();
					
					for(int j = 0, size = chessMap[chess.x][chess.y].size(); j < size; j++) {
						if(chessMap[chess.x][chess.y].get(j) == i) flag = true;
						if(flag) moveList.add(chessMap[chess.x][chess.y].get(j)); 
					}
										
					if(map[nx][ny] == 0) {
						for (Integer in : moveList) {
							chessMap[nx][ny].add(in);
							chessMap[chess.x][chess.y].remove(in);
						}
					}
					else if(map[nx][ny] == 1) {
						for (int j = moveList.size() - 1; j >= 0; j--) {
							chessMap[nx][ny].add(moveList.get(j));
							chessMap[chess.x][chess.y].remove(moveList.get(j));
						}
					}
					
					for(Integer in : moveList) {
						chessList.get(in).x = nx;
						chessList.get(in).y = ny;
					}
				}
				
				if(Endpoint()) {
					System.out.println(cnt);
					return;
				}
			}
		}		
		
		System.out.println(-1);

	}
	
	static boolean Endpoint() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(chessMap[i][j].size() >= 4) return true;
			}
		}
		return false;
	}
	
	static boolean ChkValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) return false;
		return true;
	}
	
	static class Chess {
		int x, y, dir;
		boolean flag;

		public Chess(int x, int y, int dir, boolean flag) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.flag = flag;
		}
	}

}
