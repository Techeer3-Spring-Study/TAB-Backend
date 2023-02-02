package com.techeeresc.tab;

import org.junit.jupiter.api.Test;

@SpringBootTest
// 어노테이션은 스프링을 실제 띄워서 컨테이너를 띄우거나 DI를 하여 스프링과 동일한 환경에서 테스트할 수 있고,
// 단순 자바 코드로서 테스트가 아닌 DB연동 등 통합 테스트에 자주 사용한다.
// 하지만 김영한님의 말을 따르면
// 테스트 코드에서 해당 어노테이션이 붙으면 테스트의 설계가 잘못될 가능성이 높다고 했다. (통합 테스트가 아닌 이상.)
// https://compogetters.tistory.com/139
class TabApplicationTests {

  @Test
  void contextLoads() {}
}
