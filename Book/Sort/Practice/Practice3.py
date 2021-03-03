# 210303 수 실패율 (2019 카카오 신입 공채 1차)

def solution(N, stage):
    answer = []
    temp = []
    for i in range(1, N+1):
        solve = 0; non = 0
        for s in stage:
            if i == s:
                non += 1
                solve += 1
            elif s > i:
                solve += 1
        if solve == 0:
            temp.append((i, 0))
        else:
            temp.append((i, non/solve))
    
    temp.sort(key=lambda t : t[1], reverse=True)
    for t in temp:
        answer.append(t[0])
    return answer
    
print(solution(5, [2, 1, 2, 6, 2, 4, 3, 3]), [3,4,2,1,5])
print(solution(4, [4,4,4,4,4]), [4,1,2,3])