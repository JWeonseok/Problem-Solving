# 210208 월 연구소 (삼성 SW 역량 테스트)

"""
내가 경험한 구현 관련해서 까다로운 것으로 top5안에 들듯 하다;
경우의 수에 따라서 탐색하고 count하는 과정이 굉장히 양이 많기 때문에
삐끗만해도 시간 초과가 발생한다;
심지어 내 코드는 pypy3가 아닌 python3으로 제출하면 시간 초과가 발생한다..
python3로 해결한 사람들의 풀이를 봤지만 아직 큰 차이를 찾지 못했다;
공부가 더 필요할 듯 하다
"""


import sys
from collections import deque
from itertools import combinations
import copy

N, M = map(int,sys.stdin.readline().rstrip().split())
grid = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(N)]
q = deque()
dx = [1,-1,0,0]
dy = [0,0,1,-1]

def chkvalid(x, y, N, M):
    if x < 0 or x >= N or y < 0 or y >= M:
        return False
    return True

temp = []
for i in range(N):
    for j in range(M):
        if grid[i][j] == 0:
            temp.append((i, j))
            
comb = list(combinations(temp, 3))
answer = 0
for c in comb:
    ans = 0
    tmp = copy.deepcopy(grid)
    for c1 in c:
        tmp[c1[0]][c1[1]] = 1
    
    for i in range(N):
        for j in range(M):
            if tmp[i][j] != 2:
                continue
            else:
                q.append([i, j])
                while q:
                    cur = q.popleft()
                    for k in range(4):
                        x = cur[0] + dx[k]
                        y = cur[1] + dy[k]
                        if chkvalid(x, y, N, M) and tmp[x][y] == 0:
                            q.append([x, y])
                            tmp[x][y] = 2
                            
    for i in range(N):
        for j in range(M):
            if tmp[i][j] == 0:
                ans += 1
    if answer < ans:
        answer = ans
        
print(answer)