# 210127 수 문자열 재정렬

"""
쉬운 문제
"""

N = input()
digit = 0; alpha = []
for char in N:
    if char.isalpha():
        alpha.append(char)
    else:
        digit += int(char)
print("".join(sorted(alpha) + [str(digit)]))