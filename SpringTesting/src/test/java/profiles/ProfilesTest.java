package profiles;

/**
 * Created by Vitaliy Sereda on 17.11.16.
 */

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = ProfilesTest.AppConfig.class)
@ActiveProfiles(profiles = "profiled")
public class ProfilesTest {
	@Autowired
	private String bean;

	@Test
	public void someTest() throws Exception {
		System.out.println("executing someTest");
		Assertions.assertThat(bean).isEqualTo("Profiled Bean.");
	}

	@Configuration
	public static class AppConfig {

		@Bean
		public String freeBean() {
			return "Free Bean.";
		}

		@Bean
		@Profile("profiled")
		@Primary
		public String profiledBean() {
			return "Profiled Bean.";
		}
	}

}
