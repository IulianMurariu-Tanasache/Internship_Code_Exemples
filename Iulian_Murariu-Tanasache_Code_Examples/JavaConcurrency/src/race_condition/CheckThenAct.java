package race_condition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CheckThenAct {

	public static void main(String[] args) {
		Map<String, String> sharedMap = new ConcurrentHashMap<>();

		Runnable runnable = () -> {
			for (int i = 0; i < 1_000_000; i++) {
				if (sharedMap.containsKey("key")) {
					String val = sharedMap.remove("key");
					if (val == null) {
						System.out.println("iteration " + i + ": Value for 'key' was null");
					}
				}
				else {
					sharedMap.put("key", "value");
				}
			}
		};

		Thread thread1 = new Thread(runnable);
		Thread thread2 = new Thread(runnable);

		thread1.start();
		thread2.start();
	}
}
