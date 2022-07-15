package deadlocks;

import static java.lang.Thread.sleep;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class RunnableTimeout2 implements Runnable {

	private Lock lock1;
	private Lock lock2;
	public RunnableTimeout2(Lock lock1, Lock lock2) {
		this.lock1 = lock1;
		this.lock2 = lock2;
	}

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();

		while(true) {
			int failureCount = 0;
			while(! tryLockBothLocks()) {
				failureCount++;
				System.out.println(threadName + "Failed to lock both locks -> this is the " + failureCount + " attempt");
				try {
					sleep((long)(100.0 * Math.random()));
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			System.out.println(threadName + "Successfully locked the locks after " + failureCount + " attempts");
			lock1.unlock();
			lock2.unlock();
		}
	}

	private boolean tryLockBothLocks() {
		String threadName = Thread.currentThread().getName();

		// try to lock lock2
		try{
			boolean lock2Locked = lock2.tryLock(1, TimeUnit.SECONDS);
			if(!lock2Locked)
				return false;
		} catch (InterruptedException e) {
			//throw new RuntimeException(e);
			System.out.println(threadName + " intrerrupted trying to lock lock 2");
		}

		// if successful, try lock lock1
		try{
			boolean lock1Locked = lock1.tryLock(1, TimeUnit.SECONDS);
			if(!lock1Locked) {
				lock2.unlock();
				return false;
			}
		} catch (InterruptedException e) {
			//throw new RuntimeException(e);
			System.out.println(threadName + " intrerrupted trying to lock lock 1");
		}

		return true;
	}
}
