package producer_consumer;

import static java.lang.Thread.sleep;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

	private BlockingQueue<String> blockingQueue;

	public Producer(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		while (true) {
			long timeMillis = System.currentTimeMillis();
			try {
				this.blockingQueue.put("" + timeMillis);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
