package rev.dfsbfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No1939_중량제한 {
	
	static int N, M, sNode, eNode, start = 0, end = 0;
	static ArrayList<Node>[] adjList;
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
			int w = Integer.parseInt(st.nextToken());
			
			adjList[v].add(new Node(u, w));
			adjList[u].add(new Node(v, w));
			
			end = Math.max(end, w);
		}
		
		st = new StringTokenizer(br.readLine());
		sNode = Integer.parseInt(st.nextToken()) - 1;
		eNode = Integer.parseInt(st.nextToken()) - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (bfs(mid)) start = mid + 1;
			else end = mid - 1;
		}
		
		System.out.println(end);

	}
	
	static boolean bfs(int weight) {
		
		q = new LinkedList<>();
		visited = new boolean[N];
		q.offer(sNode);
		visited[sNode] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			if (cur == eNode) return true;
			
			for (Node node : adjList[cur]) {
				if (!visited[node.v] && weight <= node.w) {
					q.offer(node.v);
					visited[node.v] = true;
				}
			}
		}
		
		return false;
		
		
	}
	
	static class Node {
		int v, w;

		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
	}

}
