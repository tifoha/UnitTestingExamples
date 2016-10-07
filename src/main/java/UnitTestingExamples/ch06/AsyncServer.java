package UnitTestingExamples.ch06;

import java.util.concurrent.Executor;

/**
 * Created by Vitaly on 06.10.2016.
 */
public class AsyncServer {
    private final Executor executor;
    private final TaskService taskService;

    public AsyncServer(Executor executor, TaskService taskService) {
        this.executor = executor;
        this.taskService = taskService;
    }

    public void process(Task task) {
        executor.execute(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
                taskService.handle(task);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        });

    }
}

interface Task {
    void foo();
}
class TaskService {

    public void handle(Task task) {

    }
}
