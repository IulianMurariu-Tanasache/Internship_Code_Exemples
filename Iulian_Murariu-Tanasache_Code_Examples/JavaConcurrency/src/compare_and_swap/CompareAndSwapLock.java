package compare_and_swap;

import java.util.concurrent.atomic.AtomicBoolean;

public class CompareAndSwapLock{

	private AtomicBoolean atomicLock = new AtomicBoolean(false);

	public void unlock() {this.atomicLock.set(false);}

	public void lock() {
		while(!this.atomicLock.compareAndSet(false, true)){
			// busy waiting for the lock to be unlocked
		}
	}
}
