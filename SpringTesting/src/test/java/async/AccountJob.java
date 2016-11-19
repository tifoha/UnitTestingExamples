package async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Vitaliy Sereda on 19.11.16.
 */
@Service
public class AccountJob {
	@Autowired
	private SMSTask task;

	public void process() throws ExecutionException, InterruptedException {
		System.out.println("Going to find defaulters... ");
		Future<Boolean> asyncResult = task.send("1", "2", "3");
		System.out.println("Defaulter Job Complete. SMS will be sent to all defaulter");
//		Boolean result = asyncResult.get();
//		System.out.println("Was SMS sent? " + result);
	}
}

