# 210125 월 숫자 카드 게임

N, M = map(int, input().split(" "))
test = []
answer = 0
for _ in range(N):
    m = min(list(map(int, input().split(" "))))
    if answer < m:
        answer = m
print(m)