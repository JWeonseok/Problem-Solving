# 210122 최빈수 구하기

"""
내 코드와 다른 사람 코드의 차이가 내장 함수의 유무
다른 사람 코드의 속도와 메모리가 조금 더 효율적임
음.. 앞으로 내장 함수의 활용은 고민하면서 써야겠다.
"""

T = int(input())
for _ in range(T):
    num = input()
    chk = [0] * 101
    score = list(map(int, list(input()[:-1].split(" "))))
    for s in score:
        chk[s] += 1
    while chk.count(max(chk)) > 1:
        chk[chk.index(max(chk))] = 0
    m = chk.index(max(chk))
    print(f"#{num} {m}")