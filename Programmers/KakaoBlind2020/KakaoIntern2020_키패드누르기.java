package programmers;

import org.junit.jupiter.api.Test;

/*
 * 211220 월 키패드 누르기 (2020 카카오 인턴)
 * 
 * 문제에 제시된 조건들을 정확히 구현해야 하는 간단한 구현 문제이다.
 * 조건들이 복잡하지 않은 문제였고 다만 키패드간 거리를 구하는 것이 키포인트로 보인다.
 * *, 0, # -> 10, 11, 12로 매치하여 각 키패드간 거리를 구할 수 있었다.
 * */

public class KakaoIntern2020_키패드누르기 {
	
	static int curL = 10, curR = 12;
	
	public String solution(int[] numbers, String hand) {
		String answer = "";
        
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7){
                answer += "L";
                curL = numbers[i];
                continue;
            }else if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9){
                answer += "R";
                curR = numbers[i];
                continue;
            }
            
            if(numbers[i] == 0) numbers[i] += 11;
            
            int lDist = getDist(curL, numbers[i]);
            int rDist = getDist(curR, numbers[i]);
            
            if(lDist < rDist){
                answer += "L";
                curL = numbers[i];
            }else if(lDist > rDist){
                answer += "R";
                curR = numbers[i];
            }else {
                if(hand.equals("left")){
                    answer += "L";
                    curL = numbers[i];
                }else{
                    answer += "R";
                    curR = numbers[i];
                }
            }
            
        }
        return answer;
	}
	
	public int getDist(int num1, int num2){
        int d = Math.abs(num1 - num2);
        return d / 3 + d % 3;
    }
	
	@Test
	public void Test() {
		System.out.println(solution(new int[] {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right"));
		System.out.println(solution(new int[] {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}, "left"));
		System.out.println(solution(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, "right"));
	}
}
