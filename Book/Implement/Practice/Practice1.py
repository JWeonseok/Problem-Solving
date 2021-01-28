# 210127 수 럭키 스트레이트

"""
쉬운 문제
"""

N = list(map(int, list(input())))
if sum(N[:len(N)//2]) == sum(N[len(N)//2:]):
    print("LUCKY")
else:
    print("READY")