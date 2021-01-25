# 210125 월 1이 될 때까지

N, K = map(int, input().split(" "))

answer = 0
while N != 1:
    while N > 1 and N % K != 0:
        N -= 1
        answer += 1
    while N > 1 and N % K == 0:
        N //= K
        print(N)
        answer += 1
print(answer)