package synchronized_keyword;

public class ReentranceMainClass {

	public static void main(String[] args) {
		Reentrance reentrance = new Reentrance();
		Thread thread1 = new Thread( () -> {
			for(int i = 0; i < 1_000_00; ++i){
				System.out.println(reentrance.IncAndGet());
			}
		});

		Thread thread2 = new Thread(() -> {
			for(int i = 0; i < 1_000_00; ++i){
				System.out.println(reentrance.IncAndGet());
			}
		});

		thread1.start();
		thread2.start();
	}
}
