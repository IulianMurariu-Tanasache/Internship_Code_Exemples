package thread_pools;

import java.util.concurrent.BlockingQueue;

public class PoolThreadRunnable implements Runnable{

	private Thread thread = null;
	private BlockingQueue taskQueue = null;
	private boolean isStopped = false;

	public PoolThreadRunnable(BlockingQueue taskQueue) {
		this.taskQueue = taskQueue;
	}

	public void doStop() {
		isStopped = true;
		thread.interrupt();
	}

	@Override
	public void run() {
		this.thread = Thread.currentThread();
		while(!isStopped) {
			try{
				Runnable runnable = (Runnable) taskQueue.take();
				runnable.run();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public synchronized boolean isStopped(){
		return isStopped;
	}
}
