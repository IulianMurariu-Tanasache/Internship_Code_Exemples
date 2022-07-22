package lock;

import java.util.concurrent.locks.ReentrantLock;

enum Calculation {
	ADD,
	SUBTRACT
}

public class ReentrantLockCalculator {

	private ReentrantLock reentrantLock = new ReentrantLock();
	private double result = 0.0;

	private void add(double val) {
		try {
			reentrantLock.lock();
			result += val;
			System.out.println("added");
		} finally {
			// unlock in finally for safety
			reentrantLock.unlock();
		}
	}

	private void subtract(double val) {
		try {
			reentrantLock.lock();
			result -= val;
			System.out.println("subtracted");
		} finally {
			// unlock in finally for safety
			reentrantLock.unlock();
		}
	}

	public void calculate (double val, Calculation... calculations) {
		try {
			reentrantLock.lock();
			for (Calculation calculation : calculations) {
				switch (calculation) {
					case ADD:
						add(val);
						break;
					case SUBTRACT:
						subtract(val);
						break;
				}
			}
		}
		finally {
			reentrantLock.unlock();
		}
	}

	public double getResult() {
		double res;
		reentrantLock.lock();
		res = result;
		reentrantLock.unlock();
		return res;
	}
}
