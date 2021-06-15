package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 210615 화 ACM Craft
 * 
 * 이 문제를 처음 접했을 때 그냥 bfs를 이용하여 해결하려다가 애를 먹었다.
 * 결론적으로 자신을 가리키는 노드의 개수를 저장하는 배열인 indegree를
 * 이용하는 위상 정렬 문제이다.
 * 또한 건설시간을 구하는 과정을 DP를 이용하면 해결이 가능하다.
 * */

public class No1005_ACM_Craft {
	
	static int T, N, K, tgt, answer;
	static int[] building, dp, indegree;
	static ArrayList<Integer>[] graph;
	static Queue<Integer> q;
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[N+1];
			building = new int[N+1];
			dp = new int[N+1];
			indegree = new int[N+1];
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 1; i <= N; i++) {
				dp[i] = building[i] = Integer.parseInt(st.nextToken());
				graph[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());				
				graph[start].add(end);
				indegree[end]++;
			}
			
			tgt = Integer.parseInt(br.readLine());
			
			topologicalSort();
			
			System.out.println(dp[tgt]);
		}

	}
	
	static void topologicalSort() {
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if(indegree[i] == 0) q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int v = q.poll();
			for (int i = 0, size = graph[v].size(); i < size; i++) {
				dp[graph[v].get(i)] = Math.max(dp[graph[v].get(i)], dp[v] + building[graph[v].get(i)]);
				indegree[graph[v].get(i)]--;
				
				if(indegree[graph[v].get(i)] == 0) q.offer(graph[v].get(i));
			}
		}
	}

}
