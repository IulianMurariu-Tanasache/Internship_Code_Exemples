package deadlocks;

public class DeadlockExampleWithSync {

	/*
		Deadlock occurs when:
		1. mutual exclusion
		2. no preemption
		3. hold and wait
		4. circular wait
	 */

	public static void main(String[] args) {
		Object lock1 = new Object();
		Object lock2 = new Object();

		RunnableSync1 runnableSync1 = new RunnableSync1(lock1, lock2);
		RunnableSync2 runnableSync2 = new RunnableSync2(lock1, lock2);

		Thread thread1 = new Thread(runnableSync1);
		Thread thread2 = new Thread(runnableSync2);

		thread1.start();
		thread2.start();
	}
}
