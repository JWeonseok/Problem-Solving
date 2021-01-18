# 210118 월 기능개발

def solution(progresses, speeds):
    answer = []
    
    ind=0
    cnt=0
    while(len(progresses) > 0):
        progresses[ind]+=speeds[ind]
        if progresses[ind] >= 100 and ind==0:
            progresses.pop(0)
            speeds.pop(0)
            cnt+=1
        else:
            ind+=1
            if ind == len(progresses):
                ind=0
            if cnt != 0:
                answer.append(cnt)
                cnt=0
    if cnt != 0:
        answer.append(cnt)
        cnt=0
        
    return answer

print(solution([93, 30, 55],[1, 30, 5]))
print(solution([95, 90, 99, 99, 80, 99],[1, 1, 1, 1, 1, 1]))