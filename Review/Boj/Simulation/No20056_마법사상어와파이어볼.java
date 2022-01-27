package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No20056_마법사상어와파이어볼 {
	
	static int N, M, K;
	static int[][] massMap, cntMap, valMap, dirMap, oddMap, evenMap;
	static Queue<FireBall> q;
	
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		q = new LinkedList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			q.offer(new FireBall(x, y, m, s, d));
		}
		
		for (int i = 0; i < K; i++) {
			moveFireBall();
		}
		
		int answer = 0;
		for(FireBall f : q) {
			answer += f.m;
		}
		
		System.out.println(answer);

	}
	
	static void moveFireBall() {
		
		massMap = new int[N][N];
		cntMap = new int[N][N];
		valMap = new int[N][N];
		oddMap = new int[N][N];
		evenMap = new int[N][N];
		dirMap = new int[N][N];
		
		while(!q.isEmpty()) {
			FireBall cur = q.poll();
			
			int nx = ((cur.x + dx[cur.d] * cur.s) % N + N) % N;
			int ny = ((cur.y + dy[cur.d] * cur.s) % N + N) % N;
			
			massMap[nx][ny] += cur.m;
			cntMap[nx][ny]++;
			valMap[nx][ny] += cur.s;
			dirMap[nx][ny] = cur.d;
			if(dirMap[nx][ny] % 2 == 0) evenMap[nx][ny]++;
			else oddMap[nx][ny]++;
			
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(cntMap[i][j] == 0) continue;
				if(cntMap[i][j] == 1) {
					q.offer(new FireBall(i, j, massMap[i][j], valMap[i][j], dirMap[i][j]));
				}else {
					int m = massMap[i][j] / 5;
					int v = valMap[i][j] / cntMap[i][j];
					
					if(m != 0) {
						if(oddMap[i][j] == 0 || evenMap[i][j] == 0) {
							q.offer(new FireBall(i, j, m, v, 0));
							q.offer(new FireBall(i, j, m, v, 2));
							q.offer(new FireBall(i, j, m, v, 4));
							q.offer(new FireBall(i, j, m, v, 6));
						}else {
							q.offer(new FireBall(i, j, m, v, 1));
							q.offer(new FireBall(i, j, m, v, 3));
							q.offer(new FireBall(i, j, m, v, 5));
							q.offer(new FireBall(i, j, m, v, 7));
						}
					}					
				}
				
			}
		}
	}
	
	static class FireBall {
		int x, y, m, s, d;

		public FireBall(int x, int y, int m, int s, int d) {
			super();
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

}
