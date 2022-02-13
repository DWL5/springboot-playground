# oauth

### oauth의 단계

1. Authorization Server에 사용할 것이라 요청을 한다.
2. Authorization Server에서 ClientId와 Client Secret값을 발급해준다.
3. 내 서비스는 이 2개를 저장한다. (Client Secret 값은 절대 노출하면 안 된다.)
4. 사용자가 내 서비스를 이용하려 하기 위해 로그인을 하면 Authorization Server에 접근이 된다.
5. Authorization Server는 사용자에게 내 서비스를 이용할 것인가 물어본다.
6. 사용자가 동의하면 Authorization Server는 내 서비스에 정보(CODE)를 준다.
7. 내 서비스는 그 코드값과 보관 중인 ID와 Secert을 담아 Authorization Server에 다시 보낸다.
8. 그 후 Authorization Server는 이를 비교해 검증한다.
9. 검증 결과 모두 유효하다면 내 서비스에게 AccessToken을 발급해준다. 내 서비스는 이제 AccessToken을 사용해 Resource Server에 접근 가능 할 수 있다.
