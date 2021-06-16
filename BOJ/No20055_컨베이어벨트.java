package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 210616 수 컨베이어벨트 위의 로봇 (삼성 SW 역량테스트)
 * 
 * 오랜만에 시뮬레이션 문제를 풀기 위해 삼성 코테 기출 문제를 풀어보았다.
 * 개인적으로 boj에서 책정한 티어보다 좀 더 높은 난이도를 체감했다;
 * 문제 자체적인 까다로움 보다는 반례를 찾는 것에 대한 어려움이 있었다.
 * 앞으로도 주기적인 시뮬레이션 문제 풀이의 필요성에 대해서 느끼게 되었다.
 * */

public class No20055_컨베이어벨트 {
	
	static int N, K, d_num;
	static int[][] belt;
	static boolean[][] chk;
	static ArrayList<robot> list;
	
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		belt = new int[2][N];
		chk = new boolean[2][N];
		list = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {			
			belt[0][i] = Integer.parseInt(st.nextToken());
		}
		for (int j = N-1; j >= 0; j--) {
			belt[1][j] = Integer.parseInt(st.nextToken());
		}
		
		
		
		int cnt = 0;
		
		while(true) {
			rotate();
			if(d_num != -1) {
				chk[0][N-1] = false;
			}
			move();
			if(!chk[0][0] && belt[0][0] != 0) {
				list.add(new robot(0, 0));
				chk[0][0] = true;
				belt[0][0]--;
			}
			if(d_num != -1) {
				chk[0][N-1] = false;
			}
			cnt++;
			if(zeroCnt()) break;
			
		}
		
		System.out.println(cnt);	

	}
	
	static boolean zeroCnt() {
		int zero = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < N; j++) {
				if(belt[i][j] == 0) zero++;
			}
		}
		if(zero >= K) return true;
		return false;
	}
	
	static void move() {		
		d_num = -1;
		for (int i = 0, size = list.size(); i < size; i++) {
			
			robot cur = list.get(i);
			int nx = cur.x;
			int ny = cur.y;
			if(nx == 0) {
				if(ny != N-1) ny += 1;
				else nx += 1;
			}else {
				if(ny != 0) ny -= 1;
				else nx -= 1;
			}
			
			if(!chk[nx][ny] && belt[nx][ny] != 0) {
				chk[cur.x][cur.y] = false;
				if(nx == 0 && ny == N-1) {
					d_num = i;		
				}				
				chk[nx][ny] = true;
				belt[nx][ny]--;
				list.get(i).x = nx;
				list.get(i).y = ny;
			}
		}
		if(d_num != -1) list.remove(d_num);
	}
	
	static boolean chkvalid(int x, int y) {
		if(x < 0 || x >= 2 || y < 0 || y >= N) return false;
		return true;
	}
	
	static void rotate() {
		d_num = -1;
		int direction = 0;
		int nx = 0;
		int ny = 0;
		
		int tmp = belt[0][0];
		boolean tmp2 = chk[0][0];
		
		while(true) {
			if(direction == 4) {
				belt[0][1] = tmp;
				chk[0][1] = tmp2;
				break;
			}
			if(chkvalid(nx + dx[direction], ny + dy[direction])) {
				belt[nx][ny] = belt[nx + dx[direction]][ny + dy[direction]];
				chk[nx][ny] = chk[nx + dx[direction]][ny + dy[direction]];
				nx += dx[direction];
				ny += dy[direction];
			}else direction++;
		}
		
		for (int i = 0, size = list.size(); i < size; i++) {
			robot cur = list.get(i);
			int rx = cur.x;
			int ry = cur.y;
			if(rx == 0) {
				if(ry != N-1) ry += 1;
				else nx += 1;
			}else {
				if(ry != 0) ry -= 1;
				else rx -= 1;
			}
			if(rx == 0 && ry == N-1) {
				d_num = i;
			}
			chk[cur.x][cur.y] = false;
			chk[rx][ry] = true;
			list.get(i).x = rx;
			list.get(i).y = ry;
		}
		if(d_num != -1) list.remove(d_num);
	}
	
	static class robot{
		int x;
		int y;
		public robot(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}		
	}

}
