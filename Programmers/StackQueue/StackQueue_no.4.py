# 210118 월 프린터

"""
구현에 어려움은 없었다
다만 아직 파이썬 코드 구현에 느낌이 없는 듯..
다른 사람 풀이로 enumerate를 사용하여 tuple을 이용
"""

def solution(priorities, location):
    answer = 0
    while(len(priorities)>0):
        cur = priorities.pop(0)
        location -= 1
        if len(priorities) == 0:
            answer+=1
            break
        if cur < max(priorities):
            priorities.append(cur)
            if location == -1:
                location = len(priorities)-1
        else:
            answer+=1
            if location == -1:
                break
    return answer

print(solution([1,1,9,1,1,1],0))