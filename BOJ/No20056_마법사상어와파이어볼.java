package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 210616 수 마법사 상어와 파이어볼 (삼성 SW 역량테스트)
 * 
 * 그 유명한 삼성 코테의 상어 시리즈의 마법사 상어 문제이다.
 * 시뮬레이션 문제의 특성상 특정 알고리즘을 사용한다기 보다는
 * 문제 해결을 위해 조건들을 따지고 반례를 찾아내는 것이 중요하다고 생각하는데
 * 딱 그러한 부분들에서 어려움을 느꼈던 전형적인 문제였다.
 * 자료구조를 적절하게 구성하고 조건들을 판별하는 함수들에 대한 설계가
 * 이 문제의 핵심인 것 같다.
 * */

public class No20056_마법사상어와파이어볼 {
	
	static int N, M, K, massSum = 0;
	static ArrayList<fireball> list;
	static int[][] map;
	static int[][] direct = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		map = new int[N][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list.add(new fireball(x, y, m, s, d));
			map[x][y]++;
		}
		
		for (int i = 0; i < K; i++) {
			move();
			Boom();
		}
		
		for (int i = 0, size = list.size(); i < size; i++) {
			massSum += list.get(i).m;
		}
		System.out.println(-8 % 3);
		System.out.println(massSum);

	}
	
	static void Boom() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] < 2) continue;
				int[] tmp = new int[map[i][j]];
				map[i][j] = 4;				
				int ind = 0;
				int sSum = 0, mSum = 0, dOdd = 0, dEven = 0;
				for (int k = 0, size = list.size(); k < size; k++) {
					if(i == list.get(k).x && j == list.get(k).y) {
						tmp[ind++] = k;
						sSum += list.get(k).s;
						mSum += list.get(k).m;
						if(list.get(k).d % 2 == 0) dEven++;
						else dOdd++;
					}
				}
				
				System.out.println(Arrays.toString(tmp));
				for (int k = 0; k < ind; k++) {					
					list.remove(tmp[k]-k);
				}
				if(mSum / 5 > 0) {
					if(dOdd == 0 || dEven == 0) {
						list.add(new fireball(i, j, mSum / 5, sSum / ind, 0));
						list.add(new fireball(i, j, mSum / 5, sSum / ind, 2));
						list.add(new fireball(i, j, mSum / 5, sSum / ind, 4));
						list.add(new fireball(i, j, mSum / 5, sSum / ind, 6));
					}else {
						list.add(new fireball(i, j, mSum / 5, sSum / ind, 1));
						list.add(new fireball(i, j, mSum / 5, sSum / ind, 3));
						list.add(new fireball(i, j, mSum / 5, sSum / ind, 5));
						list.add(new fireball(i, j, mSum / 5, sSum / ind, 7));
					}
				}else map[i][j] = 0;
			}
		}
	}
	
	static void move() {
		for (int i = 0, size = list.size(); i < size; i++) {
			fireball cur = list.get(i);
			int nx = cur.x;
			int ny = cur.y;
			System.out.println(cur.x + " " + cur.y);
			for (int j = 0; j < cur.s; j++) {
				nx += direct[cur.d][0];
				ny += direct[cur.d][1];
			}
			nx %= N;
			ny %= N;
			nx = (nx + N) % N;
			ny = (ny + N) % N;
			map[cur.x][cur.y]--;
			map[nx][ny]++;
			list.get(i).x = nx;
			list.get(i).y = ny;
			
			System.out.println(list.get(i).toString());
		}
	}
	
	static class fireball{
		int x, y, m, s, d;

		public fireball(int x, int y, int m, int s, int d) {
			super();
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}

		@Override
		public String toString() {
			return "fireball [x=" + x + ", y=" + y + ", m=" + m + ", s=" + s + ", d=" + d + "]";
		}
		
		
	}

}
