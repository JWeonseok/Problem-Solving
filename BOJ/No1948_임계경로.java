package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 210615 화 임계경로
 * 
 * 이 문제는 그래프 이론에서 위상 정렬의 개념을 통해 해결하는 문제이다.
 * 코딩테스트에서 그래프 이론을 난이도있는 문제로 출제되는 경우가 있어서
 * 공부하고 도전해봤다.
 * 나는 위상 정렬을 구현하는 여러 가지 방법에서 큐를 이용하는 bfs를 사용했는 데
 * 문제에서 도로의 개수를 구하는 부분이 해결되지 않았다.
 * 결론적으로 재귀를 이용하는 dfs를 사용해야 깔끔하게 해결이 된다.
 * 한 가지 알고리즘에 대해서 여러 구현 방법에 대해서도 익히는 것이 중요하다고 느꼈다.
 * */

public class No1948_임계경로 {
	
	static int n, m, start, end, time = 0, roadCnt = 0;
	static ArrayList<node>[] adj;
	static int[] indegree;
	
	static int[] d;
	static boolean[] chk;
	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[n+1];
		indegree = new int[n+1];
		
		for (int i = 0; i < n+1; i++) {
			adj[i] = new ArrayList<>();
		}
		StringTokenizer st = null;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[s].add(new node(e, w));
			indegree[e]++;
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
//		topologicalSort();
		
		d = new int[n+1];
		chk = new boolean[n+1];
		
		max_len(start);
		dfs(start);
		
		System.out.println(d[start]);
		System.out.println(roadCnt);
		
		
	}
	
	static void dfs(int cur) {
		if(chk[cur]) return;
		chk[cur] = true;
		
		for(node n : adj[cur]) {
			if(d[cur] == d[n.v] + n.w){
				roadCnt++;
				dfs(n.v);
			}
		}
	}
	
	static void max_len(int cur) {
		for(node n : adj[cur]) {
			if(d[n.v] == 0) max_len(n.v);
			d[cur] = Math.max(d[cur], d[n.v] +n.w);
		}
	}
	
//	static void topologicalSort() {
//		Queue<Integer> q = new LinkedList<>();
//		q.offer(start);
//		
//		while(!q.isEmpty()) {
//			int size = q.size();
//			int tmp = Integer.MIN_VALUE;
//			for (int i = 0; i < size; i++) {
//				int cur = q.poll();
//				if(cur == end) return;
//				for(node n : adj[cur]) {
//					indegree[n.v]--;
//					if(indegree[n.v] == 0) {
//						q.offer(n.v);
//						tmp = Math.max(tmp, n.w);
//					}
//				}				
//			}
//			time += tmp;
//		}
//	}
	
	static class node{
		int v;
		int w;
		public node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
		
	}

}
