# 210124 일 준환이의 운동관리

"""
SWEA D3 문제인데 D2보다 쉬웠다
정답률 높은 거로 고르긴 했는데 이건 좀..
"""

T = int(input())
for case in range(1, T+1):
    L, U, X = map(int, input().split(" "))
    if X < L:
        print(f"#{case} {L - X}")
    elif U < X:
        print(f"#{case} -1")
    else:
        print(f"#{case} 0")