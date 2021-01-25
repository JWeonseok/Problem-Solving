# 210125 월 곱하기 혹은 더하기

numbers = input()
answer = 0
for num in numbers:
    if answer == 0:
        answer += int(num)
    else:
        answer *= int(num)
print(answer)