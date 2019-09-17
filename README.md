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
- [X] 알아서 디버깅해보자!
* Repository 구현체들은 거의 사라질 것
* 되긴 하는데 리스트 저장이 안됨..
* 개판으로 꼬임
* JPA 모르고 그냥 맘대로 했더니 Crud 쪽에서 충돌이 너무 많이 남
* 실제 프로젝트 때는 어떻게 할까??

### 실습 종료
* JPA 적용하는 부분에 미숙한 점이 굉장히 많음
* 특히 CRUD 확장할 때...
* 새로 혼자 처음부터 만들어 볼 것    

## 20190824

### 강의 재시작
- 최신 강의 내용 따라해볼 것
- 프론트엔드는 일단 여기선 webpack으로 대충 하고 넘어간다

## 20190826
### Restaurant Domain Update
- [x] PATCH /restaurants/{id} endpoint status ok
- [x] PATCH /restaurants/{id} endpoint 속성값 잘 받는지 확인
- [x] PATCH /restaurants/{id} endpoint restaurantService 로 책임 이관
- [x] restaurantService 에서 적용 대상 잘 찾아오는지 확인
- [x] restaurantService 에서 찾아온 대상의 정보가 알맞게 바뀌는지 확인

* update 에서는 명시적인 save() 처리가 아닌 @Transactional 활용
* setter 를 여러개 부르기 보다는 한번에 처리할 수 있는 메서드를 추가로 생성
  (이유 확인해볼 것) 

### Lombok 적용
- [x] Restaurant domain getter / setter 적용 <- setter는 필요 없어서 삭제
- [x] Restaurant domain constructor 적용
- [x] Restaurant domain builder 패턴 적용
- [x] ItemMenu domain builder 패턴 적용

* Restaurant Domain 의 itemMenus column setter 변경
* Array(또는 객체 타입)의 setter 를 사용할 때는 참조 타입에 꼭 유의
* 안 지키고 하면 함수 실행 뒤의 결과값이 뒤죽박죽이 될 수 있음
* 결론 => 오브젝트 타입의 setter 는 복사본을 사용하자!
* Lombok 사용 시 불필요한 getter/setter 나 기타 코드가 생성되지 않도록 주의

### Validation
* given() + will() + lambda 를 통해 실제 데이터 test 가능
* Restaurant Controller create() test case에 사례 추가

- [x] Restaurant Controller Create Test case 작성
- [x] Valid, Invalid 두가지 케이스로 작성
- [x] Restaurant Controller Update Test case 작성

* @Valid annotation 은 적용 대상 resource 앞에 붙여야 한다.  

## 20190827 ~ 20190828
### Error 처리
- [X] Restaurant Controller Detail case 나누기 (성공/실패)
- [X] Restaurant Controller Detail Notfound test case 작성
- [X] not found class 생성
- [X] error advice controller advice 생성
- [X] 빈 json을 돌려주는 test case 작성
- [X] 실제 service layer 에서 동작하도록 수정
- [X] restaurant service test 수정 

* 키워드 별로 못따라간 부분이 너무 많음
* 지우고 다시 혼자 재구현할 것 (20190827 1차 재구현 완료)

### ItemMenu Domain CRUD
- [X] bulk update 용 json 파일 생성
- [x] ItemMenu Controller 구현
- [x] bulkUpdate test case 작성
- [x] ItemMenu Service 구현
- [x] ItemMenu Service test case 작성

* @JsonInclude Annotation 새로 사용
* JsonInclude.Include.Not_Null 인자로 없는 놈 안 보이게 할 수 있음
* @Transient 와 함께 사용법 제대로 숙지해둘 것

### ItemMenu Domain CRUD 2
- [x] ItemMenu 수정 기능 구현 (기존 기능 활용)
- [x] ItemMenu 삭제 기능 구현
- [X] ItemMenu 없는 대상 삭제 예외 처리 구현

* 현재 API 구조에서 ItemMenu 에 id를 주면 수정도 가능하다 (PATCH 를 쓰니까)
* 편하긴 한데 언제 POST 없이 PATCH 로만 구현해도 될 지 고민해보자
* ItemMenu 없는 대상 구현 중 이상한 현상 발견(Error Handler 쪽 주석 참고)

## 20190829

### Review Domain CRUD
- [X] Review Controller create test case 작성
- [X] Review Controller create 구현
- [x] Review Service 뼈대 구현
- [x] Review Domain 뼈대 구현 + Entity 설정

## 20190901

### Review Domain CRUD2
- [X] Review Service addReview 테스트 및 구현
- [X] Review Repository 테스트 및 구현
- [X] Review Controller Validation 테스트 및 구현

## 20190902 
### Restaurant Domain Review 조회 기능 추가
- [X] Controller 테스트 수정
- [X] Service 테스트 수정
- [X] Review Service 에서 restaurantId 설정하도록 수정

