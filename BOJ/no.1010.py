# 210118 월 다리놓기

"""
문제를 보고 combination만 떠올린다면 풀기 쉽겠다!..라고 생각했지만
itertools에 combinations를 이용하면 메모리 초과가 발생한다.
따라서 combination 정의에 따라 factorial로 풀어서 계산해야 한다
(이게 심지어 속도도 빠르다;) 
"""

import sys
import math
T = int(sys.stdin.readline().rstrip())
for _ in range(T):
    a, b = map(int, sys.stdin.readline().rstrip().split(" "))
    print(math.factorial(b) // (math.factorial(a) * math.factorial(b - a)))