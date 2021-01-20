# 210120 수 쉬운 계단 수(DP)

"""
아쉬움(시간 초과)
알고리즘이 어렵진 않았는데 떠올리지 못함
밑에 코드를 보면 알수 있듯이 이 풀이는 DP가 아님
DP에 대한 이해력이 아직 부족한 듯
"""

import sys
T = sys.stdin.readline().rstrip()
dp = list(range(1, 10))
for _ in range(T-1):
    size = len(dp)
    for _ in range(size):
        e = dp.pop(0)
        if e == 0:
            dp.append(1)
        elif e == 9:
            dp.append(8)
        else:
            dp.append(e + 1)
            dp.append(e - 1)

print(len(dp) % 1000000000)