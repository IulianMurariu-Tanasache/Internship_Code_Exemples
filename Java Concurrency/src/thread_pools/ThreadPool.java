package thread_pools;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {

	private BlockingQueue taskQueue = null;
	private List<PoolThreadRunnable> runnables = new ArrayList<>();
	private boolean isStopped = false;

	public ThreadPool(int noThreads, int maxNoOfThreads) {
		taskQueue = new ArrayBlockingQueue(maxNoOfThreads);

		for(int i = 0; i < noThreads; ++i) {
			PoolThreadRunnable poolThreadRunnable = new PoolThreadRunnable(taskQueue);
			runnables.add(poolThreadRunnable);
		}

		for(PoolThreadRunnable runnable: runnables){
			new Thread(runnable).start();
		}
	}

	public synchronized void execute(Runnable task) throws Exception {
		if(this.isStopped)
			throw new IllegalStateException("ThreadPool is stopped");

		taskQueue.offer(task);
	}

	public synchronized void stop() {
		isStopped = true;
		for(PoolThreadRunnable runnable: runnables) {
			runnable.doStop();
		}
	}

	public synchronized void waitUntilAllTasksFinished() {
		while(taskQueue.size() > 0) {
			try{
				Thread.sleep(1);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
