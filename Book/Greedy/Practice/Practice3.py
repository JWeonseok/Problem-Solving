# 210125 월 문자열 뒤집기

T = input()
s = T[0]
zero = 0
one = 0
for i in range(1, len(T)):
    if s != T[i]:
        if s == "0":
            zero += 1
        else:
            one += 1
        s = T[i]
print(min(zero, one))