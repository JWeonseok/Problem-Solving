package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class No20924_트리의기둥과가지 {
	
	static int N, R;
	static int pillar, branch = Integer.MIN_VALUE;
	static boolean[] chk;
	static ArrayList<node>[] adjList;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[N + 1];
		chk = new boolean[N + 1];
		
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N - 1; i++) {			
			
			st = new StringTokenizer(br.readLine());
			
			int v = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adjList[v].add(new node(u, w));
			adjList[u].add(new node(v, w));			
		}
		
		for (int i = 0; i < 2; i++) {
			findGIGA(R, 0, i);
		}
		System.out.print(pillar + " ");
		System.out.println(branch);
		
	}
	
	static void findGIGA(int node, int len, int type) {
		
		chk[node] = true;
		
		if(type == 0) {
			if((adjList[node].size() > 2) || (R != node && adjList[node].size() == 1) || (adjList[node].size() == 2 && R == node)) {
				R = node;
				pillar = len;				
				return;
			}
		}else {
			branch = Math.max(branch, len);
		}
		
		for (int i = 0, size = adjList[node].size(); i < size; i++) {
			node cur = adjList[node].get(i);
			if(chk[cur.v]) continue;
			findGIGA(cur.v, len + cur.w, type);
		}
	}
	
	static class node {
		int v, w;

		public node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}		
	}

}
