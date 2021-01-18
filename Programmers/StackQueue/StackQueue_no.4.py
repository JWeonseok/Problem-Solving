# 210118 월 프린터

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