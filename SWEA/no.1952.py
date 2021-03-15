# 210315 수 수영장 (모의 SW 역량테스트)

"""
재귀를 이용한 조합 문제로 간단하게 해결이 가능하다.
다른 사람의 풀이로 DP로 해결했다고 하는데
확실히 DP를 이용하면 좀 더 효율적인 풀이가 나올 것 같다.
"""
T = int(input())

def comb(ind, cnt):
    global m, price, month
    if ind > 11:
        if m > cnt:
            m = cnt
        return
    if month[ind] == 0:
        comb(ind+1, cnt)
    else:
        comb(ind+1, cnt + (month[ind] * price[0]))
        comb(ind+1, cnt + price[1])
        comb(ind+3, cnt + price[2])
    


for t in range(1, T+1):
    
    price = list(map(int, input().split()))
    month = list(map(int, input().split()))
    
    m = price[-1]

    comb(0, 0)

    print(f"#{t} {m}")