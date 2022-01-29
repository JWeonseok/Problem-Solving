package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No21609_상어중학교 {
	
	static int N, M, answer = 0, maxSize, rainbowBlocks, stdX, stdY;
	static int[][] map;
	static ArrayList<Block> blockList;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		

		while(true) {
			AutoPlay();
			if(maxSize < 2) break;
		}
		
		System.out.println(answer);

	}
	
	static void AutoPlay() {
		
		blockList = new ArrayList<>();
		maxSize = 0;
		rainbowBlocks = 0;
		stdX = -1;
		stdY = -1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 0 || map[i][j] == -1 || map[i][j] == -2) continue;
				
				Queue<Block> q = new LinkedList<>();
				ArrayList<Block> tmpList = new ArrayList<>();
				boolean[][] chk = new boolean[N][N];
				int cnt = 1, rainbowCnt = 0;
				int tmpX = Integer.MAX_VALUE, tmpY = Integer.MAX_VALUE;
				
				q.offer(new Block(i, j));
				chk[i][j] = true;
				
				while(!q.isEmpty()) {
					Block cur = q.poll();
					tmpList.add(new Block(cur.x, cur.y));
					
					if(map[cur.x][cur.y] != 0) {
						if(cur.x < tmpX) {
							tmpX = cur.x;
							tmpY = cur.y;
						}else if(cur.x == tmpX && cur.y < tmpY) {
							tmpX = cur.x;
							tmpY = cur.y;
						}
					}else rainbowCnt++;
					
					for (int k = 0; k < 4; k++) {
						int nx = cur.x + dx[k];
						int ny = cur.y + dy[k];
						
						if(chkValid(nx, ny) && (map[nx][ny] == map[i][j] || map[nx][ny] == 0) && !chk[nx][ny]) {
							q.offer(new Block(nx, ny));
							chk[nx][ny] = true;
							cnt++;
						}
					}
				}
				
				if(cnt > maxSize) {
					maxSize = cnt;
					rainbowBlocks = rainbowCnt;
					stdX = tmpX;
					stdY = tmpY;
					blockList.clear();
					
					for(Block block : tmpList) {						
						blockList.add(new Block(block.x, block.y));
					}
				}else if(cnt == maxSize) {
					if(rainbowBlocks < rainbowCnt) {
						rainbowBlocks = rainbowCnt;
						stdX = tmpX;
						stdY = tmpY;
						blockList.clear();
						
						for(Block block : tmpList) {
							blockList.add(new Block(block.x, block.y));
						}
					}else if(rainbowBlocks == rainbowCnt) {
						if(stdX < tmpX) {
							stdX = tmpX;
							stdY = tmpY;
							blockList.clear();
							
							for(Block block : tmpList) {
								blockList.add(new Block(block.x, block.y));
							}
						}else if(stdX == tmpX && stdY < tmpY) {
							stdX = tmpX;
							stdY = tmpY;
							blockList.clear();
							
							for(Block block : tmpList) {
								blockList.add(new Block(block.x, block.y));
							}
						}
						
					}
				}
				
			}
		}
		
		for(Block block : blockList) {
			map[block.x][block.y] = -2;
		}
		
		if(blockList.size() >= 2) {
			answer += (int)(Math.pow(blockList.size(), 2));
		}
				
		Gravity();
		Rotate(0, 0, N);
		Gravity();
	}
	
	static void Rotate(int x, int y, int cnt) {
		if(cnt <= 1) return;
		
		for (int i = 0; i < cnt - 1; i++) {
			int nx = x;
			int ny = y;
			int dir = 0;
			int tmp = map[x][y];
			
			while(true) {
				if(nx == x + 1 && ny == y) break;				
				if(nx + dx[dir] >= x + cnt || ny + dy[dir] >= y + cnt || nx + dx[dir] < x || ny + dy[dir] < y) {
					dir = (dir + 1) % 4;
					continue;
				}
				
				map[nx][ny] = map[nx + dx[dir]][ny + dy[dir]];
				nx += dx[dir];
				ny += dy[dir];
			}
			
			map[x + 1][y] = tmp;
		}
		
		Rotate(x + 1, y + 1, cnt - 2);
		
	}
	
	static void Gravity() {
		for (int i = 0; i < N; i++) {
			for (int j = N - 1; j >= 0; j--) {
				if(map[j][i] == -1 || map[j][i] == -2) continue;
				
				int nx = j;
				while(true) {
					if(nx + 1 >= N || map[nx + 1][i] != -2) break;					
					nx++;
				}
				if(nx == j) continue;
				
				map[nx][i] = map[j][i];
				map[j][i] = -2;
			}
		}
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) return false;
		return true;
	}
	
	static class Block {
		int x, y;

		public Block(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
