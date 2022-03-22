package rev.dfsbfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No1240_노드사이의거리 {
	
	static int N, M;
	static ArrayList<Node>[] adjList;
	static Queue<Node> q;
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
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken()) - 1;
			int u = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			
			adjList[v].add(new Node(u, w));
			adjList[u].add(new Node(v, w));
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			
			q = new LinkedList<>();
			visited = new boolean[N];
			q.offer(new Node(start, 0));
			visited[start] = true;
			
			while (!q.isEmpty()) {
				Node cur = q.poll();
				
				if (cur.v == end) {
					System.out.println(cur.w);
					break;
				}
				
				for (Node node : adjList[cur.v]) {
					if (!visited[node.v]) {
						q.offer(new Node(node.v, node.w + cur.w));
						visited[node.v] = true;
					}
				}
			}
		}

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
