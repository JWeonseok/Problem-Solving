package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

/*
 * 211221 화 가장 큰 수
 * 
 * 프로그래머스 연습 문제 키트 정렬 파트 문제이다.
 * 정렬 문제는 항상 어떻게 정렬할 것인가가 중요한데
 * 결론적으로 나는 해결하지 못하고 해설을 참고했다.
 * 
 * 내 해결 방법은 조건에 충족하도록 수들의 각 자리수를 비교하는 방식으로 진행하였다.
 * 하지만 이 방법은 반례들이 많아 추가해야하는 조건들이 너무 많아지고 결국 풀지 못했다.
 * 
 * 해설의 방법은 단순하면서 확실한데 두 수를 세워보면서 그때마다 큰지 작은지를 판별하여 정렬하는 방법이다.
 * 이 방법은 응용도가 높아 보여서 잘 이해해야 할 것 같다.
 * */
public class Basic_Sort_가장큰수 {
	
	public String solution(int[] numbers) {
        String answer = "";
        
        ArrayList<String> aryList = new ArrayList<>();

        for(int nums : numbers) aryList.add(Integer.toString(nums));
        
        Collections.sort(aryList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return (o1 + o2).compareTo(o2 + o1);
			}
		});
        
        if(aryList.get(0).equals("0")) return "0";
        
        for(String str : aryList) answer += str;
        
        return answer;
	}
        
        
       
	
	@Test
	public void Test() {
		System.out.println(solution(new int[] {6, 10, 2}));
		System.out.println(solution(new int[] {3, 30, 34, 5, 9}));
	}

}
