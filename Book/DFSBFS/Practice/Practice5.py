# 210216 화 연산자 끼워 넣기 (삼성전자 SW 역량테스트)
import sys
N = int(sys.stdin.readline().rstrip())
num = list(map(int, sys.stdin.readline().rstrip().split()))
cal = list(map(int, sys.stdin.readline().rstrip().split()))
sum_cal = sum(cal)
result = []
perm = []

def calculation(num1, num2, c):
    if c == 1:
        return num1 + num2
    elif c == 2:
        return num1 - num2
    elif c == 3:
        return num1 * num2
    elif c == 4:
        return int(num1 / num2)

def recur(ind):

    if ind == sum_cal:
        tmp = num[0]
        for i in range(len(perm)):
            tmp = calculation(tmp, num[i+1], perm[i])
        result.append(tmp)
    
    for i in range(len(cal)):
        if cal[i] == 0:
            continue
        cal[i] -= 1
        perm.append(i+1)
        recur(ind + 1)
        cal[i] += 1
        perm.pop()

recur(0)
print(max(result))
print(min(result))
