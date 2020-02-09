# 레스토랑 예약 사이트

Charter 3의 레스토랑 예약 사이트에 대한 프로젝트 입니다.

### 회원가입
일단 Create와 달리 사용자가 하는 회원가입의 경우, password에 대한 Hasing이나 암호화과정이 필요하다.<br>
주로 BCrpyt를 사용하며, 단방향 암호화이므로 암호키를 얻어낼 수 없다. 
> HttpSecurity에 대한 설정 필요

### JWT
JWT(JSON WEB TOKENS) : JSON 형식을 이용하여 Web에서 이용가능한 토큰의 표준
<br><br>
- JWT의 구조(
1. Header : 타입, 알고리즘에 대한 사항
2. Payload :  실제로 어떤 데이터가 담겨있는 지. 암호화되지 않으므로 주의
3. Signature : 서명