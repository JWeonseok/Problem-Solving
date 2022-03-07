package rev.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class No1916_최소비용구하기 {
	
	static int N, M, start, end;
	static int[] distance;
	static boolean[] visited;
	static ArrayList<Node>[] adjList;
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		distance = new int[N];
		visited = new boolean[N];
		adjList = new ArrayList[N];
		pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken()) - 1;
			int u = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			
			adjList[v].add(new Node(u, w));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken()) - 1;
		end = Integer.parseInt(st.nextToken()) - 1;
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		
		pq.offer(new Node(start, distance[start]));
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (visited[cur.v]) continue;
			
			if (cur.v == end) break;
			visited[cur.v] = true;
			
			for (Node n : adjList[cur.v]) {
				if (visited[n.v]) continue;
				distance[n.v] = Math.min(distance[n.v], distance[cur.v] + n.w);
				pq.offer(new Node(n.v, distance[n.v]));
			}
		}
		
		System.out.println(distance[end]);

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
