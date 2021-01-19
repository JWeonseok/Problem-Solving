# 210119 화 등굣길

"""
비교적 복잡하진 않음
DP보다는 그리디 같지만..
puddles의 좌표를 반대로 입력해서 런타임에러 삽질
이래서 자신이 편한대로 좌표 기준을 정하라 했구나
(문제에서 설명이 부족하긴했음)
다른 사람 풀이는 위, 왼쪽에 padding을 사용
"""
def solution(m, n, puddles):
    road = [([0] * m) for _ in range(n)]
    for p in puddles:
        road[p[1] - 1][p[0] - 1] = -1
    road[0][0] = 1
    for i in range(n):
        for j in range(m):
            if road[i][j] == -1:
                continue
            if i == 0 and j == 0:
                continue
            if j == 0 and road[i - 1][j] != -1:
                road[i][j] = road[i - 1][j]
            elif i == 0 and road[i][j - 1] != -1:
                road[i][j] = road[i][j - 1]
            else:
                if road[i - 1][j] == -1:
                    road[i][j] = road[i][j - 1]
                elif road[i][j - 1] == -1:
                    road[i][j] = road[i - 1][j]
                else:
                    road[i][j] = (road[i - 1][j] + road[i][j - 1]) % 1000000007
    return road[n - 1][m - 1]

print(solution(4, 3, [[2, 2]]))