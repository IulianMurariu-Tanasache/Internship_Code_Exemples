package synchronized_keyword;

public class VisibilityAndHappensBeforeGuarantee {

	public static void main(String[] args) {
		MultipleCounters multipleCounters = new MultipleCounters();

		Thread thread1 = new Thread(() -> {
			for(int i = 0; i < 1_000; ++i) {
				multipleCounters.incCounter1();
				multipleCounters.incCounter2();
				multipleCounters.incCounter3();
			}
		});

		Thread thread2 = new Thread(() -> {
			for(int i = 0; i < 1_000; ++i) {
				System.out.println("Counter 3: " + multipleCounters.getCounter3());
				System.out.println("Counter 2: " + multipleCounters.getCounter2());
				System.out.println("Counter 1: " + multipleCounters.getCounter1());
			}
		});

		thread1.start();
		thread2.start();
	}
}
