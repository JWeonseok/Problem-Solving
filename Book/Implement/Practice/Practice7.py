# 210131 일 치킨 배달 (삼성전자 SW 역량테스트)

"""
해설과 동일하게 풀었다!
입력값과 문제의 조건, 내가 생각하는 알고리즘의 경우의 수를
생각해서 대충 이 정도면 이렇게 풀어도 되겠다! 하는 
견적 짜는 능력이 조금은 생긴 것 같다ㅎ
한 큐에 오류도 없이 해결해서 뿌-듯
"""

from itertools import combinations

N, M = map(int, input().split(" "))
house = []; chicken = []

for i in range(N):
    tmp = list(input().split())
    for j in range(len(tmp)):
        if tmp[j] == '1':
            house.append([i, j])
        elif tmp[j] == '2':
            chicken.append([i, j])

chk = list(combinations(chicken, M))

answer = 0
for case in chk:
    temp = 0
    for h in house:
        tmp = 2 * N
        for c in case:
            length = abs(h[0] - c[0]) + abs(h[1] - c[1])
            if tmp > length:
                tmp = length
        temp += tmp
    if answer == 0:
        answer = temp
    else:
        if answer > temp:
            answer = temp
print(answer)