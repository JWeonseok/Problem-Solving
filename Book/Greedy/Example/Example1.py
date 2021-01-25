# 210125 월 큰 수의 법칙

N, M, K = map(int, input().split(" "))
test = list(map(int, input().split(" ")))

m1 = max(test)
test[test.index(m1)] = 0
m2 = max(test)

answer = 0
chk = 0
for _ in range(M):
    if chk < K:
        answer += m1
        chk += 1
    else:
        answer += m2
        chk = 0
print(answer)