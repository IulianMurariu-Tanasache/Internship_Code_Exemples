package producer_consumer;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

	private BlockingQueue<String> blockingQueue;

	public Consumer(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		while (true) {
			try{
				String element = this.blockingQueue.take();
				System.out.println(threadName + " consumed " + element);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
