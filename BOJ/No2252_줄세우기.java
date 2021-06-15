package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 210615 화 줄세우기
 * 
 * 이 문제는 위상 정렬 개념을 적용하는 대표적인 문제이다.
 * 위상 정렬을 이해하고 구현할 수 있다면 그대로 적용하여 해결할 수 있다.
 * 나는 bfs 방법을 통해서 위상 정렬을 구현하였다.
 * */

public class No2252_줄세우기 {
	
	static int N, M;
	static int[] indegree;
	static ArrayList<Integer> order;
	static ArrayList<Integer>[] adj;
	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N+1];
		indegree = new int[N+1];
		order = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s1 = Integer.parseInt(st.nextToken());
			int s2 = Integer.parseInt(st.nextToken());
			adj[s1].add(s2);
			indegree[s2]++;
		}
		topologicalSort();
		
		for(Integer i : order) System.out.print(i + " ");

	}
	
	static void topologicalSort() {
		Queue<Integer> q = new LinkedList<Integer>();
		
		for (int i = 1; i <= N; i++) {
			if(indegree[i] == 0) q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int v = q.poll();
			order.add(v);
			for (Integer i : adj[v]) {
				indegree[i]--;
				
				if(indegree[i] == 0) q.offer(i);
			}			
		}
	}

}
