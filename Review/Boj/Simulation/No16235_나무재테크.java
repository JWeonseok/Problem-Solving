package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No16235_나무재테크 {
	
	static int N, M, K;
	static int[][] map, food;
	static LinkedList<Tree> trees;
	static Queue<Tree> deadTrees;

	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		food = new int[N][N];
		trees = new LinkedList<>();
		deadTrees = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = 5;
				food[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			
			trees.add(new Tree(x, y, age));
		}
		
		
		
		for (int i = 0; i < K; i++) {
			Spring();
			Summer();
			Fall();
			Winter();
		}
		
		System.out.println(trees.size());
	}
	
	static void Spring() {
		
		Iterator<Tree> iterator = trees.iterator();		
		while(iterator.hasNext()) {
			Tree cur = iterator.next();
			if(cur.age > map[cur.x][cur.y]) {
				deadTrees.offer(cur);
				iterator.remove();
				continue;
			}
			map[cur.x][cur.y] -= cur.age;
			cur.age++;
		}
	}
	
	static void Summer() {
		while(!deadTrees.isEmpty()) {
			Tree deadTree = deadTrees.poll();
			map[deadTree.x][deadTree.y] += deadTree.age / 2;
		}
	}
	
	static void Fall() {
		LinkedList<Tree> babyTrees = new LinkedList<>();
		
		for(Tree t : trees) {
			if(t.age % 5 != 0) continue;
			
			for (int j = 0; j < 8; j++) {
				int nx = t.x + dx[j];
				int ny = t.y + dy[j];
				
				if(chkValid(nx, ny)) babyTrees.add(new Tree(nx, ny, 1));
				
			}
		}
		trees.addAll(0, babyTrees);
	}
	
	static void Winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += food[i][j];
			}
		}
	}
	
	static boolean chkValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) return false;
		return true;
	}
	
	static class Tree {
		int x, y, age;

		public Tree(int x, int y, int age) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
		}
	}

}
