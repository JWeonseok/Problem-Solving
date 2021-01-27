# 210127 수 만들 수 없는 금액

"""
결국 못풀고 해답을 보았다;
솔직히 DP 느낌이 더 나서 풀다가 경우의 수가 너무 많아서
해답을 보고 이런 식으로 풀 수 있다는 거에 아직 모자람을 많이 느꼈다
"""
"""
N = int(input())
coins = list(map(int, input().split()))
t = 1
for coin in coins:
    if t < coin:
        break
    t += coin
print(t)
"""