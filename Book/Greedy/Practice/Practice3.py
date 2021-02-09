# 210125 월 문자열 뒤집기

text = list(input())
zero = 0; one = 0
cur = ""
for char in text:
    if char != cur:
        cur = char
        if char == "0":
            zero += 1
        else:
            one += 1
print(min(zero, one))