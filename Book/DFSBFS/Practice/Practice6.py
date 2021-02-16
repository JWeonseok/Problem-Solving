# 감시 피하기

import sys
from itertools import combinations

N = int(sys.stdin.readline().rstrip())
grid = [list(sys.stdin.readline().rstrip().split()) for _ in range(N)]

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

temp = []

for i in range(N):
    for j in range(N):
        if grid[i][j] == "X":
            temp.append([i, j])
            
obstacles = list(combinations(temp, 3))

def check(x, y):    
    for m in range(1, N):
        for i in range(4):
            m_x = x + m*dx[i]
            m_y = y + m*dx[i]
            if not chkvalid(m_x, m_y):
                continue
            if grid[m_x][m_y] == "S":
                return False
    return True
            
            
def chkvalid(i, j):
    if i < 0 or i >= N or j < 0 or j >= N:
        return False
    return True

chk2 = False
for obs in obstacles:
    if chk2:
        break
    for elements in obs:
        grid[elements[0]][elements[1]] = "O"
    
    chk = False
    for i in range(N):
        if chk:
            break
        for j in range(N):
            if grid[i][j] != "T":
                continue
            if not check(i, j):
                chk = True
                break
                
    if not chk:
        chk2 = True
        print("YES")
        break
        
    for elements in obs:
        grid[elements[0]][elements[1]] = "X"

if not chk2:
    print("NO")
