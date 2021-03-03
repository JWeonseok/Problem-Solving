# 210303 수 안테나 (2019 SW 마에스트로 입학 테스트)

import sys

N = int(sys.stdin.readline().rstrip())
house = list(map(int, sys.stdin.readline().rstrip().split()))

house.sort()
if len(house) % 2 == 1:
    print(house[(len(house)//2)+1])
else:
    ind1 = len(house)//2-1
    ind2 = len(house)//2
    tmp1 = 0; tmp2 = 0
    for h in house:
        tmp1 += abs(h - house[ind1])
        tmp2 += abs(h - house[ind2])
    if tmp1 >= tmp2:
        print(house[ind1])
    else:
        print(house[ind2])