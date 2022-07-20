package thread_local;

public class ThreadLocalBasicExemple {

	public static void main(String[] args) {
		ThreadLocal<String> threadLocal = new ThreadLocal<>();

		Thread thread1 = new Thread(() -> {
			threadLocal.set("thread1");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			//threadLocal.remove(); // -> removes value only for this thread
			String value = threadLocal.get();
			System.out.println(value);
		});

		Thread thread2 = new Thread(() -> {
			threadLocal.set("thread2");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			String value = threadLocal.get();
			System.out.println(value);
		});

		thread1.start();
		thread2.start();
	}
}
