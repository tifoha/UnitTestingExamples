package environment;

import static org.fest.assertions.Assertions.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.mock.env.MockPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Vitaliy Sereda on 18.11.16.
 */
@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = EnvironmentTest.AppConfig.class)
@TestPropertySource ("classpath:test.properties")
public class EnvironmentTest {
	@Autowired
	private String bean;

	@Resource
	private Environment env;

	@Test
	public void someTest() throws Exception {
		assertThat(bean).isEqualTo("Test bean value");
	}

	@Test
	public void envTestProperty() throws Exception {
		assertThat(env.getProperty("beanValue2"))
				.isEqualTo("Test bean value2");

	}

	@Configuration
	@PropertySource ("classpath:app.properties")
	public static class AppConfig {
		@Bean
		public String freeBean(@Value ("${beanValue}") String beanValue) {
			return beanValue;
		}
	}

	public static class MockPropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {
			MutablePropertySources propertySources = applicationContext
					.getEnvironment()
					.getPropertySources();

			MockPropertySource mockEnvVars = new MockPropertySource()
					.withProperty("beanValue", "Test bean value");

			propertySources.replace(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, mockEnvVars);
		}
	}
}
