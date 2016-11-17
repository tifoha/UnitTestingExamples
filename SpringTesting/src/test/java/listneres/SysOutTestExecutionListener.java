package listneres;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * Created by Vitaliy Sereda on 17.11.16.
 */
public class SysOutTestExecutionListener extends AbstractTestExecutionListener {
	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		System.out.println("SysOutTestExecutionListener.beforeTestClass");
	}

	@Override
	public void prepareTestInstance(TestContext testContext) throws Exception {
		System.out.println("SysOutTestExecutionListener.prepareTestInstance");
	}

	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
		System.out.println("SysOutTestExecutionListener.beforeTestMethod");
	}

	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {
		System.out.println("SysOutTestExecutionListener.afterTestMethod");
	}

	@Override
	public void afterTestClass(TestContext testContext) throws Exception {
		System.out.println("SysOutTestExecutionListener.afterTestClass");
	}
}
