# 210207 일 음료수 얼려 먹기

"""
오랜만의 코테 연습 + DFS/BFS 문제임에도 잘 풀었다ㅎ
이전에 Java로 DFS/BFS 문제 유형을 풀었던 경험이
아직 잊지 않고 잘 이해된 것 같다.
해설은 DFS를 이용해서 물었는데 내 직관으로는 BFS로 이해가 되서
그대로 풀었다 코드는 더 길지만..
"""

from collections import deque

answer = 0
N, M = map(int, input().split())
grid = [list(map(int, list(input()))) for _ in range(N)]
chk = [[False] * M for _ in range(N)]
queue = deque()

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

def chkvalid(x, y, M, N):
    if x < 0 or x > N-1 or y < 0 or y > M-1:
        return False
    return True

for i in range(N):
    for j in range(M):
        if grid[i][j] == 1 or chk[i][j]:
            continue
        else:
            answer += 1
            queue.append([i, j])
            chk[i][j] = True
            while queue:
                cur = queue.popleft()
                for k in range(4):
                    x = cur[0] + dx[k]
                    y = cur[1] + dy[k]
                    if chkvalid(x, y, M, N) and not chk[x][y] and not grid[x][y]:
                        chk[x][y] = True
                        queue.append([x, y])
print(answer)