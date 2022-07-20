package volatile_keyword;

public class MultipleCounters {
	private int counter1 = 0;
	private int counter2 = 0;
	private volatile int counter3 = 0;

	public int getCounter1() {
		return counter1;
	}

	public void incCounter1() {
		this.counter1++;
	}

	public int getCounter2() {
		return counter2;
	}

	public void incCounter2() {
		this.counter2++;
	}

	public int getCounter3() {
		return counter3;
	}

	public void incCounter3() {
		this.counter3++;
	}
}
