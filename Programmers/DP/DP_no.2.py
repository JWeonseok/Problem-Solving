# 210119 화 정수 삼각형

"""
이전 문제에 비해서 쉬운 편
딱히 고민하거나 아이디어 없음
다만 다른 사람 풀이 중 lambda식으로 한 줄로 풀어낸 풀이가 충격
"""

def solution(triangle):
    
    for i in range(len(triangle) - 1):
        triangle[i + 1][0] += triangle[i][0]
        triangle[i + 1][len(triangle[i + 1]) - 1] += triangle[i][len(triangle[i]) - 1]
        for j in range(len(triangle[i]) - 1):
            triangle[i + 1][j + 1] += max(triangle[i][j], triangle[i][j + 1])
            
    return max(triangle[-1])

print(solution([[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]])) # 30