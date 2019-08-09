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

## 20190805

### 리팩토링(중복코드 제거 + DI 적용)
- [X] Restaurant Controller의 list(), detail() 중복 코드 제거
- [X] 분리 된 중복 코드의 interface
- [X] DI 적용
- [X] test code 상의 context load 문제 해결(<= SpyBean 사용)

## 20190806

### Restaurant에 메뉴 추가
- [X] Nandos restaurant test에 periperi 메뉴 추가 테스트
- [X] MenuItem domain 새로 만들기
- [X] MenuItem 한개 추가하기
- [X] MenuItem 여러개 추가하기
- [X] 각 레스토랑별로 다른 메뉴 추가해서 확인하기
- [X] list()에서도 작동하도록 기능 구현

* test case에서 나오면 안될 값을 확인하는 방법????
* getter가 없으면 spring bean이 속성값을 불러올 수 없다
* long / Long, int / Int 같은 건 구현 방식이 아예 다르다 (data type vs. Class)

## 20190807

### Application Layer 추가
- [X] detail()에서 application을 통해 restaurant 정보 받아오기
- [X] application layer 구현
- [X] application layer test 구현
- [X] DI test를 위한 생성자 추가
- [X] 위의 application에 추가로 menuitem 정보까지 받아오기
- [X] restaurant controller detail() test 통과
- [X] list()에서도 같은 방식 적용하기

### Mock을 활용한 Test Case 작성
- [X] RestaurantControllerTests mock 적용
- [X] RestaurantServiceTests mock 적용

* Mock 객체를 통해 테스트 간 의존성 감소
* 근데 테스트용 데이터가 도대체 관리가 안되고 여기저기서 똑같은 게 선언됨
* 이걸 리팩토링을 해야될까..?

### Mock Test 변형
- [X] RestaurantServiceTest에 Spring runner를 이용한 테스트 적용
=> DI 사용

### MenuItem 기능 개선
- [X] MenuItem List에 MenuItem 객체 넣는 기능 추가

### Restaurant Collection POST endpoint 구현
- [X] 접속 테스트 (status 201)
- [X] 헤더 정보 확인 (location)
- [X] 내용 정보 확인 (빈 dict)
- [X] 실제로 객체 추가할 수 있도록 수정
- [X] 인자를 받아서 처리할 수 있도록 수정

* POST 관련 도구 다시 한번 확인할 것(<- 여전히 미숙)
* 항상 돌아다니는 data type에 대해 고민할 것..

## 20180808

### RestaurantService의 addRestaurant() 구현
- [X] addRestaurant을 통해 추가 후 restaurant 반환
- [X] 실제 Repository에 추가되도록 구현

* 실제로 ID 늘어나도록 구현 시도
* 내일 싹 다 지우고 다시 재구현 시도(애 울어서 제대로 못함..)

## 20180809

### Restaurant Collection DELETE endpoint 구현
- [X] 접속 테스트 (status 204)
- [X] id 인자 받을 수 있도록 구현
- [X] service layer 구현
- [X] 실제 repository layer 구현

## 20190810

### JPA 적용해보자
- [X] 의존성 추가
- [ ] 알아서 디버깅해보자!
* Repository 구현체들은 거의 사라질 것