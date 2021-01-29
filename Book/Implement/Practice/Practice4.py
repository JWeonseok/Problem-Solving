# 210129 금 자물쇠와 열쇠(2020 카카오 신입 공채)

"""
대표적인 시뮬레이션 유형의 문제라는 것을 느낌

내 알고리즘
1. key 크기를 lock과 맞춤(padding)
2. bfs의 방식으로 상,하,좌,우,회전하여 비교
-> 시간 초과

해설 알고리즘(을 참고한 알고리즘)
1. lock을 key크기 - 1 만큼 pddding
2. lock + key하여 유효성 비교
3. lock과 key가 맞지 않으면 다시 key를 뺌
4. key를 회전하고 다시 2부터 반복

기존 알고리즘에서 시간 초과의 이유를 뇌피셜로 정리해봤을 때
1. 상,하,좌,우 움직임에서 리스트의 변동을 한다.
즉, 이중 리스트의 수정 vs lock + key, -key에서 후자가 더
시간 효율이 좋다.
2. bfs로 탐색했기 떄문에 회전만 계속 도는 경우가 있을 것이다.
4번의 회전은 제자리로 오기 때문에 불필요한 연산 들어간다.

padding이 필요하다는 것은 느꼈지만 핀트가 좀 엇나갔던 것 같다.
그래도 이제 이러한 유형의 풀이를 배웠으니 다음의 비슷한 유형에서는
좀 더 금방 풀 수 있을 것 같다.
"""

def checkvalid(key, lock):
    for i in range(len(key)):
        for j in range(len(key)):
            if key[i][j] + lock[i][j] != 1:
                return False
    return True

def change(key, num):
    if num == 0:
        return roll(key)
    elif num == 1:
        return right(key)
    elif num == 2:
        return left(key)
    elif num == 3:
        return down(key)
    elif num == 4:
        return up(key)
def roll(key):
    tmp = [[0] * len(key) for _ in key]
    for i in range(len(key)):
        for j in range(len(key)):
            tmp[j][len(key)-1-i] = key[i][j]
    return tmp

def right(key):
    tmp = []
    for i in range(len(key)):
        tmp.append([0] + key[i][:-1])
    return tmp

def left(key):
    tmp = []
    for i in range(len(key)):
        tmp.append(key[i][1:] + [0])
    return tmp

def down(key):
    tmp = [[0] * len(key)]
    tmp += key[:-1]
    return tmp

def up(key):
    tmp = []
    tmp += key[1:]
    tmp.append([0] * len(key))
    return tmp

def solution(key, lock):
    answer = True
    
    chk = [[0] * len(key) for _ in lock]
    
    pad = [k + [0] * (len(lock) - len(key)) for k in key]
    for _ in range(len(lock) - len(key)):
        pad.append([0] * len(pad[0]))
    
    q = [pad]
    
    while q:
        size = len(q)
        for _ in range(size):
            cur = q.pop(0)
            if checkvalid(cur, lock):
                return True
                   
            for i in range(5):
                change_key = change(cur, i)
                if change_key == chk:
                    continue
                else:
                    q.append(change_key)
    
    return answer

def ans_solution(key, lock):
    
    length = (len(key) - 1) * 2 + len(lock)
    pad = [[0] * length for _ in range(length)]
    
    for i in range(len(lock)):
        for j in range(len(lock)):
            pad[len(key)-1+i][len(key)-1+j] = lock[i][j]
            
            
            
    for _ in range(4):
        for indy in range(len(key) + len(lock) - 1):
            for indx in range(len(key) + len(lock) - 1):
        
        
                for i in range(len(key)):
                    for j in range(len(key)):                
                        pad[indy + i][indx + j] += key[i][j]
        
                chk = False
                for i in range(len(lock)):
                    for j in range(len(lock)):
                        if pad[len(key)-1+i][len(key)-1+j] != 1:
                            chk = True
                            break
                    if chk:
                        break
                
                if not chk:
                    return True
                for i in range(len(key)):
                    for j in range(len(key)):                
                        pad[indy + i][indx + j] -= key[i][j]
                
        
        key = roll(key)
        
    return False