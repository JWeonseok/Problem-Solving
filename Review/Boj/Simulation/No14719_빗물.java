package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No14719_빗물 {
	
	static int H, W, answer = 0;
	static int[] walls;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		walls = new int[W];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			walls[i] = Integer.parseInt(st.nextToken());
		}
		
		int leftSide = walls[0];
		
		for (int i = 1; i < W - 1; i++) {
			if(leftSide <= walls[i]) {
				leftSide = walls[i];
				continue;
			}
			int rightSide = walls[i];
			for (int j = i + 1; j < W; j++) {
				if(rightSide < walls[j]) rightSide = walls[j];
			}
			if(rightSide > walls[i]) answer += (Math.min(leftSide, rightSide) - walls[i]);
		}
		
		System.out.println(answer);

	}

}
