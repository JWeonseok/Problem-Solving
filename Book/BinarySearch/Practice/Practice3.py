# 210308 월 공유기 설치

"""
이분 탐색 응용 문제는 아무리 생각해도 어렵다..
연습이 부족한 것도 있지만 해답을 보고 오랜 시간을 고민해서
약간이나마 왜 이렇게 풀었는지 이해를 했다.
많은 연습만이 답인 듯 하다..
"""

import sys

N, C = map(int, sys.stdin.readline().rstrip().split())

house = [int(sys.stdin.readline()) for _ in range(N)]

house.sort()

start = 1
end = house[-1] - house[0]
answer = 0

while start <= end:
    cur = house[0]
    mid = (start + end) // 2
    cnt = 1
    for i in range(1, N):
        if house[i] - cur >= mid:
            cnt += 1
            cur = house[i]

    if cnt >= C:
        answer = mid
        start = mid + 1
    else:
        end = mid - 1

print(answer)