package async;

import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Vitaliy Sereda on 19.11.16.
 */
@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AsyncTest.AppConfig.class)
public class AsyncTest {
	@Autowired
	private AccountJob job;
	@Test
	public void jobTest() throws Exception {
		System.out.println("Starting job...");
		job.process();
		System.out.println("Job done.");
//		TimeUnit.SECONDS.sleep(3);
	}

	@Configuration
	@ComponentScan("async")
	@EnableAsync
//	@EnableScheduling
	public static class AppConfig  {

//		@Override
//		public Executor getAsyncExecutor() {
//			return threadPoolTaskExecutor();
//		}
//
		@Bean(destroyMethod = "shutdown")
		public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
			ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
			exec.setCorePoolSize(7);
			exec.setMaxPoolSize(14);
			exec.setQueueCapacity(64);
			exec.setWaitForTasksToCompleteOnShutdown(true);
//			exec.setAwaitTerminationSeconds(3);
			exec.initialize();
			return exec;
		}
//
//		@Override
//		public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//			return new SimpleAsyncUncaughtExceptionHandler();
//		}

		public static void main(String[] args) throws ExecutionException, InterruptedException {
			ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
			ctx.getBean(AccountJob.class).process();
		}
	}
}
