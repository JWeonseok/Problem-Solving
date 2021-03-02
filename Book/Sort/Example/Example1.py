# 210302 화 위에서 아래로 (T 기업 코딩 테스트)

N = int(input())
list = []
for _ in range(N):
    list.append(int(input()))
list.sort(reverse=True)
for element in list:
    print(element, end=" ")