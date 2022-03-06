package rev.bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class No1062_가르침 {
	
	static int N, K, answer = 0;
	static HashSet<Character>[] alphaAry;
	static HashSet<Character> charSet;
	static char[] tgt;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		alphaAry = new HashSet[N];
		for (int i = 0; i < N; i++) {
			alphaAry[i] = new HashSet<>();
			String word = br.readLine();
			for (int j = 0; j < word.length(); j++) {
				alphaAry[i].add(word.charAt(j));
			}
		}
		
		if (K < 5) {
			System.out.println(0);
			return;
		}
		
		K -= 5;
		tgt = new char[K];
		comb(0, 0);
		
		System.out.println(answer);

	}
	
	static void ChkAlpha() {
		charSet = new HashSet<>();
		charSet.add('a');
		charSet.add('n');
		charSet.add('t');
		charSet.add('c');
		charSet.add('i');
		
		for (Character c : tgt) charSet.add(c);
		
		int cnt = 0;
		loop: for (int i = 0; i < N; i++) {
			for (Character c : alphaAry[i]) {
				if (!charSet.contains(c)) continue loop;
			}
			cnt++;
		}
		answer = Math.max(answer, cnt);
		
	}
	
	static void comb(int tgtInd, int srcInd) {
		if (tgtInd == K) {
			ChkAlpha();
			return;
		}
		
		if (srcInd == 26) return;
		
		char c = (char)(srcInd + 'a');
		if (c != 'a' && c != 'n' && c != 't' && c != 'c' && c != 'i') {
			tgt[tgtInd] = c;
			comb(tgtInd + 1, srcInd + 1);
		}
		comb(tgtInd, srcInd + 1);
	}

}
