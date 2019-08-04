# - SelfEatGo -

* 아샬님의 패캠 강의 기준 예시로 작성
* 손꾸락 연습을 위해 안보고 따라해보는 것이 주목적
* 매 push 마다 테스트 케이스 반드시 같이 업데이트 할 것 

# - Test 명세서 -
## 20190801
### Restaurant Domain 작성
- [X] Restaurant 객체 생성 테스트
- [X] name 속성 테스트
- [X] address 속성 테스트
- [X] 속성 결합 테스트

## 20190802
### Restaurant Domain 테스트 추가
- [X] ID 속성 테스트

### Test 설정 추가
- [X] Spring MVC Test 설정

### Restaurant Collection GET endpoint 작성
- [X] Interfaces 패키지 작성
- [X] Restaurant Domain Controller 작성
- [X] Restaurant list 함수 테스트
- [X] 접속 테스트 (status 200)
- [X] list 함수 내 restaurant id 속성 테스트
- [X] list 함수 내 restaurant name 속성 테스트
- [X] list 함수 내 restaurant address 속성 테스트

## 20190803
- 게으름 + 육아..

## 20190804

### Restaurant Member GET endpoint 작성
- [X] endpoint 접속 확인
- [X] 각 속성(ID, address, name) 확인
- [X] endpoint URI 내 ID 변수화(요청값에 따라 내용 바뀌도록)
- [X] Restaurant 객체 2개 이상으로 테스트
- [X] 여러 객체 중 하나만 가져올 수 있는 방법 모색 (=> Java Stream으로 해결)
* !! Java Stream API 사용법 숙지할 것 !! (https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html)

