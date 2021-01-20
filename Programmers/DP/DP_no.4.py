# 210120 수 도둑질

"""
재귀의 함정
일반적인 테케로 풀면 답은 나오지만
문제에서 리스트의 길이가 1000,000이 넘어가서
python에서 재귀로 풀어서 터져버렸다.(다른 분들은 안 그럴수도 있지만)
다른 분 풀이를 보고 효율적인 방법을 알 수 있었다
DP는 아직 갈 길이 멀다
"""

def steal(money, value, ind, s_ind, tmp):
    if ind == len(money) - 1:
        if s_ind != 0:
            tmp.append(value)
            
    elif ind == len(money) - 2:
        tmp.append(value)
        return
    
    elif ind == len(money) - 3:
        if s_ind != 0:
            tmp.append(value + money[ind + 2])
            return
    else:
        steal(money, value + money[ind + 2], ind + 2, s_ind, tmp)
        steal(money, value + money[ind + 3], ind + 3, s_ind, tmp)

def solution(money):
    temp = []
    steal(money, money[0], 0, 0, temp)
    steal(money, money[1], 1, 1, temp)
    
    return max(temp)

print(solution([1, 2, 3, 1]), 4)