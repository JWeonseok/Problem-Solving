# 210207 일 미로 탈출

"""
미로 문제라서 DFS라고 생각했다가 큰일 날 뻔했다;
도착점까지 최단 경로기 때문에 BFS가 맞는 문제였다
이와 비슷한 문제가 카카오 공채 기출에 있었는데 복습해야 겠다.
추가로 해설은 나아가면서 격자점의 값을 더해나가서 도착점의 값을
반환하는 방법으로 답을 도출했다. 이 부분도 숙지해야 겠다.
"""

from collections import deque

N, M = map(int, input().split())
grid = [list(map(int, list(input()))) for _ in range(N)]

q = deque()
chk = [[False] * M for _ in range(N)]

dx = [1,-1,0,0]
dy = [0,0,1,-1]

def chkvalid(x, y, N, M):
    if x < 0 or x >= N or y < 0 or y >= M:
        return False
    return True

q.append([0,0])
chk[0][0] = True

answer = 0
while q:
    size = len(q)
    answer += 1
    
    for _ in range(size):
        if not q:
            break
        cur = q.popleft()
        
        for i in range(4):
            x = cur[0] + dx[i]
            y = cur[1] + dy[i]
            if x == N-1 and y == M-1:
                answer += 1
                q.clear
                break
            if chkvalid(x, y, N, M) and not chk[x][y] and grid[x][y]:
                q.append([x, y])
                chk[x][y] = True
                
print(answer)