# 연산자 끼워 넣기
import sys
import copy
N = int(sys.stdin.readline().rstrip())
num = list(map(int, sys.stdin.readline().rstrip().split()))
cal = list(map(int, sys.stdin.readline().rstrip().split()))
result = []
def recur(num, cal, ind):
    if sum(cal) == 0:
        result.append(num[-1])
        return

    for i in range(len(cal)):
        if cal[i] == 0:
            continue

        if i == 0:
            num[ind+1] += num[ind]
        elif i == 1:
            num[ind+1] = num[ind] - num[ind+1]
        elif i == 2:
            num[ind+1] *= num[ind]
        elif i == 3:
            num[ind+1] = num[ind] // num[ind+1]

        cal[i] -= 1
        ind += 1
        recur(num, cal, ind)
        #cal[i] += 1
        ind -= 1
recur(num, cal, 0)
#print(result)
print(max(result), min(result))