package async;

import java.util.concurrent.Executors;

import org.junit.Test;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

/**
 * Created by Vitaliy Sereda on 19.11.16.
 */
public class AsyncRestTemplateTest {
	@Test
	public void aliRestTest() throws Exception {
		AsyncRestTemplate restTemplate = new AsyncRestTemplate(new TaskExecutorAdapter(Executors.newSingleThreadExecutor()));

		String url = "http://gw.api.alibaba.com/openapi/param2/2/portals.open/api.listPromotionProduct/52605?fields=productId,productUrl&keywords=iphone";
		HttpMethod method = HttpMethod.GET;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Class<String> responseType = String.class;
		ListenableFuture<ResponseEntity<String>> result = restTemplate.exchange(url, method, null, responseType);
		ListenableFutureCallback<ResponseEntity<String>> responseEntityListenableFutureCallback = new ListenableFutureCallback<ResponseEntity<String>>() {
			@Override
			public void onFailure(Throwable ex) {
				System.out.println("faulure!!!!");
			}

			@Override
			public void onSuccess(ResponseEntity<String> result) {
				System.out.println(result.getBody());
			}
		};
		result.addCallback(responseEntityListenableFutureCallback);
		System.out.println("end.");
//		result.addCallback(responseEntityListenableFutureCallback);
//		System.out.println(result.get().getBody());
	}
}
