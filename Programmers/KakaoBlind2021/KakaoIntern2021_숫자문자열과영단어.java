package programmers;

public class KakaoIntern2021_숫자문자열과영단어 { 

	public static void main(String[] args) {
		
		System.out.println(solution("one4seveneight"));
		System.out.println(solution("23four5six7"));
		System.out.println(solution("2three45sixseven"));
		System.out.println(solution("123"));

	}
	
	static int solution(String s) {
		StringBuilder ans = new StringBuilder();
		
		for (int i = 0, len = s.length(); i < len; i++) {
			char cur = s.charAt(i);
			if(cur - '0' >= 0 && cur - '0' <= 9) ans.append(cur);
			else {
				if(cur == 'z') {
					ans.append('0');
					i += 3;
				}else if(cur == 'o') {
					ans.append('1');
					i += 2;
				}else if(cur == 't') {
					if(s.charAt(i + 1) == 'w') {
						ans.append('2');
						i += 2;
					}else {
						ans.append('3');
						i += 4;
					}
				}else if(cur == 'f') {
					if(s.charAt(i + 1) == 'o') {
						ans.append('4');
						i += 3;
					}else {
						ans.append('5');
						i += 3;
					}
				}else if(cur == 's') {
					if(s.charAt(i + 1) == 'i') {
						ans.append('6');
						i += 2;
					}else {
						ans.append('7');
						i += 4;
					}
				}else if(cur == 'e') {
					ans.append('8');
					i += 4;
				}else if(cur == 'n') {
					ans.append('9');
					i += 3;
				}
			}
		}
		
		return Integer.parseInt(ans.toString());
	}
	

}
