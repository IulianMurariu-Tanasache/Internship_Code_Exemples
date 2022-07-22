package fork_join;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinExample {

	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
		int result = forkJoinPool.invoke(new FibonacciRecursiveTask(10));
		System.out.println(result);
	}
}
