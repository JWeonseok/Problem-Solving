# 210124 일 View

"""
문제를 잘 못 이해해서 약간 버벅거린 케이스
그 외는 특이사항 없다
문제 읽는 데 좀 더 신경 써야겠다
D3이 왜 D2보다 쉽지 앞으로 정답률 좀 더 낮은 거로 풀어봐야겠다
"""

for case in range(1, 11):
    answer = 0
    num = int(input())
    buildings = list(map(int, input()[:-1].split(" ")))
    for i in range(2, len(buildings)-2):
        M = max(buildings[i-2], buildings[i-1], buildings[i+1], buildings[i+2])
        if buildings[i] - M > 0:
            answer += (buildings[i] - M)
    print(f"#{case} {answer}")