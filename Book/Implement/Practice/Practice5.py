# 210129 금 뱀(삼성 SW 역량 테스트)

"""
한 큐에 성공해서 뿌듯
규칙만 잘 이해하면 구현하는 데 어려움은 없다
해설은 게임 보드를 만들어서 풀었는데
나는 개인적으로 내 풀이가 좀 더 좋다^^
"""

N = int(input())
A = int(input())
apple = []
for _ in range(A):
    apple.append(list(map(int, input().split(" "))))
D = int(input())
move_t = []; move_d=[]
for _ in range(D):
    tmp = list(input().split(" "))
    move_t.append(int(tmp[0]))
    move_d.append(tmp[1])

move = [[0, 1], [1, 0], [0, -1], [-1, 0]]
snake = [[1, 1]]
time = 0

chk = True
cur_move = 0
answer = 0
while chk:
    time += 1
    snake.append([snake[-1][0] + move[cur_move][0], snake[-1][1] + move[cur_move][1]])
    
    if snake[-1][0] > N or snake[-1][1] > N or snake[-1][0] < 1 or snake[-1][1] < 1:
        answer = time
        break
    if snake[-1] in snake[:-1]:
        answer = time
        break
    
    if snake[-1] in apple:
        apple.remove(snake[-1])
    else:
        snake.pop(0)

    if move_t:    
        if time == move_t[0]:
            move_t.pop(0)
            direc = move_d.pop(0)
            if direc == 'D':
                cur_move = (cur_move + 1) % 4
            elif direc == 'L':
                cur_move = (cur_move - 1) % 4
    
print(answer)