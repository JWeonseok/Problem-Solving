# 210118 월 기능개발

"""
알고리즘 자체는 어렵지 않으나
효율성 측면에서 고민함(ex 과도한 연산, 메모리)
다른 사람 풀이는 zip을 이용하고 복합적인 조건 판별로 줄임
"""

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