# 200122 금 간단한 369게임

"""
문제 자체에 어려운 느낌은 없음
다만 효율적으로 짜려고 고민
아직 python 내장 함수의 효율성을 잘 활용못하는 듯
"""
T = int(input())
test = list(map(str, list(range(1, T+1))))
size = len(test)
for _ in range(size):
    num = test.pop(0)
    tmp = num.count("3") + num.count("6") + num.count("9")
    if tmp != 0:
        num = "-"*tmp
    test.append(num)
    
print(" ".join(test))