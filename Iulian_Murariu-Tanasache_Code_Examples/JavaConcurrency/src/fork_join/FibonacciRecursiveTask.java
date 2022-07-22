package fork_join;

import java.util.concurrent.RecursiveTask;

public class FibonacciRecursiveTask extends RecursiveTask<Integer> {

	final int n;

	public FibonacciRecursiveTask(int n) {
		this.n = n;
	}

	@Override
	protected Integer compute() {
		if(n <= 1)
			return n;
		System.out.println(Thread.currentThread().getName() + " -> " + n);
		FibonacciRecursiveTask f1 = new FibonacciRecursiveTask(n - 1);
		f1.fork();
		FibonacciRecursiveTask f2 = new FibonacciRecursiveTask(n - 2);
		f2.fork();
		System.out.println(n + " -> waiting to join");
		return f1.join() + f2.join();
	}
}