* ItemMenu Service 에외처리 이상함 수정할 것

## 20190903
### 프로젝트 분리(Admin API 정리)
- [X] Customer, Admin, Common 프로젝트 분리 작업 진행
- [X] Admin RestaurantController detail() review, itemMenu 조회 기능 제거
- [X] Admin RestaurantService detail() review, itemMenu 조회 기능 제거
- [X] Admin ItemMenuController list() 구현 (restaurant 에 종속)
- [X] Admin ReviewController 전체 기능 제거 후 list() 구현 (restaurant 에 비종속)
- [X] Customer API 불필요한 기능 제거

## 20190904
### Gradle 설정 변경
- [X] Common 모듈 jar 설정 추가
- [X] Gradle을 통해 서버 실행, 테스트 변경

* Gradle을 활용하면 멀티프로젝트 관리가 용이함

### 레알 영속화
- [X] application.yml 파일 추가
- [X] 테스트를 위한 환경변수 profile 생성

## 20190905
### 가게 필터링 기능 구현 
- [X] Admin Region Domain 구현
- [X] Admin Region Controller GET, POST 구현
- [X] Admin Region Service list(), add() 구현
- [X] Common Region Repository Interface 구현
- [X] Customer Region Controller GET 구현
- [X] Customer Region Service list() 구현

## 20190906
### 가게 필터링 기능 구현2 (Category 구현)
- [X] Admin Category Domain 구현
- [X] Admin Category Controller GET, POST 구현
- [X] Admin Category Service list(), add() 구현
- [X] Common Category Repository Interface 구현
- [X] Customer Category Controller GET 구현
- [X] Customer Category Service list() 구현

### 가게 필터링 기능 구현3 (region 필터링)
- [X] Customer Restaurant Controller URL에 regions 추가
- [X] Customer Restaurant Controller 변경
- [X] Customer Restaurant Service 변경

## 20190907
### 가게 필터링 기능 구현4 (category 필터링)
- [X] Restaurant Domain CategoryId 추가
- [X] Customer Restaurant Controller URL에 category 추가
- [X] Customer Restaurant Controller 변경
- [X] Customer Restaurant Service 변경

* Restaurant 도메인에 categoryId 처음 추가 시 @NotNull 넣고 돌리면 에러 발생
* auto-ddl 설정 하면서 null을 기본으로 넣는데 설정에 위배되서 그런 것 같음

## 20190908
### 유저 관리 기능 구현
- [X] User Domain 구현 (email, name, level)
- [X] User Repository Interface 구현
- [X] Admin User Controller, Service API GET 구현
- [X] Admin User Controller, Service API POST 구현

## 20190909 이어서 구현
- [X] Admin User Controller, Service API PATCH 구현
- [X] Admin User Controller, Service API DELETE 구현
- [ ] Admin User Service 예외 처리

### 가게 필터링 기능 개선
- [ ] Restaurant Domain 필터링이 category, region에 유연하게 작동하도록 변경

* JPQL 외에는 답이 없는지 고민 필요

### 회원 가입 기능 구현
- [X] Spring Security 의존성 추가 및 설정 (필요 없는 보안 설정 제거)
- [X] Security 기본 페이지 삭제
- [X] User Domain 수정 (password 추가)
- [X] Customer User Controller, Service API POST 구현 

* 유저 삭제 기능은 실제 삭제가 아닌 레벨 조정을 통해 구현
* Transactional 사용에 유의할 것
* 덜렁 거리지 말고 꼼꼼하게 확인하면서 개발 할 것
* Repository를 MockBean 처리해서 디버깅 한참 걸림 ㅡㅡ (null pointer 발생)

## 20190910
### 회원 가입 기능 구현
- [X] 패스워드 암호화 (Bcrypt)
- [X] email 중복 막기 (중복 시 exception 발생)

## 20190911
### 인증 기능 구현
- [X] /session POST API 구현
- [X] POST return content에 accessToken 부여
- [X] SessionDto 구현 (lombok data 활용, Application 계층에 생)
- [X] UserService에서 인증 처리하도록 구현

## 20190916
### Restaurnat Filter 기능 개선 버전 업데이트 (연기)
- [ ] RestaurantSpecification 구현
- [ ] RestaurantSpecificationBuilder 구현
- [ ] SearchCriteria 구현
- [ ] 기존 Restaurant Controller, Service 적용

## 20190917
### 인증 기능 구현
- [X] Session Request, Respons Dto 분리 (다시 interfaces 패키지로 이동)
- [X] Session Controller 예외 처리 (패스워드 틀린 경우, 계정 없는 경우)
- [X] User Service authenticate 구현
- [X] User Service test 작성 (올바른 케이스, 없는 이메일, 패스워드 틀림)
- [X] Password Encoder 의존성 주입 형태로 변경
- [X] 기존 테스트 코드 변경