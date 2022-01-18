package rev;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No1091_카드섞기 {
	
	static int N, answer = 0;
	static int[] S, P, cards;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N];
		P = new int[N];
		cards = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			P[i] = Integer.parseInt(st.nextToken());
			cards[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}
		
		while(true) {
			if(chkValid()) {
				System.out.println(answer);
				break;
			}
			shuffle();
			answer++;
			if(chkEnd()) {
				System.out.println(-1);
				break;
			}
		}
		
	}
	
	static boolean chkValid() {
		for (int i = 0; i < N; i++) {
			if(P[cards[i]] != i % 3) return false;
		}
		return true;
	}
	
	static void shuffle() {
		int[] tmpCards = new int[N];
		for (int i = 0; i < N; i++) {
			tmpCards[S[i]] = cards[i];
		}
		for (int i = 0; i < N; i++) {
			cards[i] = tmpCards[i];
		}
	}
	
	static boolean chkEnd() {
		for (int i = 0; i < N; i++) {
			if(cards[i] != i) return false;
		}
		return true;
	}

}
