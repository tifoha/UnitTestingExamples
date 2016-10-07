package UnitTestingExamples.ch06;

import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Vitaly on 06.10.2016.
 */
public class AsyncServerTest {
    private Task task = mock(Task.class);
    private TaskService taskService = mock(TaskService.class);
    private Executor executor = Executors.newFixedThreadPool(1);
    private AsyncServer sut = new AsyncServer(executor, taskService);
    @Test
    public void asyncInvocationFail() throws Exception {
        sut.process(task);
        verify(taskService).handle(any());
    }

     @Test
    public void waitInLoop() throws Exception {
        sut.process(task);
         for (int i = 0; i < 20; i++) {
             try {
                 verify(taskService).handle(any());
                 return;
             } catch (AssertionError e) {
                 TimeUnit.MILLISECONDS.sleep(100);
             }
         }
         verify(taskService).handle(any());
     }

     @Test
    public void syncMock() throws Exception {
         Executor exec = mock(Executor.class);
         doAnswer(invocationOnMock -> {
             Runnable runnable = (Runnable) invocationOnMock.getArguments()[0];
             runnable.run();
             return null;
         }).when(exec).execute(any(Runnable.class));
//         sut = new AsyncServer(Runnable::run, taskService);
         sut = new AsyncServer(exec, taskService);
         sut.process(task);
         verify(taskService).handle(any());
     }




}

