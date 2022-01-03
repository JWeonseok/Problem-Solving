from collections import deque

# 각각의 터널 종류를 딕셔너리를 이용해서 표현해 줍니다.
tunnel = {
    0: (),
    1: ((1, 0), (0, 1), (-1, 0), (0, -1)),
    2: ((1, 0), (-1, 0)),
    3: ((0, 1), (0, -1)),
    4: ((-1, 0), (0, 1)),
    5: ((1, 0), (0, 1)),
    6: ((1, 0), (0, -1)),
    7: ((-1, 0), (0, -1))
}

for tc in range(1, int(input())+1):
    N, M, R, C, L = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(N)]
    q = deque([(R,C)])
    visit = [[0] * M for _ in range(N)]
    visit[R][C], cnt = 1, 1
    # 너비우선으로 탐색
    while q:
        x, y = q.popleft()
        print(x, y)
        for dx, dy in tunnel[board[x][y]]:
            nx, ny = x + dx, y + dy
            if not 0 <= nx < N or not 0<= ny < M:continue # 예외처리 1) 모서리
            if (-dx, -dy) in tunnel[board[nx][ny]]: # 예외처리 2) 연결부
                if not visit[nx][ny] and board[nx][ny]:
                    visit[nx][ny] = visit[x][y] + 1
                    q += [(nx, ny)]
                    if visit[nx][ny] <= L:cnt += 1
    print("#{} {}".format(tc, cnt))