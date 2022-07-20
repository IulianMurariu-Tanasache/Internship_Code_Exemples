package memory_model;

public class MemoryModelRunnableImpl implements Runnable{

  private int counter = 0;

  @Override
  public void run() {
    System.out.println("Runnable running on thread: " + Thread.currentThread().getName());

    for(int i = 0; i < 1_000_000; ++i) {
      counter++;
    }

    System.out.println(Thread.currentThread().getName() + ": " + counter);
  }
}
