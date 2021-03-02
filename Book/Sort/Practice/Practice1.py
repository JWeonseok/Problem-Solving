# 210302 국영수

N = int(input())
students = [(input().split()) for _ in range(N)]

students.sort(key = lambda student : (-int(student[1]), int(student[2]), 
-int(student[3]), student[0]))

for student in students:
    print(student[0])
