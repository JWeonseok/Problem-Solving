# 210127 수 문자열 압축(2020 카카오 신입 공채)

"""
문제를 잘 못 이해해서 나 혼자 엄청 어렵게 풀고 있었다..
아직도 이런 실수를 하다니 너무 서두르지 말아야겠다.
"""

def solution(s):
    answer = len(s)
    M = len(s) // 2
    for i in range(1, M+1):
        tmp = ""
        digit = 1
        for j in range(0, len(s), i):
            if s[j:j+i] == s[i+j:j+2*i]:
                digit += 1
            else:
                if digit != 1:
                    tmp += str(digit)
                    digit = 1
                tmp += s[j:j+i]
        if answer > len(tmp):
            answer = len(tmp)
    return answer