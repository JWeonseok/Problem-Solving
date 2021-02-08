# 210228 월 특정 거리의 도시 찾기

"""
그리 어렵지 않은 문제인거 같은데 인접 행렬에서 탐색은 많이 했지만
인접 리스트에서의 탐색은 경험이 없어서 오래 걸린 것 같다
"""

N, M, K, X = map(int, input().split())

city = dict()

for _ in range(M):
    k, i = map(int, input().split())
    if not city.get(k):
        city[k]=[i]
    else:
        city[k].append(i)

chk = [False] * N
q = [X]
chk[X-1] = True
cnt = 0
while q:    
    size = len(q)
    for _ in range(size):
        cur = q.pop(0)
        if city.get(cur):
            for element in city.get(cur):
                if not chk[element-1]:
                    chk[element-1] = True
                    q.append(element)
    cnt += 1
    if cnt == K:
        break
    
if not q:
    print(-1)
else:
    for answer in q:
        print(answer)