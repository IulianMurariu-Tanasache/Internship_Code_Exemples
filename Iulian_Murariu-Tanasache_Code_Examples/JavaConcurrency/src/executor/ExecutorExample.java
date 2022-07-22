package executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorExample {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(3);

		Callable<Integer> runnableWithOutput = () -> {
			System.out.println("Callable that returns the value 24");
			return 24;
		};

		executorService.execute(newRunnable("Runnable1"));
		executorService.submit(newRunnable("Runnable2"));
		Future<Integer> res = executorService.submit(runnableWithOutput);
		System.out.println(res.isDone());

		//executorService.shutdown();
		try {
			executorService.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		try {
			System.out.println(res.get());
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	public static Runnable newRunnable(String msg) {
		return () -> {
			System.out.println(msg);
		};
	}
}
