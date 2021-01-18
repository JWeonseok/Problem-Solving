# 210118 월 모의고사

def solution(answers):
    answer = []
    cnt = [0] * 3

    student = [[1, 2, 3, 4, 5],
    [2, 1, 2, 3, 2, 4, 2, 5],
    [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]]

    for i in range(len(student)):
        for j in range(len(answers)):
            if answers[j] == student[i][j % len(student[i])]:
                cnt[i] += 1

    for i in range(3):
        if max(cnt) == cnt[i]:
            answer.append(i+1)        
    return answer

print(solution([1, 2, 3, 4, 5]))
print(solution([1, 3, 2, 4, 2]))