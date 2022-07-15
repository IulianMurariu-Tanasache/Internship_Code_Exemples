package lock;

public class ReentrantLockMainClass {

	public static void main(String[] args) {
		ReentrantLockCalculator calculator = new ReentrantLockCalculator();

		Thread thread1 = new Thread(() -> {
			calculator.calculate(5, Calculation.ADD, Calculation.ADD, Calculation.ADD);
		});

		Thread thread2 = new Thread(() -> {
			calculator.calculate(1, Calculation.SUBTRACT, Calculation.SUBTRACT, Calculation.SUBTRACT);
		});

		Thread thread3 = new Thread(() -> {
			for(int i = 0; i < 5; ++i ) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				System.out.println(calculator.getResult());
			}
		});

		thread1.start();
		thread2.start();
		thread3.start();
	}
}
