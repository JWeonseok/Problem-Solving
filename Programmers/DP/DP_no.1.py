# 210119 화 N으로 표현

"""
DP 첫 문제부터 멘탈 바사삭
중복을 제거하는데 in 보다 set을 사용할 것을 늦게 깨달았다.
이전에 Java로 풀때는 dfs의 재귀를 이용해서 풀었는데
python은 다른 풀이로 푼다는 것이 새삼 새로웠다.
programmers 테케는 맞췄는데 테케 5, 1010(답 7)은
결국 묻기로 했다.(다른 사람들과 내 코드의 차이가 없는데;)
"""

import math
def solution(N, number):
    if N == number:
        return 1
    temp = [[N]]
    for j in range(2, 9):
        r = []
        for i in range(math.ceil(len(temp)/2)):
            tmp1 = temp[i]
            tmp2 = temp[-(i+1)]
            for e1 in tmp1:
                for e2 in tmp2:
                    if e1 + e2 == number:
                        return j
                    elif e1 + e2 not in r:
                        r.append(e1 + e2)
                    if e1 - e2 == number:
                        return j
                    elif e1 - e2 not in r:
                        r.append(e1 - e2)
                    if e2 - e1 == number:
                        return j
                    elif e2 - e1 not in r:
                        r.append(e2 - e1)
                    if e1 * e2 == number:
                        return j
                    elif e1 * e2 not in r:
                        r.append(e1 * e2)
                    if e1 == 0 or e2 == 0:
                        if 0 not in r:
                            r.append(0)
                    elif e1 % e2 == 0:
                        if int(e1 / e2) == number:
                            return j
                        elif int(e1 / e2) not in r:
                            r.append(int(e1 / e2))
                    elif e2 % e1 == 0:
                        if int(e2 / e1) == number:
                            return j 
                        elif int(e2 / e1) not in r:
                            r.append(int(e2 / e1))
        if int(str(N)*j) == number:
            return j
        else:
            r.append(int(str(N)*j))
        temp.append(r)
    return -1

print(solution(5, 12)) # 4
print(solution(2, 11)) # 3