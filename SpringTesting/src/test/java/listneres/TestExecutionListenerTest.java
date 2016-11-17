package listneres;

/**
 * Created by Vitaliy Sereda on 17.11.16.
 */

import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = TestExecutionListenerTest.AppConfig.class)
@TestExecutionListeners (value = {SysOutTestExecutionListener.class}, mergeMode = MERGE_WITH_DEFAULTS)
public class TestExecutionListenerTest {
	@Test
	public void someTest() throws Exception {
		System.out.println("executing someTest");
	}

	@Test
	public void someOtherTest() throws Exception {
		System.out.println("executing someOtherTest");
	}

	@Configuration
	public static class AppConfig {

	}

}
