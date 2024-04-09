# 📝 공부 계획 작성 자동화 프로젝트

<br>

## 🔎 개발 배경
notion에 주간 계획과 일간 계획을 작성한다.

그런데 불편한 점이 생겼다. 

<br>

- 주간 계획에 매번 비슷한 부분이 존재
  - 매번 이전 주간 계획에서 복사해 와야 함
  - 일간 계획도 매번 주간 계획에서 복사해 와야 함

- 주간 계획의 시간 % 계산
  - 수작업으로 계산하려니 시간 오래 걸림

- 일간 계획 성공 여부 체크
  - 수작업으로 O, X 하려니 귀찮음
  - 성공률 계산도 직접 해야 함

<br>

이렇듯 계획을 작성하는데 반복적인 일이 많고 시간이 많이 걸린다. 

이런 부분들을 자동화 하기 위해 사이드 프로젝트를 진행한다. 

<br>

## 🌌 화면 설명
1. **메인 화면** : 주간 계획표 들어갈 수 있는 링크 있는 화면
2. **전체 주간 계획표 화면** : 월, 주차별로 주간 계획표 보여주는 화면
3. **주간 계획표 화면** : 주간 계획표, 시간으로 이동하는 링크, 일간 계획표 7개 있는 화면 
4. **시간 계산 화면** : 항목별 시간 계산된 표 보여주는 화면
5. **일간 계획표 화면** : 계획과 실행한 것, 체크 있는 화면

<br>

### **전체 주간 계획표 화면**

<p align="center">
<img src="https://file.notion.so/f/f/7d1044b7-24d0-4b32-b24a-1b9061f7bf65/9d9a35f4-c339-4da3-bfc8-b7e40bba35e6/Untitled.png?id=95a1bf69-fe53-4c54-8af4-6a17ce2eb25c&table=block&spaceId=7d1044b7-24d0-4b32-b24a-1b9061f7bf65&expirationTimestamp=1712721600000&signature=XKyZa3wBqDX2h9SLXQFUOhPtPbijhHeFdgHvcb9-xwc&downloadName=Untitled.png" width="200" height="200"/>
</p>

<br>

### **주간 계획표 화면**

<br>

<p align="center">
<img src="https://file.notion.so/f/f/7d1044b7-24d0-4b32-b24a-1b9061f7bf65/0b8f75c0-cdf2-410d-a4f1-ec86baf16ce5/Untitled.png?id=58ffc71d-9032-41e5-941f-a108cbe19d1a&table=block&spaceId=7d1044b7-24d0-4b32-b24a-1b9061f7bf65&expirationTimestamp=1712721600000&signature=LiENK29Ju9pEBT0ASaD_sACxu8TUr-ujorWevdWPJxk&downloadName=Untitled.png" width="250" height="250"/>
</p>

<br>

### **시간 계산 화면**

<br>

<p align="center">
<img src="https://file.notion.so/f/f/7d1044b7-24d0-4b32-b24a-1b9061f7bf65/27812d65-821a-4335-8241-bd2d3a32449c/Untitled.png?id=ff6136c0-757a-47b4-aa2e-2de71537896b&table=block&spaceId=7d1044b7-24d0-4b32-b24a-1b9061f7bf65&expirationTimestamp=1712721600000&signature=P_8-sBejEfdJY3CAXXqkn4hnK2Q5GfHL7bkLxX2HKu0&downloadName=Untitled.png" width="250" height="250"/>
</p>

<br>

### **일간 계획표 화면**

<br>

<p align="center">
<img src="https://file.notion.so/f/f/7d1044b7-24d0-4b32-b24a-1b9061f7bf65/67c01bce-e66f-42e7-aa78-fca853ef3745/Untitled.png?id=8b80894a-28f5-41e2-b61d-a9f4a06d1f29&table=block&spaceId=7d1044b7-24d0-4b32-b24a-1b9061f7bf65&expirationTimestamp=1712721600000&signature=87OlkcQYmYN_1ONThk9R_thFTNSVNdhTYLIwCYq4I3A&downloadName=Untitled.png" width="250" height="300"/>
</p>

<br>

## ⚙ 기능 요구 사항
### 전체 주간 계획표 화면 관련

1. n월 만드는 기능 (n월의 주간 계획표 추가할 수 있는 탭 만드는 기능)  
2. n월 n주차 주간 계획표 만드는 기능 (n주차 선택해서 n주차 주간 계획표 탭 만드는 기능)

<br>

### 주간 계획표 화면 관련

1. 일간 계획표 리스트 반환해 보여주는 기능
2. 새로 만든 주간 계획표라면 빈 주간 계획표 보여주는 기능
3. 템플릿 불러오기 버튼 누르면 템플릿 불러오는 기능
4. 주간 계획표에 계획 작성 후 저장 버튼 누르면 저장하는 기능
5. 시간 계산 화면으로 이동하는 기능
6. 큰 태그 추가하는 기능 (공부, 휴식, 기타…)
7. 태그에 세부 항목 추가 하는 기능 (공부 태그: 스터디, 알고리즘…)

<br>

### 시간 계한 화면 관련

1. 주간 계획표의 시간들 가져와 계산하는 기능 (초기 기능 - 그냥 항목별로 전부 계산)
2. 주간 계획표의 시간들 태그 별로 계산하는 기능 (향상 기능 - 태그별로 계산)
3. 주간 총합 시간 계산하는 기능
4. 주간 총합 시간 비율 계산하는 기능
5. 태그 별로 총 시간, % 보여주는 기능

<br>

### 일간 계획표 화면 관련

1. 주간 계획표의 계획 가져와 일간 계획 탭에 보여주는 기능
2. 일간 실행 탭에 같은 것 작성 시 체크 O 되는 기능
3. 일간 실행 탭에 다른 것 작성 시 체크 X 되는 기능
4. 체크 O 누르면 일간 실행 탭에 일간 계획 탭의 것 작성해주는 기능
