package thread_pools;

public class ThreadPoolMainClass {

	public static void main(String[] args) {
		ThreadPool threadPool = new ThreadPool(3, 10);

		for(int i = 0; i < 10; ++ i) {
			int taskNo = i;
			try {
				threadPool.execute(() -> {
					String msg = Thread.currentThread().getName() + ": Task " + taskNo;
					System.out.println(msg);
				});
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		threadPool.waitUntilAllTasksFinished();
		threadPool.stop();
	}
}
