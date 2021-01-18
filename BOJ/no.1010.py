# 210118 월 다리놓기

import sys
import math
T = int(sys.stdin.readline().rstrip())
for _ in range(T):
    a, b = map(int, sys.stdin.readline().rstrip().split(" "))
    print(math.factorial(b) // (math.factorial(a) * math.factorial(b - a)))