import java.util.concurrent.Executor;

public class HomeMadeExecutors {
}

/**
 * 1) Are these executors correct?
 * 2) What is the main problem of this implementation?
 */
class DirectExecutor implements Executor {
    public void execute(Runnable runnable) {
        runnable.run();
    }
}

class ThreadPerTaskExecutor implements Executor {
    public void execute(Runnable runnable) {
        new Thread(runnable).start();
    }
}