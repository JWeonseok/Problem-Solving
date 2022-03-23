package rev.dfsbfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No1068_트리 {
	
	static int N, rootNode, rmNode, leafNode = 0;
	static ArrayList<Integer>[] adjList;
	static Queue<Integer> q;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int v = Integer.parseInt(st.nextToken());
			if (v == -1) {
				rootNode = i;
				continue;
			}
			adjList[v].add(i);
		}
		
		rmNode = Integer.parseInt(br.readLine());
		
		q = new LinkedList<>();
		visited = new boolean[N];
		q.offer(rmNode);
		visited[rmNode] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (Integer in : adjList[cur]) {
				if (!visited[in]) {
					q.offer(in);
					visited[in] = true;
				}
			}
		}
		
		if (!visited[rootNode]) {
			q = new LinkedList<>();
			q.offer(rootNode);
		}		
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			boolean flag = false;
			for (Integer in : adjList[cur]) {
				if (!visited[in]) {
					q.offer(in);
					visited[in] = true;
					flag = true;
				}
			}
			if (!flag) leafNode++;
		}
		
		System.out.println(leafNode);
	}
}
