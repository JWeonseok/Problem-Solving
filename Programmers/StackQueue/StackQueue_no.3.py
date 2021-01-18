# 210118 월 다리를 지나는 트럭

def solution(bridge_length, weight, truck_weights):
    answer = 0
    
    onBridge = []
    endBridge = []
    chk = []
    limit = len(truck_weights)
    while(len(endBridge) < limit):
        answer+=1
        for i in range(len(chk)):
            chk[i]+=1
        if bridge_length in chk:
            chk.pop(0)
            endBridge.append(onBridge.pop(0))
        if len(truck_weights) != 0:
            if sum(onBridge)+truck_weights[0] <= weight:
                onBridge.append(truck_weights.pop(0))
                chk.append(0)    
    return answer

print(solution(2,10,[7,4,5,6]))
print(solution(100,100,[10,10,10,10,10,10,10,10,10,10]))