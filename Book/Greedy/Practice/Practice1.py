# 210125 월 모험가 길드

T = int(input())
players = list(map(int, input().split(" ")))
players.sort()
answer = 0
guild = []
for player in players:
    guild.append(player)
    if guild[-1] <= len(guild):
        answer += 1
        guild = []
print(answer)