# 210130 토 기둥과 보 설치

"""
완패..
기둥과 보, 설치와 삭제, 그리고 그 때마다의 유효성을 경우에 따라서
판별하려니 경우의 수가 너무 많았다..
해설에서는 시간과 입력값을 고려하여
설치와 삭제가 일어날 때마다 그냥 현재 상태 전체를 탐색하여 간단히 풀어냈다.
"""

def possible(answer):
    
    for build in answer:
        x, y, kind = build
        if kind:
            if [x, y-1, 0] in answer or [x+1, y-1, 0] in answer or ([x-1, y, 1] in answer and [x+1, y, 1] in answer):
                continue
            return False
        else:
            if y == 0 or [x, y-1, 0] in answer or [x, y, 1] in answer or [x-1, y, 1] in answer:
                continue
            return False
    return True
        
def solution(n, build_frame):
    answer = []
    
    for build in build_frame:
        x, y, kind, valid = build
        if valid:
            answer.append([x, y, kind])
            if not possible(answer):
                answer.remove([x, y, kind])
        else:
            answer.remove([x, y, kind])
            if not possible(answer):
                answer.append([x, y, kind])
    return sorted(answer)