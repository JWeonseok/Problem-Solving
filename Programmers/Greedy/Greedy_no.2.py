# 210126 화 조이스틱

"""
확실하게 Greedy 느낌이 빡 드는 문제
그래서 구현만 잘해서 풀 수 있었다
좀 더 코드를 간결하게 쓸 수 있을 것 같지만
다른 사람들의 가독성 측면에서 이것도 나쁘진 않은듯
"""

def solution(name):
    answer = 0
    chk = ["A"] * len(name)
    name = list(name)
    ind = 0
    while "".join(chk) != "".join(name):
        if chk[ind] != name[ind]:
            tmp = ord(name[ind]) - ord(chk[ind])
            if tmp < 13:
                answer += tmp
            else:
                answer += (26 - tmp)
            chk[ind] = name[ind]
        for i in range(1, len(name)):
            if chk[(ind+i) % len(name)] != name[(ind+i) % len(name)]:
                answer += i
                ind = (ind+i) % len(name)
                break
            elif chk[(ind-i) % len(name)] != name[(ind-i) % len(name)]:
                answer += i
                ind = (ind-i) % len(name)
                break
    return answer