# 210126 화 체육복

"""
문제 조건 이해 못해서 삽질1
python에서 dynamic하게 처리하는 부분 생각 못해서 삽질2
엣지 케이스를 생각 좀 하자
갈 길이 멀다
"""

def solution(n, lost, reserve):
    answer = n
    
    for i in range(len(lost)):
        if lost[i] in reserve:
            reserve[reserve.index(lost[i])] = 0
            lost[i] = 0
    
    lost = list(filter(lambda x : x != 0, lost))
    reserve = list(filter(lambda x : x != 0, reserve))
    
    for i in range(1, n+1):
        if i in lost:
            if i-1 in reserve:
                reserve.remove(i-1)
            elif i+1 in reserve:
                reserve.remove(i+1)
            else:
                answer -= 1
                
    return answer

print(solution(5, [2, 4], [1, 3, 5]), 5)
print(solution(5, [2, 4], [3]), 4)
print(solution(3, [3], [1]), 2) 
print(solution(8, [1,2,3,4,5,6,7,8], [1,2,3,4,5,6,7,8]), 8) 