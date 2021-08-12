package programmers;

public class WeeklyChallenge_2주차 {

	public static void main(String[] args) {

		System.out.println(solution(new int[][] {{100,90,98,88,65},{50,45,99,85,77},{47,88,95,80,67},{61,57,100,80,65},{24,90,94,75,65}}));
		System.out.println(solution(new int[][] {{50,90},{50,45,99,85,77},{47,88,95,80,67},{61,57,100,80,65},{24,90,94,75,65}}));
		System.out.println(solution(new int[][] {{100,90,98,88,65},{50,45,99,85,77},{47,88,95,80,67},{61,57,100,80,65},{24,90,94,75,65}}));

	}
	
	static String solution(int[][] scores) {
        String answer = "";
        
        for (int i = 0; i < scores.length; i++) {        	
        	int sum = 0, div = scores.length;
        	int maxInd = -1;
        	int maxScore = Integer.MIN_VALUE;
        	int minInd = -1;
        	int minScore = Integer.MAX_VALUE;
        	boolean maxflag = false, minflag = false;
			for (int j = 0; j < scores.length; j++) {
				sum += scores[j][i];
				if(maxScore < scores[j][i]) {
					maxScore = scores[j][i];
					maxInd = j;
					maxflag = false;
				}else if(maxScore == scores[j][i]) maxflag = true;
				if(minScore > scores[j][i]) {
					minScore = scores[j][i];
					minInd = j;
					minflag = false;
				}else if(minScore == scores[j][i]) minflag = true;
			}
			if(maxInd == i && !maxflag) {
				sum -= maxScore;
				div--;
			}
			if(minInd == i && !minflag) {
				sum -= minScore;
				div--;
			}
			float avg = (float)(sum) / (float)(div);
			
			if(avg >= 90) answer += "A";
			else if(avg >= 80) answer += "B";
			else if(avg >= 70) answer += "C";
			else if(avg >= 50) answer += "D";
			else answer += "F";
		}
        return answer;
    }

}
