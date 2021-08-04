package programmers;

public class WeeklyChallenge_1주차 {

	public static void main(String[] args) {
		System.out.println(solution(3, 20, 4));

	}
	
	static long solution(int price, int money, int count) {
        long answer = (((long)count * (long)(count + 1)) / 2) * (long)price - (long)money;
        if(answer > 0) return answer;
        return 0;
    }

}
