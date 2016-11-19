package async;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 * Created by Vitaliy Sereda on 19.11.16.
 */
@Component
public class SMSTask {
	@Async
	public Future<Boolean> send(String s, String s1, String s2) {
		System.out.println("Selecting SMS format  ");

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return new AsyncResult<>(false);
		}

		System.out.println("Async SMS send task is Complete!!!");
		return new AsyncResult<>(true);
	}

}
