# 210308 월 떡볶이 떡 만들기

import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
ddeok = list(map(int, sys.stdin.readline().rstrip().split()))

def binary_search(start, end):
    if start > end:
        return

    mid = (start + end) // 2

    cut = 0
    for el in ddeok:
        if el > mid:
            cut += (el - mid)

    if cut == M:
        return mid
    elif cut < M:
        return binary_search(start, mid-1)
    else:
        return binary_search(mid+1, end)


print(binary_search(0, max(ddeok)))
