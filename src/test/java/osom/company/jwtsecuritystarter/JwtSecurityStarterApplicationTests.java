package osom.company.jwtsecuritystarter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import osom.company.jwtsecuritystarter.config.SecurityConfiguration;
import osom.company.jwtsecuritystarter.config.SerenityConfiguration;

@SpringBootTest
@ContextConfiguration (classes = {SecurityConfiguration.class, SerenityConfiguration.class})
class JwtSecurityStarterApplicationTests {

	@Test
	void contextLoads() {
	}

}
