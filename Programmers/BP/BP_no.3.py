# 210119 화 카펫

"""
큰 문제 없었음
brown이랑 yellow 사이에 관계식이 있다고 하던데
굳이 몰라도 쉽게 풀 수 있음
math.sqrt 대신 **(1/2)
"""

import math
def solution(brown, yellow):
    answer = []
    
    for i in range(1, int(math.sqrt(yellow))+1):
        if yellow % i != 0:
            continue
        else:
            if (i + int(yellow / i)) * 2 + 4 == brown:
                answer.append(int(yellow / i) + 2)
                answer.append(i + 2)
    return answer

print(solution(10, 2))
print(solution(8, 1))
print(solution(24, 24))