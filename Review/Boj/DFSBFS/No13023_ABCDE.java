package rev.dfsbfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class No13023_ABCDE {
	
	static int N, M;
	static ArrayList<Integer>[] adjList;
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
			int v = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			
			adjList[v].add(u);
			adjList[u].add(v);
		}
		
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			visited[i] = true;
			dfs(i, 1);
		}
		
		System.out.println(0);

	}
	
	static void dfs(int node, int cnt) {
		if (cnt == 5) {
			System.out.println(1);
			System.exit(0);
		}
		
		for (Integer in : adjList[node]) {
			if (!visited[in]) {
				visited[in] = true;
				dfs(in, cnt + 1);
				visited[in] = false;
			}
		}
	}

}
