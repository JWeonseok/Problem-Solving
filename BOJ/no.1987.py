# 210306 토 알파벳

"""
최근 들어서 가장 고민을 많이 한 문제인 것 같다.
하지만 덕분에 백트래킹 관련 문제에 대한 이해도가 높아졌다.
나는 백트래킹 문제를 주로 stack을 이용해서 풀었다
하지만 그럴 경우 제한점이 많은 것을 알게 되었고 재귀를 이용하여
해결할 수 있었다.
그러나 방문한 알파벳을 판별하는 과정에서 in 문법을 쓰게 되면
시간 초과로 해결할 수 없게 된다.
따라서 밑과 같이 index를 이용해서 방문 알파벳을 표시해야 한다.
"""

import sys

R, C = map(int, sys.stdin.readline().rstrip().split())

grid = [list(map(lambda x : ord(x) - 65, sys.stdin.readline().rstrip())) for _ in range(R)]

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

visited = [0] * 26
visited[grid[0][0]] = 1
answer = 0

def dfs(x, y, dist):
    global answer
    
    answer = max(answer, dist)

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if((0 <= nx < R) and (0 <= ny < C) and visited[grid[nx][ny]] == 0):
            visited[grid[nx][ny]] = 1
            dfs(nx, ny, dist+1)
            visited[grid[nx][ny]] = 0

dfs(0, 0, 1)

print(answer)