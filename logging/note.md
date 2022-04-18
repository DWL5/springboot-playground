### SLF4J
- Simple Logging Facade for Java
- 로깅에 대한 Facade 패턴
- SLF4J 자체가 로깅 프레임웍이 아니라 다양한 로깅 프레임웍을 같은 API를 사용해서 접근 할 수 있도록 해주는 추상화 계층
- SLF4J를 구현한 자바 로깅 프레임워크 로는 Log4J, Logback, Log4J2가 있다.

### MDC
- 멀티 클라이언트 환경에서 다른 클라이언트와 값을 구별하여 로그를 추적할수 있도록 제공되는 map이다.
- ThradLocal에 구별할 수 있는 키 값을 저장하여 Thread가 존재하는 동안 계속해서 사용할 수 있도록 하는 방법이다.
- log4j 및 logback만 MDC기능을 제공하고 있다.
