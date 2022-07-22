package deadlocks;

public class RunnableSync2 implements Runnable{
	private Object lockObj1;
	private Object lockObj2;

	public RunnableSync2(Object lockObj1, Object lockObj2) {
		this.lockObj1 = lockObj1;
		this.lockObj2 = lockObj2;
	}

	@Override
	public void run() {
		System.out.println("Runnable 2 locks on object 1");

		synchronized (this.lockObj2) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			System.out.println("Runnable 2 trying to lock on object 1");
			synchronized (this.lockObj1) {
				System.out.println("Runnable 2 successfully locked on object 1");
			}
		}
	}
}
