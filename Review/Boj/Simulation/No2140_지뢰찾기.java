package rev.simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class No2140_지뢰찾기 {
	
	static int N, answer = 0;
	static char[][] map;
	
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		if (N <= 2) {
			System.out.println(0);
			return;
		}
		
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!Character.isDigit(map[i][j])) continue;
				
				int curNum = map[i][j] - '0', boomCnt = 0;
				ArrayList<Point> coverList = new ArrayList<>();
				for (int k = 0; k < 8; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if (ChkValid(nx, ny)) {
						if (map[nx][ny] == '#') coverList.add(new Point(nx, ny));
						else if (map[nx][ny] == '*') boomCnt++;
					}
				}
				
				if (curNum == boomCnt) {
					for (Point p : coverList) map[p.x][p.y] = ' ';
				} else if (curNum == boomCnt + coverList.size()) {
					for (Point p : coverList) {
						map[p.x][p.y] = '*';
						answer++;
					}
				} 
			}
		}
		
		answer += (N >= 5 ? Math.pow(N - 4, 2) : 0);
		System.out.println(answer);

	}
	
	static boolean ChkValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) return false;
		return true;
	}
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
