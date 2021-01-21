# 210121 목 백만 장자 프로젝트

"""
만만히 보다가 당함
시간 효율을 위해서 약간의 아이디어가 필요
이렇게 함수로 떡칠하는 것(내 코드)보다
변수를 좀 써서 푸는 것이 시간 효율이 좋음
"""


T = int(input())
for j in range(T):
    value = 0
    n = int(input())
    price = list(map(int, list(input().split(" "))))
    while(len(price) > 1):
        value += (len(price[:price.index(max(price)) + 1]) * max(price) - sum(price[:price.index(max(price)) + 1]))
        price = price[price.index(max(price)) + 1:]
    print("#"+str(j+1)+" "+str(value))