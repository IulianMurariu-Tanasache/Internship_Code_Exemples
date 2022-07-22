package race_condition;

public class OneWritesOneReads {

	public static void main(String[] args) {
		Counter counter = new Counter();

		Thread readThread = new Thread(() -> {
			for(int i = 0; i < 5; i ++) {
				try{
					Thread.sleep(1);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}

				System.out.println("Read thread: " + counter.getCounter());
			}
		});

		Thread writeThread = new Thread(() -> {
			for(int i = 0; i < 1_000_000; i ++) {
				counter.IncAndGet();
			}

			System.out.println("Thread 1: " + counter.getCounter());
		});

		writeThread.start();
		readThread.start();
	}
}
