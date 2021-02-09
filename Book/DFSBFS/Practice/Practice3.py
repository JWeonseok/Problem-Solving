# 210209 화 경쟁적 전염

"""
처음엔 번호가 낮은 순서로 pop되어야 하므로 우선순위 큐를 사용했다가
논리적 오류를 알아내서 큐를 처음만 정렬하고 실행하는 것으로 수정하여 해결했다.
해설도 동일한 풀이로 해결
"""

import sys

N, K = map(int, sys.stdin.readline().rstrip().split())
grid = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(N)]
S, X, Y = map(int, sys.stdin.readline().rstrip().split())

dx = [1,-1,0,0]
dy = [0,0,1,-1]

q = []
for i in range(N):
    for j in range(N):
        if grid[i][j] != 0:
            q.append([grid[i][j], i, j])
q.sort()
def chkvalid(x, y):
    if x < 0 or x >= N or y < 0 or y >= N:
        return False
    return True
time = 0
while q:
    if time == S:
        break

    time += 1
    size = len(q)
    for _ in range(size):
        cur = q.pop(0)
        for i in range(4):
            x = cur[1] + dx[i]
            y = cur[2] + dy[i]
            if chkvalid(x, y) and grid[x][y] == 0:
                grid[x][y] = cur[0]
                q.append([grid[x][y], x, y])

              
print(grid[X-1][Y-1])