# 210217 수 인구 이동 (삼성전자 SW 역량테스트)

"""
실수때매 시간을 많이 낭비했다;
알고리즘 자체는 그리 어렵지 않다
요즘 잔실수로 자꾸 시간을 많이 소요하는데 이 부분도 신경써야할 거 같다
"""

import sys

N, L, R = map(int, sys.stdin.readline().rstrip().split())
grid = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(N)]

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

answer = 0

def checkvalid(x, y):
    if x < 0 or x >= N or y < 0 or y >= N:
        return False
    return True

def check(c1, c2):
    if(L <= abs(c1 - c2) <= R):
        return True
    return False

while True:
    temp = []
    chk = [[False] * N for _ in range(N)]
    q = []

    for i in range(N):
        for j in range(N):
            if chk[i][j]:
                continue
            tmp = [[i, j]]
            q.append([i, j])
            chk[i][j] = True
            while q:
                cur = q.pop(0)
                for k in range(4):
                    x = cur[0] + dx[k]
                    y = cur[1] + dy[k]
                    if checkvalid(x, y) and not chk[x][y] and check(grid[cur[0]][cur[1]], grid[x][y]):
                        q.append([x, y])
                        chk[x][y] = True
                        tmp.append([x, y])
        
            if len(tmp) > 1:
                temp.append(tmp)
    if not temp:
        break

    for t1 in temp:
        s = 0
        for t2 in t1:
            s += grid[t2[0]][t2[1]]
        for t2 in t1:
            grid[t2[0]][t2[1]] = s // len(t1)
    
    answer += 1

print(answer)