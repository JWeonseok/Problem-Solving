# 210127 수 게임 개발

N, M = map(int, input().split())
user = list(map(int, input().split()))
Map = []
answer = 1
move = {0: (-1, 0), 1: (0, -1), 2: (0, 1), 3: (1, 0)}
for _ in range(N):
    Map.append(list(map(int, input().split())))
    
chk = True
while chk:
    cur = [user[0], user[1]]
    for _ in range(4):
        direction = (user[2] - 1) % 4
        if Map[user[0]+move[direction][0]][user[1]+move[direction][1]] == 0:
            user[0] += move[direction][0]
            user[1] += move[direction][1]
            Map[user[0]][user[1]] = -1
            user[2] = direction
            answer += 1
            break
        else:
            user[2] = direction
    if cur[0] == user[0] and cur[1] == user[1]:
        if Map[user[0]+move[(user[2]-1)%4][0]][user[1]+move[(user[2]-1)%4][1]] != 1:
            user[0] = user[0]+move[(user[2]-1)%4][0]
            user[1] = user[1]+move[(user[2]-1)%4][1]
        else:
            chk = False
            
print(answer)   