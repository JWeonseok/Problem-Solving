# 210315 월 등산로 조성 (모의 SW 역량테스트)

"""
dfs를 이용하면 간단히 해결할 수 있는 문제다
다만 문제 설명을 차근히 읽어야 실수를 피할 수 있다.
"""

T = int(input())

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

def dfs(x, y, dist):
    global answer
    global flag

    if answer < dist:
        answer = dist

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if (0 <= nx < N) and (0 <= ny < N) and not chk[nx][ny]:
            if grid[nx][ny] < grid[x][y]:
                chk[nx][ny] = True
                dfs(nx, ny, dist+1)
                chk[nx][ny] = False
            elif not flag:
                for i in range(1, K+1):
                    if (grid[nx][ny] - i) < grid[x][y]:
                        chk[nx][ny] = True
                        grid[nx][ny] -= i
                        flag = True
                        dfs(nx, ny, dist+1)
                        flag = False
                        grid[nx][ny] += i
                        chk[nx][ny] = False


for t in range(1, T+1):
    N, K = map(int, input().split())
    grid = [list(map(int, input().split())) for _ in range(N)]

    answer = 0
    chk = [[False] * N for _ in range(N)]
    m = 0
    for i in range(N):
        if m < max(grid[i]):
            m = max(grid[i])
    
    for i in range(N):
        for j in range(N):
            flag = False
            if m == grid[i][j]:
                chk[i][j] = True
                dfs(i, j, 1)
                chk[i][j] = False

    print(f"#{t} {answer}")

