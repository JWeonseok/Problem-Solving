package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 210617 목 마법사 사엉와 파이어스톰 (삼성 SW 역량테스트)
 * 
 * 삼성 마법사 상어 시리즈 세 번째 문제
 * 문제 이해 자체는 어렵지 않았다.
 * 다만 디버깅 자체가 매우 힘들었다.
 * 크기가 큰 이차원 배열의 알고리즴에 따른 변화를 일일이 확인해야 되는
 * 꽤나 까다로운 문제였다. 
 * */

public class No20058_마법사상어와파이어스톰 {
	
	static int N, Q, size, iceSum = 0, iceCnt = 0;
	static int[][] map;
	static int[] L;
	
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		size = (int)(Math.pow(2, N));
		
		map = new int[size][size];
		L = new int[Q];
		
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			L[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for (int i = 0; i < Q; i++) {
			storm(L[i]);
			fire();
		}
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				iceSum += map[i][j];
			}
		}
		getMass();
		
		System.out.println(iceSum);
		System.out.println(iceCnt);

	}
	
	static void getMass() {
		boolean[][] chk = new boolean[size][size];
		Queue<point> q = new LinkedList<>();
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(chk[i][j] || map[i][j] == 0) continue;
				q.offer(new point(i, j));
				chk[i][j] = true;
				int cnt = 1;
				while(!q.isEmpty()){
					point cur = q.poll();
					for (int k = 0; k < 4; k++) {
						int nx = cur.x + dx[k];
						int ny = cur.y + dy[k];
						if(chkvalid(nx, ny) && !chk[nx][ny] && map[nx][ny] != 0) {
							q.offer(new point(nx, ny));
							chk[nx][ny] = true;
							cnt++;
						}
					}
				}
				iceCnt = Math.max(iceCnt, cnt);
			}
		}
	}
	
	static void printMap() {
		for (int i = 0; i < size; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println("------------------");
	}
	
	static void rotate(int x, int y, int s) {
		if(s == 0) return;		
		
		for (int i = 0; i < s - 1; i++) {
			int tmp = map[x][y];
			int nx = x;
			int ny = y;
			int d = 0;
			while(true) {
				if(x <= nx + dx[d] && nx + dx[d] < x + s && y <= ny + dy[d] && ny + dy[d] < y + s) {
					map[nx][ny] = map[nx + dx[d]][ny + dy[d]];
					nx += dx[d];
					ny += dy[d];
				}else d = (d + 1) % 4;
				if(nx == x && ny == y + 1) {
					map[nx][ny] = tmp;
					break;
				}
			}
		}		
		rotate(x + 1, y + 1, s - 2);		
		
	}
	
	static void storm(int L) {
		if(L == 0) return;
		int s = (int)(Math.pow(2, L));
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(i % s != 0 || j % s != 0) continue;
				rotate(i, j, s);
			}
		}
	}
	
	static boolean chkvalid(int x, int y) {
		if(x < 0 || x >= size || y < 0 || y >= size) return false;
		return true;
	}
	
	static void fire() {
		boolean[][] chk = new boolean[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int cnt = 0;
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];					
					if(!chkvalid(nx, ny) || map[nx][ny] == 0) cnt++;
				}
				if(cnt > 1 && map[i][j] > 0) chk[i][j] = true;
			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(chk[i][j]) map[i][j]--;
			}
		}
	}
	
	static class point{
		int x, y;

		public point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}		
	}

}