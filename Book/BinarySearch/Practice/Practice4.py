# 210309 화 가사 검색 (2020 카카오 신입 공채 1차)

"""
해결 못한 이분 탐색 두 번째 문제..
해설을 보고 이전 문제처럼 이해하는 데 오래 걸리진 않았지만
풀이를 보고 감탄을 금치 못했다..
문제를 처음 접하고 이러한 풀이를 떠올리는 것은 거의 불가능할 것 같다;
이렇게 문제의 키 포인트를 캐치하는 직관은 정말 많은 노력이 필요할 듯 하다.
추가적으로 python에서 list의 곱셈으로 확장했을 때
그 내부의 list의 값을 변동할 경우 주소 값이 복사되어
다른 리스트도 동일하게 변하는 것을 알게 되었다.
(그래서 해설을 보고 구현했는 데 답이 나오질 않아서 삽질을 했다..)
ex) test = [[1]] * 6 => [[1], [1], [1], [1], [1], [1]]
    test[0][0] = 2 => [[2], [2], [2], [2], [2], [2]]
    test[0].append(2) => [[1,2], [1,2], [1,2], [1,2], [1,2], [1,2]]
"""

from bisect import bisect_left, bisect_right
    
def count_by_range(ary, left_value, right_value):
    left_ind = bisect_left(ary, left_value)
    right_ind = bisect_right(ary, right_value)
    return right_ind - left_ind

def solution(words, queries):
    answer = []

    text = [[] for _ in range(10001)]
    rev_text = [[] for _ in range(10001)]

    for w in words:
        text[len(w)].append(w)
        rev_text[len(w)].append(w[::-1])
        # print(text[len(w)])

    for i in range(10001):
        text[i].sort()
        rev_text[i].sort()
    
    for q in queries:
        if q[0] != "?":
            answer.append(count_by_range(text[len(q)], q.replace("?", "a"), q.replace("?", "z")))
        else:
            answer.append(count_by_range(rev_text[len(q)], q[::-1].replace("?", "a"), q[::-1].replace("?", "z")))

    return answer

words = ["frodo", "front", "frost", "frozen", "frame", "kakao"]
queries = ["fro??", "????o", "fr???", "fro???", "pro?"]

print(solution(words, queries), [3, 2, 4, 1, 0])