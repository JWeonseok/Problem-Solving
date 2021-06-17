package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 210617 목 마법사 상어와 토네이도 (삼성 SW 역량테스트)
 * 
 * 삼성 마법사 상어 시리즈 두 번째 문제
 * 시뮬레이션과 하드 코딩의 결정체였다.
 * 모래의 움직임이 난이도를 높여서 꼼꼼함이 많이 요구되는 문제였다.
 * 반례 또한 찾기가 여간 쉽지 않아서 애를 먹은 문제였다.
 * 
 * key point
 * - 모래의 움직임 처리
 * - 정확한 디버깅 방법을 통한 반례 찾기
 * */

public class No20057_마법사상어와토네이도 {
	
	static int N, r, c, outSand = 0;
	static int[][] map;
	
	static int[][] mvSand_x = {{-1, 1, -2, 2, 0, -1, 1, -1, 1, 0},
								{-1, -1, 0, 0, 2, 0, 0, 1, 1, 1},
								{-1, 1, -2, 2, 0, -1, 1, -1, 1, 0},
								{1, 1, 0, 0, -2, 0, 0, -1, -1, -1}};
	static int[][] mvSand_y = {{1, 1, 0, 0, -2, 0, 0, -1, -1, -1},
								{1, -1, 2, -2, 0, 1, -1, 1, -1, 0},
								{-1, -1, 0, 0, 2, 0, 0, 1, 1, 1},
								{1, -1, 2, -2, 0, 1, -1, 1, -1, 0},};
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		r = N / 2;
		c = N / 2;
		
		rotate();
		
		System.out.println(outSand);

	}
	
	static boolean chkvalid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) return false;
		return true;
	}
	
	static void rotate() {
		int nx = r;
		int ny = c;
		int cnt = 0;
		int d = 0;
		
		loop: while(true) {			
			if(d == 0 || d == 2) cnt++;
			for (int i = 0; i < cnt; i++) {
				nx += dx[d];
				ny += dy[d];
				if(!chkvalid(nx, ny)) break loop;
				
				spread(nx, ny, d);
			}
			d = (d+1) % 4;
		}
	}
	
	static void spread(int x, int y, int d) {
		int nx = 0, ny = 0, sand = 0;
		int totalsand = 0;
		for (int i = 0; i < 10; i++) {
			nx = x + mvSand_x[d][i];
			ny = y + mvSand_y[d][i];
			
			if(i < 2) sand = (int)(map[x][y] * 0.01);
			else if(i < 4) sand = (int)(map[x][y] * 0.02);
			else if(i < 5) sand = (int)(map[x][y] * 0.05);
			else if(i < 7) sand = (int)(map[x][y] * 0.07);
			else if(i < 9) sand = (int)(map[x][y] * 0.1);
			else sand = (map[x][y] - totalsand);
			totalsand += sand;
			
			if(chkvalid(nx, ny)) map[nx][ny] += sand;
			else outSand += sand;
		}
	}

}