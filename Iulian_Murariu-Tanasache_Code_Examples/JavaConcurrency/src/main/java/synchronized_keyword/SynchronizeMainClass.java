package synchronized_keyword;

public class SynchronizeMainClass {

	public static void main(String[] args) {
		SynchronizedExchanger exchanger = new SynchronizedExchanger();

		Thread thread1 = new Thread( () -> {
			for(int i = 0; i < 1_000_00; ++i){
				exchanger.setObjSyncMethod("" + i);
			}
		});

		Thread thread2 = new Thread(() -> {
			for(int i = 0; i < 1_000_00; ++i){
				System.out.println(exchanger.getObjSyncBlock());
			}
		});

		thread1.start();
		thread2.start();
	}
}
