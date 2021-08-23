package programmers;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class WeeklyChallenge_4주차 {
	
	private Map<String, Integer> map;
	
	public String solution(String[] table, String[] languages, int[] preference) {
        String answer = "";
        
        String[][] scores = new String[table.length][6];
        map = new HashMap<>();
        
        for (int i = 0; i < scores.length; i++) {
			String[] tmp = table[i].split(" ");
			for (int j = 0; j < 6; j++) {
				scores[i][j] = tmp[j];
			}
		}
        
        for (int i = 0; i < languages.length; i++) {
			String cur = languages[i];
			
			for (int j = 0; j < scores.length; j++) {
				for (int k = 1; k < 6; k++) {
					if(scores[j][k].equals(cur)) {
						int score = preference[i] * (6 - k);
						if(map.containsKey(scores[j][0])) {
							map.put(scores[j][0], map.get(scores[j][0]) + score);
						}else map.put(scores[j][0], score);
					}
				}
			}
		}
        
        int min = Integer.MIN_VALUE;
        for(String key : map.keySet()) {
        	if(min < map.get(key)) {
        		min = map.get(key);
        		answer = key;
        	}else if(min == map.get(key)) {
        		if(answer.compareTo(key) > 0) {
        			answer = key;
        		}
        	}
        }
        
        return answer;
    }
	
	@Test
	public void Test(){
		System.out.println(solution(new String[] {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"},
									new String[] {"PYTHON", "C++", "SQL"}, new int[] {7, 5, 5}));
		System.out.println(solution(new String[] {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"},
									new String[] {"JAVA", "JAVASCRIPT"}, new int[] {7, 5}));
	}
}
