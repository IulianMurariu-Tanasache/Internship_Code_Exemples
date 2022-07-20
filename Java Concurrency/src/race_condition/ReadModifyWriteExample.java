package race_condition;

public class ReadModifyWriteExample {

	public static void main(String[] args) {
		Counter counter = new Counter();
		Runnable badRunnable = () -> {
			for(int i = 0; i < 1_000_000; ++i){
				counter.IncAndGet();
			}

			System.out.println(counter.getCounter());
		};

		Runnable goodRunnable = () -> {
			for(int i = 0; i < 1_000_000; ++i){
				counter.AtomicIncAndGet();
			}

			System.out.println(counter.atomicGet());
		};

		Thread thread1 = new Thread(badRunnable);
		Thread thread2 = new Thread(badRunnable);

		thread1.start();
		thread2.start();

		thread1 = new Thread(goodRunnable);
		thread2 = new Thread(goodRunnable);

		thread1.start();
		thread2.start();
	}
}
