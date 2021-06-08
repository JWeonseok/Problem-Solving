package programmers;

/*
 * 210608 화 신규아이디추천(2021 카카오 블라인드)
 * 
 * 최근 몇 번의 기업 코딩테스트 경험을 통해 문자열 문제가 거의 항상 나온다는 것을 알게되었다.
 * Java의 경우 문자열 처리가 Python에 비해 어려운 부분이 있기 때문에 많은 연습을 통해
 * 숙련도를 올려야 된다는 것을 느꼈다.
 * 이 문제는 2021 카카오 블라인드 테스트 1번 문제이며 문제의 조건만 신경쓰면 해결이 된다.
 * */
public class KakaoBlind2021_신규아이디추천 {

	public static void main(String[] args) {
		System.out.println(solution("...!@BaT#*..y.abcdefghijklm"));
		System.out.println(solution("z-+.^."));
		System.out.println(solution("=.="));
		System.out.println(solution("123_.def"));
		System.out.println(solution("abcdefghijklmn.p"));
	}
	
	static String solution(String new_id) {
		StringBuilder ans = new StringBuilder(new_id.toLowerCase());
		
		ans = deleteChar(ans);
		
		ans = deleteDuplicate(ans);
		
		if(ans.length() > 0 && ans.charAt(0) == '.') ans.deleteCharAt(0);
		if(ans.length() > 0 && ans.charAt(ans.length()-1) == '.') ans.deleteCharAt(ans.length()-1);
		
		if(ans.length() == 0) ans.append("a");
		
		if(ans.length() >= 16) ans.delete(15, ans.length());
		if(ans.length() > 0 && ans.charAt(ans.length()-1) == '.') ans.deleteCharAt(ans.length()-1);
		
		while(ans.length() < 3) {
			ans.append(ans.charAt(ans.length()-1));
		}
		
		
		return ans.toString();
	}
	
	static StringBuilder deleteChar(StringBuilder str) {
		int ind = 0;
		StringBuilder tmp = new StringBuilder(str);
		
		while(ind < tmp.length()) {
			char c = tmp.charAt(ind);
			if(('a' <= c && c <= 'z') || ('0' <= c && c <= '9') || c == '-' || c == '_' || c == '.') ind++;
			else tmp.deleteCharAt(ind);
		}
		
		return tmp;
	}
	
	static StringBuilder deleteDuplicate(StringBuilder str) {
		int ind = 0;
		StringBuilder tmp = new StringBuilder(str);
		
		while(ind < tmp.length()) {
			if(tmp.charAt(ind) != '.') ind++;
			else {
				int tind = ind;
				while(tind < tmp.length()) {
					if(tmp.charAt(tind) == '.') tind++;
					else break;
				}
				if(tind != ind) tmp.replace(ind, tind, ".");
				ind++;
			}
		}		
		return tmp;
	}

}
