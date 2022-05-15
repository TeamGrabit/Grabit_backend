package grabit.grabit_backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class GrabitBackendApplicationTests {

	@Value("${aabbcc}")
	private String aabbcc;

	@Value("${abc}")
	private String abc;

	@Test
	void contextLoads() {
		System.out.println(aabbcc);
		System.out.println(abc);
	}

}
