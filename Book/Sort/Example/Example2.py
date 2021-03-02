# 210302 화 성적이 낮은 순서로 학생 출력하기 (D 기업 프로그래밍 콘테스트 예선)

N = int(input())
students = []
for _ in range(N):
    input_data = input().split()
    students.append((input_data[0], int(input_data[1])))

students.sort(key=lambda student : student[1])

for student in students:
    print(student[0], end=" ")