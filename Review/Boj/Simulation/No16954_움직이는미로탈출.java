package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class No16954_움직이는미로탈출 {
	
	static char[][] map = new char[8][8];
	static boolean[] chkWall;
	static Queue<Point> q;
	static ArrayList<Point> wallList;
	
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		q = new LinkedList<>();
		wallList = new ArrayList<>();
		
		for (int i = 0; i < 8; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < 8; j++) {
				if(map[i][j] == '#') wallList.add(new Point(i, j));
			}
		}		
		chkWall = new boolean[wallList.size()];
		q.offer(new Point(7, 0));
		map[7][0] = '!';
		
		Collections.sort(wallList);
		
		while(!q.isEmpty()) {
			
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				Point cur = q.poll();
				
				if(map[cur.x][cur.y] == '#') continue;
				
				if(cur.x == 0 && cur.y == 7) {
					System.out.println(1);
					return;
				}
				
				for (int j = 0; j < 8; j++) {
					int nx = cur.x + dx[j];
					int ny = cur.y + dy[j];
					
					if(chkValid(nx, ny) && map[nx][ny] == '.') {
						q.offer(new Point(nx, ny));
						map[nx][ny] = '!';
					}
				}
				q.offer(cur);
			}
			
			Gravity();
		}
		
		System.out.println(0);
	}
	
	static void Gravity() {
		for (int i = 0; i < chkWall.length; i++) {
			if(chkWall[i]) continue;
			
			if(wallList.get(i).x + 1 < 8) {
				map[wallList.get(i).x][wallList.get(i).y] = '.';
				map[wallList.get(i).x + 1][wallList.get(i).y] = '#';
				wallList.get(i).x++;
			}else {
				chkWall[i] = true;
				map[wallList.get(i).x][wallList.get(i).y] = '.';
			}
			
		}
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= 8 || y < 0 || y >= 8) return false;
		return true;
	}
	
	static class Point implements Comparable<Point>{
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			return o.x - this.x;
		}
		
	}

}
