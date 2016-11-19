package mvc;

import static org.fest.assertions.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by Vitaliy Sereda on 18.11.16.
 */
@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = MvcTest.AppConfig.class)
@WebAppConfiguration
public class MvcTest {
	@Autowired
	private LoginService loginService;
	@Autowired
	private LoginDetails loginDetails;
	@Autowired
	private MockHttpServletRequest request;
	@Autowired
	private MockHttpSession session;

	@Test
	public void requestScope() throws Exception {
		request.setAttribute("userId", "v");
		request.setAttribute("password", "v");
		assertThat(loginService.isValid());
	}

	@Test
	public void sessionTest() throws Exception {
		LocalDateTime time = LocalDateTime.of(2016, 11, 19, 11, 40);
		session.setAttribute("userId", "Vitaly");
		session.setAttribute("loggedInTime", time);

		assertThat(loginDetails.getUser()).isEqualTo("Vitaly");
		assertThat(loginDetails.getLoginTime()).isEqualTo(time);

	}

	@Configuration
	@ComponentScan ("mvc")
	@EnableWebMvc
	public static class AppConfig extends WebMvcConfigurerAdapter {
		@Override
		public void configureViewResolvers(ViewResolverRegistry registry) {
			ViewResolver resolver = new InternalResourceViewResolver("", ".jsp");
			registry.viewResolver(resolver);
		}
	}
}
