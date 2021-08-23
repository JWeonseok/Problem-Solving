package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class No16964_스페셜저지 {
	
	static int N;
	static int[] order, answer;
	static boolean[] chk;
	static ArrayList<Integer>[] adjList;
	static ArrayList<Integer> result;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		adjList = new ArrayList[N + 1];
		chk = new boolean[N + 1];
		answer = new int[N];
		order = new int[N];
		result = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adjList[v].add(w);
			adjList[w].add(v);
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			answer[i] = Integer.parseInt(st.nextToken());
			order[answer[i] - 1] = i;
		}
		
		for (int i = 1; i <= N; i++) {
			Collections.sort(adjList[i], new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return order[o1-1] - order[o2-1];
				}
				
			});
		}
		
		dfs(1);
		
		for (int i = 0; i < N; i++) {
			if(answer[i] != result.get(i)) {
				System.out.println(0);
				System.exit(0);
			}
		}
		System.out.println(1);
	}
	
	static void dfs(int v) {
		if(chk[v]) return;
		chk[v] = true;
		
		result.add(v);
		
		for (int i = 0, size = adjList[v].size(); i < size; i++) {
			if(!chk[adjList[v].get(i)]) dfs(adjList[v].get(i));
		}
		
	}

}
