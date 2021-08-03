package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KakaoBlind2021_순위검색 {
	
	static Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();

	public static void main(String[] args) {
		
		String[] info = new String[] {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = new String[] {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		
		System.out.println(Arrays.toString(solution(info, query)));

	}
	
	static int[] solution(String[] info, String[] query) {
int[] answer = new int[query.length];
		
		for (int i = 0, len = info.length; i < len; i++) {
			String[] tmp = info[i].split(" ");
			makeMap("", 0, tmp);
		}
		
		for(String key : map.keySet()) {
			Collections.sort(map.get(key));
		}
		
		for (int i = 0, len = query.length; i < len; i++) {
			String[] tmp = query[i].replace(" and ", "").split(" ");
            
            if(!map.containsKey(tmp[0])) {
				answer[i] = 0;
				continue;
			}
			
			List<Integer> list = map.get(tmp[0]);
			int tgt = Integer.parseInt(tmp[1]);
			
			int start = 0, end = list.size() - 1, mid = 0;
			
			while(start <= end) {
				mid = (start + end) / 2;
				if(tgt > list.get(mid)) start = mid + 1;
				else end = mid - 1;				
			}
			
			answer[i] = list.size() - start;
		}		
		
		return answer;
	}
	
	
	static void makeMap(String str, int depth, String[] tmp) {
		if(depth == 4) {
			if(map.containsKey(str)) map.get(str).add(Integer.parseInt(tmp[4]));
			else {
				List<Integer> list = new ArrayList<>();
				list.add(Integer.parseInt(tmp[4]));
				map.put(str, list);
			}
			return;
		}
		
		makeMap(str + tmp[depth], depth + 1, tmp);
		makeMap(str + "-", depth + 1, tmp);
	}

}
