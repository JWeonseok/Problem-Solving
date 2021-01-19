# 210118 월 소수 찾기

"""
자료형의 효율적인 변환이 포인트인듯
다른 사람의 풀이는 map을 활용해서 set에서 답을 도출하여 풀이
또한 아리스토텔레스의 체를 for문 내부로 구현했다.
"""

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