# 210303 수 카드 정렬하기

"""
반례를 생각 못한 케이스
알고리즘 문제를 안 푼지 꽤 되긴 했지만
반례를 이렇게 찾기 힘든 경우도 오랜만인 것 같다
"""
import heapq

N = int(input())

card = []
for _ in range(N):
    heapq.heappush(card, int(input()))

answer = 0

for _ in range(N-1):
    tmp1 = heapq.heappop(card)
    tmp2 = heapq.heappop(card)
    answer += (tmp1 + tmp2)
    heapq.heappush(card, tmp1+tmp2)
print(answer)
