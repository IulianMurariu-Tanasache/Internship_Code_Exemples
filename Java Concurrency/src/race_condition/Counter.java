package race_condition;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

	private int counter = 0;
	// one solution
	private AtomicInteger atomicCounter = new AtomicInteger(0);

	public int IncAndGet() {
		// another solution
		// synchronized (this){
		return ++counter;
	}

	public int AtomicIncAndGet() {
		return atomicCounter.incrementAndGet();
	}

	public int getCounter() {
		return counter;
	}

	public int atomicGet() {
		return atomicCounter.get();
	}

}
