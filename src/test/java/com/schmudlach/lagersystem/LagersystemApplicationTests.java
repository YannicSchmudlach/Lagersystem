package com.schmudlach.lagersystem;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LagersystemApplicationTests {

	@Test
	void alive(){
		assertThat(true).isTrue();
	}

}
