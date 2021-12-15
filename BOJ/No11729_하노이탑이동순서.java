package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 211215 수 하노이 탑 이동 순서
 * 
 * 재귀 학습을 위한 좋은 문제인것 같다.
 * 이동 횟수는 간단한 점화식을 통해서 해결이 가능하지만 이동 경로가 생각해볼만한 점이다.
 * 일반적인 풀이로
 * 	func(n, from, by, to){
 * 		이동 횟수++
 * 		n이 1일때 "from to\n" 저장
 * 		그게 아니면 
 * 			func(n-1, from, to, by) // 1번에서 2번으로 이동
 * 			"1 3\n" 제일 큰 원판 3번으로 이동 
 * 			func(n-1, by, from, to) // 2번에서 3번으로 이동
 * 	}
 * 
 * 하지만 난 이러한 방식을 생각하지 못하여 sb의 길이만큼 반복문을 통해서 값을 직접 변경하였다.
 * 여기서 신기한건 두 방법의 시간이 비슷하다는 것인데
 * 위의 방법으로 이동 경로를 탐색할 경우 2^n번의 함수 호출이 일어나는 반면
 * 내 방법의 경우 n번의 함수 호출이 일어나기 때문에 시간이 비슷한 것 같다.
 * 하지만 역시 가독성 측면에서 재귀를 이용한 방법이 월등한 것 같다.
 * */

public class No11729_하노이탑이동순서 {
	
	static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		hanoi(1, 1, new StringBuilder("1 3\n"));

	}
	
	static void hanoi(int cnt, int val, StringBuilder sb) {
		if(cnt == N) {
			System.out.println(val);
			System.out.println(sb.toString());
			return;
		}
		
		hanoi(cnt + 1, 2 * val + 1, replace(sb));
	}
	
	static StringBuilder replace(StringBuilder sb) {
		StringBuilder rep = new StringBuilder();
		for (int i = 0; i < sb.length(); i++) {
			if(sb.charAt(i) == '3') {
				rep.append('2');
				continue;
			}else if(sb.charAt(i) == '2') {
				rep.append('3');
				continue;
			}
			rep.append(sb.charAt(i));
		}
		
		rep.append("1 3\n");
		
		for (int i = 0; i < sb.length(); i++) {
			if(sb.charAt(i) == '1') {
				rep.append('2');
				continue;
			}else if(sb.charAt(i) == '2') {
				rep.append('1');
				continue;
			}
			rep.append(sb.charAt(i));
		}
		return rep;
	}

}
