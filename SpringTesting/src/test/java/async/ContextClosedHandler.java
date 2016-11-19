package async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by Vitaliy Sereda on 19.11.16.
 */
//@Component
public class ContextClosedHandler implements ApplicationListener<ContextClosedEvent> {
	@Autowired
	private ThreadPoolTaskExecutor executor;
//	private ApplicationContext ctx;

	public void onApplicationEvent(ContextClosedEvent event) {
//		executor.setAwaitTerminationSeconds();
		System.out.println("exit.");
	}
}