# 210308 월 고정점 찾기 (Amazon 인터뷰)


"""
이전 문제를 풀어서 이번 문제는 비교적 쉽게 풀 수 있었다.
"""

import sys

N = int(sys.stdin.readline())
numbers = list(map(int, sys.stdin.readline().rstrip().split()))

def search(start, end):
    if start > end:
        return -1

    mid = (start + end) // 2

    if mid == numbers[mid]:
        return mid
    elif mid > numbers[mid]:
        return search(mid+1, end)
    else:
        return search(start, mid-1)

print(search(0, N-1))