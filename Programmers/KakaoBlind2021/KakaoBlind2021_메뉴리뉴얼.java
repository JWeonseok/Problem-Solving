package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/*
 * 210617 목 메뉴 리뉴얼  (2021 카카오 블라인드)
 * 
 * 2021년 카카오 블라인드 코테 2번 문제인데
 * 책정된 난이도에 비해 체감상 어려움이 많았다;
 * 일단 문제가 이해가 된듯 안된듯 헷갈리는 부분이 있었고
 * 실제로 해답을 보고 왜 이렇게 푸는 지 이해하는 부분도 쉽지 않았다.
 * 이 문제의 해결법은 특별한 알고리즘을 적용하는 것 보다는
 * 조합을 이용하여 조건을 충족하면 해결된다.
 * */

public class KakaoBlind2021_메뉴리뉴얼 {
	
	static HashMap<String, Integer> map;
	static char[] tgt;
	static int[] count;
	
	public static void main(String[] args) {
		solution(new String[] {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[] {2, 3, 4});
		solution(new String[] {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, new int[] {2, 3, 5});
		solution(new String[] {"XYZ", "XWY", "WXA"}, new int[] {2, 3, 4});
		
		
	}
	
	static String[] solution(String[] orders, int[] course) {
		
		map = new HashMap<String, Integer>();
		count = new int[26];
        
        for (int i = 0, size = orders.length; i < size; i++) {
			String str = orders[i];
			for (Integer c : course) {
				if(str.length() >= c) {
					tgt = new char[c];
					comb(0, 0, str);
				}				
			}
		}
        
        ArrayList<String> list = new ArrayList<>(); 
        
        for (String menu : map.keySet()) {
        	if(map.get(menu) >= 2) count[menu.length()] = Math.max(count[menu.length()], map.get(menu));
		}
        
        for (int i = 0; i < 26; i++) {
        	if(count[i] == 0) continue;
			for (String menu : map.keySet()) {
				if(i == menu.length() && map.get(menu) == count[i]) list.add(menu);
			}
		}
        
        Collections.sort(list);
        
        String[] answer = new String[list.size()];
        
        for (int i = 0; i < answer.length; i++) {
			answer[i] = list.get(i);
		}
        
        System.out.println(Arrays.toString(answer));
        
        return answer;
    }
	
	static void comb(int srcind, int tgtind, String str) {
		if(tgtind == tgt.length) {
			char[] strary = tgt.clone();
			Arrays.sort(strary);
			String tmp = new String(strary);
			
			if(!map.containsKey(tmp)) map.put(tmp, 1);
			else {
				map.put(tmp, map.get(tmp) + 1);
			}
			return;
		}
		
		if(srcind == str.length()) return;		
		
		tgt[tgtind] = str.charAt(srcind);
		comb(srcind + 1, tgtind + 1, str);
		comb(srcind + 1, tgtind, str);
	}

}
