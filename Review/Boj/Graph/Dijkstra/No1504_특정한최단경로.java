package rev.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class No1504_특정한최단경로 {
	
	static int N, E, v1, v2;
	static int[] distance, track;
	static boolean[] visited;
	static ArrayList<Node>[] adjList;
	static PriorityQueue<Node> pq;
	
	static final int INF = 2000000000;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());		
		
		track = new int[2];
		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken()) - 1;
			int u = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			
			adjList[v].add(new Node(u, w));
			adjList[u].add(new Node(v, w));
		}
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken()) - 1;
		v2 = Integer.parseInt(st.nextToken()) - 1;
		
		Dijkstra(0, v1, 0);
		Dijkstra(v1, v2, 0);
		Dijkstra(v2, N - 1, 0);
		
		Dijkstra(0, v2, 1);
		Dijkstra(v2, v1, 1);
		Dijkstra(v1, N - 1, 1);
		
		System.out.println(track[0] == INF && track[1] == INF ? -1 : Math.min(track[0], track[1]));		

	}
	
	static void Dijkstra(int start, int end, int trackType) {
		if (track[trackType] == INF) return;
		
		distance = new int[N];
		visited = new boolean[N];
		pq = new PriorityQueue<>();
		
		Arrays.fill(distance, INF);
		distance[start] = 0;		
		pq.offer(new Node(start, distance[start]));
		
		while (!pq.isEmpty()) {
			Node minEdge = pq.poll();
			if (visited[minEdge.v]) continue;
			
			if (minEdge.v == end) break;
			
			visited[minEdge.v] = true;
			
			for (Node n : adjList[minEdge.v]) {
				if (visited[n.v]) continue;
				distance[n.v] = Math.min(distance[n.v], distance[minEdge.v] + n.w);
				pq.offer(new Node(n.v, distance[n.v]));
			}
		}
		
		track[trackType] += distance[end];
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
