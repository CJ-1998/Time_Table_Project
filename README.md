# 공부 계획 작성 자동화 프로젝트

<br>

## 개발 배경
notion에 주간 계획과 일간 계획을 작성한다.
그런데 불편한 점이 생겼다. 

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

## 그림 설명
### **주간 계획표**

<br>

![image](https://file.notion.so/f/f/7d1044b7-24d0-4b32-b24a-1b9061f7bf65/8a178aa9-50c0-447b-8d2f-6d6c1cc1898d/Untitled.png?id=ae569cc6-c88b-4152-9db7-0ac0c6ade6e0&table=block&spaceId=7d1044b7-24d0-4b32-b24a-1b9061f7bf65&expirationTimestamp=1712152800000&signature=27uWqfVV8-EgE-OsHLhEPuywzHoN5y0aOMQS9UbF8Q0&downloadName=Untitled.png)

<br>

### **주간 계획표 시간 계산**

<br>

![image](https://file.notion.so/f/f/7d1044b7-24d0-4b32-b24a-1b9061f7bf65/27812d65-821a-4335-8241-bd2d3a32449c/Untitled.png?id=d92139a0-870c-485d-9cc9-9022142e3ffa&table=block&spaceId=7d1044b7-24d0-4b32-b24a-1b9061f7bf65&expirationTimestamp=1712160000000&signature=eirXRsrkMK9cxGR1hRcw-KtqSJFDeTM_MNpL_9SFeVs&downloadName=Untitled.png)

<br>

### **일간 계획표**

<br>

![image](https://file.notion.so/f/f/7d1044b7-24d0-4b32-b24a-1b9061f7bf65/52e226fd-f80a-49cb-a72e-6a7b9aff2330/Untitled.png?id=8be8a8a6-34d9-4925-824c-c0e17084a1fc&table=block&spaceId=7d1044b7-24d0-4b32-b24a-1b9061f7bf65&expirationTimestamp=1712160000000&signature=lc0pYtDRjNOguHMtyLYiSE-bslfrJnwCby1MFMpmG8U&downloadName=Untitled.png)

<br>

## 기능 요구 사항
1. 주간 계획 템플릿 불러오기 기능
2. 주간 계획 시간 계산 기능
3. 일간 계획 주간 계획 통해 자동 작성 기능
4. 일간 계획 O 체크 시 수행한 것 계획과 똑같이 자동 작성 기능
5. 일간 계획 X 체크 시 수행한 것 스스로 작성 기능
6. 일간 계획 성공률 계산 기능
7. 주간 계획 성공률 계산 기능
