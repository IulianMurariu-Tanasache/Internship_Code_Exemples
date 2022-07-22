package thread_local;

public class ThreadLocalInitialValueAndInlheritable {

	public static void main(String[] args) {

		// needs a lambda functions that returns / supplies the value
		ThreadLocal<String> threadLocalWithInitial = ThreadLocal.withInitial(() -> {
			return "threadlocal with initial";
		});

		InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>() {
			@Override
			protected String initialValue() {
				return "inheritable threadlocal";
			}
		};

		Thread parentThread = new Thread(() -> {
			System.out.println("=== Parent Thread ===");
			threadLocalWithInitial.set("new value for threadlocal");
			inheritableThreadLocal.set("new value for inheritable threadlocal");

			System.out.println(threadLocalWithInitial.get());
			System.out.println(inheritableThreadLocal.get());

			Thread childThread = new Thread(() -> {
				System.out.println("=== Child Thread ===");
				System.out.println(threadLocalWithInitial.get());
				System.out.println(inheritableThreadLocal.get());
			});

			childThread.start();
		});

		parentThread.start();
	}

}
