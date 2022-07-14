package synchronized_keyword;

public class Reentrance {

	private int counter = 0;

	public synchronized int getCounter() {
		return counter;
	}

	public synchronized void setCounter(int counter) {
		this.counter = counter;
	}

	public synchronized int IncAndGet(){
		setCounter(getCounter() + 1);
		return getCounter();
	}
}
