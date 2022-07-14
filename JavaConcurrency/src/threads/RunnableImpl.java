package threads;

public class RunnableImpl implements Runnable{
  @Override
  public void run() {
    System.out.println("Runnable running on thread: " + Thread.currentThread().getName());
  }
}
