package rev.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class No1753_최단경로 {
	
	static int V, E, start;
	static int[] distance;
	static boolean[] visited;
	static ArrayList<Node>[] adjList;
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		start = Integer.parseInt(br.readLine()) - 1;
		distance = new int[V];
		visited = new boolean[V];
		pq = new PriorityQueue<>();
		
		adjList = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken()) - 1;
			int u = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			
			adjList[v].add(new Node(u, w));
		}
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		
		pq.offer(new Node(start, distance[start]));
		
		while (!pq.isEmpty()) {
			Node minEdge = pq.poll();
			if (visited[minEdge.v]) continue;
			
			visited[minEdge.v] = true;
			
			for (Node n : adjList[minEdge.v]) {
				if (visited[n.v]) continue;
				distance[n.v] = Math.min(distance[n.v], distance[minEdge.v] + n.w);
				pq.offer(new Node(n.v, distance[n.v]));
			}
		}
		
		StringBuilder answer = new StringBuilder();
		for (Integer in : distance) {
			answer.append(in == Integer.MAX_VALUE ? "INF" : in).append('\n');
		}
		
		System.out.println(answer);

	}
	
	static class Node implements Comparable<Node>{
		int v, w;

		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}

}
