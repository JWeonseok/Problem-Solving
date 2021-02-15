# 210215 월 괄호 변환

"""
생각보다 쉽게 풀었다
이전에 Java로 풀었을 때 꽤 애먹었던 기억이 있는데
python이 편한 언어인지 내가 실력이 늘었는지..ㅎ
해설과 비슷한 풀이
"""

def solution(p):
    answer = ''
    
    if not p or chkgood(p):
        return p
    u = ""; v = ""
    for char in p:
        if not chkbalance(u):
            u += char
        else:
            v += char
    if chkgood(u):
        return u + solution(v)
    else:
        answer += "("
        answer += solution(v)
        answer += ")"
        u = u[1:-1]
        tmp = ""
        for char in u:
            if char == "(":
                tmp += ")"
            else:
                tmp += "("
        answer += tmp
        return answer

def chkbalance(p):
    if not p:
        return False
    l = 0; r = 0
    for char in p:
        if char == "(":
            l += 1
        else:
            r += 1
    if l == r:
        return True
    else:
        return False

def chkgood(p):
    if not p:
        return False
    r = 0; l = 0
    for char in p:
        if char == "(":
            l += 1
        else:
            r += 1
        if l < r:
            return False
    if r != l:
        return False
    return True