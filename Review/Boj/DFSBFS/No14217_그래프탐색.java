package rev.dfsbfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No14217_그래프탐색 {
	
	static int N, M, Q;
	static int[][] answer;
	static ArrayList<Integer>[] adjList;
	static Queue<Integer> q;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken()) - 1;
			int u = Integer.parseInt(st.nextToken()) - 1;
			
			adjList[v].add(u);
			adjList[u].add(v);
		}
		
		Q = Integer.parseInt(br.readLine());
		
		answer = new int[Q][N];
		for (int i = 0; i < Q; i++) {
			Arrays.fill(answer[i], -1);
		}
		
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken()) - 1;
			int u = Integer.parseInt(st.nextToken()) - 1;
			
			if (a == 1) {
				adjList[v].add(u);
				adjList[u].add(v);
			} else if (a == 2) {
				for (int j = 0; j < adjList[v].size(); j++) {
					if (adjList[v].get(j) == u) {
						adjList[v].remove(j);
						break;
					}
				}
				
				for (int j = 0; j < adjList[u].size(); j++) {
					if (adjList[u].get(j) == v) {
						adjList[u].remove(j);
						break;
					}
				}
			}
			
			q = new LinkedList<>();
			visited = new boolean[N];
			q.offer(0);
			visited[0] = true;
			
			int cnt = 0;
			while (!q.isEmpty()) {
				int size = q.size();
				for (int j = 0; j < size; j++) {
					int cur = q.poll();
					answer[i][cur] = cnt;
					
					for (Integer in : adjList[cur]) {
						if (!visited[in]) {
							q.offer(in);
							visited[in] = true;
						}
					}
				}
				cnt++;
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(answer[i][j]).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb);

	}

}
