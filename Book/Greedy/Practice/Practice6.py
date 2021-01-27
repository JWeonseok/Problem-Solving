# 210127 수 무지의 먹방 라이브 (2019 카카오 신입 공채)

"""
풀고 뿌-듯
첫 programmers lv.4 해결
해설과는 전체적인 알고리즘은 비슷하나
but 해설은 우선순위큐와 튜플을 사용하여 불필요한 계산 최소화 
자료구조를 적절하게 사용하는 것도 공부해야겠다.
"""
def solution(food_times, k):
    answer = 0
    
    while k >= len(food_times) - food_times.count(0):
        tmp = len(food_times) - food_times.count(0)
        if tmp == 0:
            return -1
        seconds = k // tmp
        k = k % tmp
    
        for i in range(len(food_times)):
            if food_times[i] == 0:
                continue
            if food_times[i] - seconds < 0:
                k += (seconds - food_times[i])
                food_times[i] = 0
            else:
                food_times[i] -= seconds
                
    for i in range(len(food_times)):
        if k == 0:
            if food_times[i] == 0:
                continue
            else:
                answer = i + 1
                break
        else:
            if food_times[i] == 0:
                continue
            else:
                food_times[i] -= 1
                k -= 1
        
    return answer