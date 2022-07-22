package deadlocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockTimeoutBackoff {

	public static void main(String[] args) {
		Lock lock1 = new ReentrantLock();
		Lock lock2 = new ReentrantLock();

		Runnable runnable1 = new RunnableTimeout1(lock1, lock2);
		Runnable runnable2 = new RunnableTimeout2(lock1, lock2);

		Thread thread1 = new Thread(runnable1);
		Thread thread2 = new Thread(runnable2);

		thread1.start();
		thread2.start();
	}
}
