package rev.dfsbfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No11725_트리의부모찾기 {
	
	static int N;
	static ArrayList<Integer>[] adjList;
	static int[] answer;
	static Queue<Integer> q;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		answer = new int[N];
		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken()) - 1;
			int u = Integer.parseInt(st.nextToken()) - 1;
			
			adjList[v].add(u);
			adjList[u].add(v);
		}
		
		q = new LinkedList<>();
		visited = new boolean[N];
		q.offer(0);
		visited[0] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (Integer in : adjList[cur]) {
				if (!visited[in]) {
					q.offer(in);
					visited[in] = true;
					answer[in] = cur;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < N; i++) {
			sb.append(answer[i] + 1).append('\n');
		}
		System.out.println(sb);

	}

}
