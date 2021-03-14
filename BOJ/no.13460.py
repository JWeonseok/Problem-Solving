# 210314 일 구슬 탈출2

"""
시뮬레이션 문제를 찾던 와중에 친구에게 추천 받은 문제이다
생각보다 변수가 많아서 좀 당황했다
첫 단추를 어떻게 끼는지에 따라 해결의 판도가 갈릴듯한 문제이다
코테에서 만나긴 싫다
"""

import sys
from collections import deque

N, M = map(int, sys.stdin.readline().rstrip().split())

board = [list(sys.stdin.readline().rstrip()) for _ in range(N)]

answer = 0

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

ball = []

for i in range(N):
    for j in range(M):
        if board[i][j] == "R":
            board[i][j] = "."
            ball.append([i, j, "R"])
        elif board[i][j] == "B":
            board[i][j] = "."
            ball.append([i, j, "B"])

def direction(ball, num):
    if num == 0:
        ball.sort(key=lambda x : x[0])
    elif num == 1:
        ball.sort(key=lambda x : x[1], reverse=True)
    elif num == 2:
        ball.sort(key=lambda x : x[0], reverse=True)
    else:
        ball.sort(key=lambda x : x[1])

q = deque()
q.append(ball)

def solution():

    global answer
    while q:
        size = len(q)
        answer += 1
        if answer > 10:
            break
        for _ in range(size):
            cur = q.popleft()
            for i in range(4):
                direction(cur, i)
                move_ball = []
                x1, y1, x2, y2 = cur[0][0], cur[0][1], cur[1][0], cur[1][1]
                chkr = False; chkb = False
                for b in cur:
                    nx = b[0]
                    ny = b[1]
                    while board[nx][ny] == ".":
                        nx += dx[i]
                        ny += dy[i]

                    if board[nx][ny] == "#":

                        nx -= dx[i]
                        ny -= dy[i]
                        board[nx][ny] = "#"
                        move_ball.append([nx, ny, b[2]])
                
                    elif board[nx][ny] == "O":
                        if b[2] == "B":
                            chkb = True
                        elif b[2] == "R":
                            chkr = True

                for mb in move_ball:
                        board[mb[0]][mb[1]] = "."
            
                if chkr and not chkb:
                    return True

                if (len(move_ball) == 2) and (x1 != move_ball[0][0] or y1 != move_ball[0][1] or x2 != move_ball[1][0] or y2 != move_ball[1][1]):
                    q.append(move_ball)
    return False




if solution() and answer <= 10:
    print(answer)
else:
    print(-1)