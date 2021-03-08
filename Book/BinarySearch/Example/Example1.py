# 210308 월 부품 찾기

import sys

N = int(sys.stdin.readline().rstrip())
number = list(map(int, sys.stdin.readline().rstrip().split()))

M = int(sys.stdin.readline().rstrip())
search = list(map(int, sys.stdin.readline().rstrip().split()))

number.sort()

def binary_search(start, end, target):
    if start > end:
        return False

    mid = (start + end) // 2
    if number[mid] == target:
        return True
    elif number[mid] < target:
        return binary_search(mid+1, end, target)
    else:
        return binary_search(start, mid-1, target)


for el in search:
    if binary_search(0, N-1, el):
        print("yes", end=" ")
    else:
        print("no", end=" ")    