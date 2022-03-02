package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No17281_야구 {
	
	static int N, answer = Integer.MIN_VALUE, score;
	static int[][] players;
	static int[] seq, base;
	static boolean[] chk;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		players = new int[N][9];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				players[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		seq = new int[9];
		chk = new boolean[9];
		
		seq[3] = 0;
		chk[0] = true;
		
		perm(0);
		
		System.out.println(answer);

	}
	
	static void perm(int ind) {
		if (ind == 9) {
			score = 0;
			play();
			return;
		}		
		
		if (ind == 3) perm(ind + 1);
		else {
			for (int i = 1; i < 9; i++) {
				if (chk[i]) continue;
				
				seq[ind] = i;
				chk[i] = true;
				perm(ind + 1);
				chk[i] = false;
				
			}
		}
		
	}
	
	static void play() {
		
		int playerNum = 0;
		
		for (int i = 0; i < N; i++) {
			int outCnt = 0;
			base = new int[4];
			while (true) {				
				
				if (outCnt == 3) {
					score += base[0];
					break;
				}
				
				if (players[i][seq[playerNum]] == 0) outCnt++;
				else {
					run(players[i][seq[playerNum]]);
					base[players[i][seq[playerNum]] % 4]++;
				}				

				playerNum = (playerNum + 1) % 9;
			}
			
		}
		
		answer = Integer.max(answer, score);
	}
	
	static void run(int num) {
		for (int i = 3; i > 0; i--) {
			if (base[i] == 0) continue;
			
			if (num + i > 3) base[0]++;
			else base[num + i]++;
			base[i]--;
		}
	}

}
