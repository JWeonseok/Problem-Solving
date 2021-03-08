# 210308 월 정렬된 배열에서 특정 수의 개수 구하기 (Zoho 인터뷰)

"""
이전에도 이분 탐색 알고리즘은 문제에 적용하는 것이 어려웠다;
이 문제의 키 포인트인 첫 번째와 마지막 index를 구하는 알고리즘을 해결하지 못했다.
이분 탐색 문제에 대한 연습이 더 필요한 것 같다.
추가로 bisect 라이브러리에 대해서 알게 되었다.
"""

import sys

N, M = map(int, sys.stdin.readline().rstrip().split())
numbers = list(map(int, sys.stdin.readline().rstrip().split()))

def first(start, end):

    if start > end:
        return None

    mid = (start + end) // 2

    if (mid == 0 or numbers[mid-1] < M) and numbers[mid] == M:
        return mid
    elif numbers[mid] >= M:
        return first(start, mid-1)
    else:
        return first(mid+1, end)

def last(start, end):

    if start > end:
        return None

    mid = (start + end) // 2

    if (mid == N-1 or numbers[mid+1] > M) and numbers[mid] == M:
        return mid
    elif numbers[mid] > M:
        return last(start, mid-1)
    else:
        return last(mid+1, end)

if not first(0, N-1):
    print(-1)
else:
    print(last(0, N-1) - first(0, N-1) + 1)

# from bisect import bisect_left, bisect_right

# # 값이 [left_value, right_value]인 데이터의 개수를 반환하는 함수
# def count_by_range(array, left_value, right_value):
#     right_index = bisect_right(array, right_value)
#     left_index = bisect_left(array, left_value)
#     return right_index - left_index

# n, x = map(int, input().split()) # 데이터의 개수 N, 찾고자 하는 값 x 입력 받기
# array = list(map(int, input().split())) # 전체 데이터 입력 받기

# # 값이 [x, x] 범위에 있는 데이터의 개수 계산
# count = count_by_range(array, x, x)

# # 값이 x인 원소가 존재하지 않는다면
# if count == 0:
#     print(-1)
# # 값이 x인 원소가 존재한다면
# else:
#     print(count)