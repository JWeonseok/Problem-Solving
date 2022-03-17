package rev.gametheory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No15900_나무탈출 {
	
	static int N;
	static ArrayList<Integer>[] adjList;
	static Queue<Integer> q;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N + 1];
		adjList = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			
			adjList[v].add(u);
			adjList[u].add(v);
		}
		
		q = new LinkedList<>();
		q.offer(1);
		visited[1] = true;
		int cnt = 0, sum = 0;
		
		while (!q.isEmpty()) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				int cur = q.poll();
				
				boolean leafFlag = false;
				for (Integer in : adjList[cur]) {
					if (!visited[in]) {
						q.offer(in);
						visited[in] = true;
						leafFlag = true;
					}
				}
				if (!leafFlag) sum += cnt;
			}
			cnt++;
		}
		
		if (sum % 2 == 1) System.out.println("Yes");
		else System.out.println("No");

	}

}
