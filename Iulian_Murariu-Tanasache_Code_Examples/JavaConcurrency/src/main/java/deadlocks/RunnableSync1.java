package deadlocks;

public class RunnableSync1 implements Runnable{

	private Object lockObj1;
	private Object lockObj2;

	public RunnableSync1(Object lockObj1, Object lockObj2) {
		this.lockObj1 = lockObj1;
		this.lockObj2 = lockObj2;
	}

	@Override
	public void run() {
		System.out.println("Runnable 1 locks on object 1");

		synchronized (this.lockObj1) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			System.out.println("Runnable 1 trying to lock on object 2");
			synchronized (this.lockObj2) {
				System.out.println("Runnable 1 successfully locked on object 2");
			}
		}
	}
}
