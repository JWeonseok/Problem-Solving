# 210123 토 패턴 마디의 길이

"""
기본적인 문자열 문제
따로 자료형을 쓸 필요도 없고 제약도 있어서 쉬웠음
나보다 빠른 사람 코드 봤을 때 내가 더 효율이 좋아 보이는데
왜 내가 더 느린지 모르겟넴
"""

T = int(input())
for i in range(T):
    case = input()
    for num in range(1, 11):
        if case[:num] == case[num: 2 * num]:
            print(f"#{i+1} {num}")
            break