# 210118 월 명령 프롬프트

"""
문제는 쉽다.
앞으로 입력은 input 대신 sys를 이용해야지
"""

import sys
T = int(sys.stdin.readline().rstrip())
file = [sys.stdin.readline().rstrip() for _ in range(T)]
answer = ""
word = file.pop(0)
for i in range(len(word)):
    w = word[i]
    chk = True
    for element in file:
        if w != element[i]:
            chk = False
    if chk:
        answer += w
    else:
        answer += "?"
print(answer)