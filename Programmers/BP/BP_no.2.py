# 210118 월 소수 찾기

from itertools import permutations
def isPrime(n):
    if n < 2 :
        return False
    for i in range(2, int(n ** 0.5) + 1):
        if n % i == 0:
            return False
    return True

def solution(numbers):
    answer = 0
    num = [c for c in numbers]
    tmp = []
    for i in range(1, len(numbers) + 1):
        temp = list(permutations(num, i))
        for element in temp:
            tmp.append(int("".join(list(element))))
    tmp = list(set(tmp))
    for chk in tmp:
        if isPrime(chk):
            answer += 1
    return answer

print(solution("17"))
print(solution("011"))