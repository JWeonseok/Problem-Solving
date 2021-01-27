# 210127 수 볼링공 고르기 (2019 sw 마에스트로 입학 테스트)

"""
나쁘지 않게 풀었다
해답은 dictionary말고 리스트의 index를 활용했는데 참고
계산 과정도 좀 더 샤프하다 이것도 참고
"""
N, M = map(int, input().split())
balls = list(map(int, input().split()))
test = dict()
for ball in balls:
    if ball in test.keys():
        test[ball] += 1
    else:
        test[ball] = 1
answer = 0
chk = list(test.keys())
for i in range(len(chk)-1):
    for j in range(i+1, len(chk)):
        answer += (test[chk[i]]*test[chk[j]])
print(answer)