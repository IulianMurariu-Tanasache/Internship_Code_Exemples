package threads;

public class ThreadMainClass {

  public static void main(String[] args) {
    CustomThread myThread = new CustomThread("thread 1");
    Runnable myRunnable = new RunnableImpl();
    Runnable myAnonymousRunnable = new Runnable() {
      @Override
      public void run() {
        System.out.println("Anonymous runnable running on thread: " + Thread.currentThread().getName());
      }
    };
    Runnable myLambdaRunnable = () -> {
      System.out.println("Lambda runnable running on thread: " + Thread.currentThread().getName());
    };

    Thread threadWithRunnable = new Thread(myRunnable, "thread 2");
    Thread threadWithAnonymousRunnable = new Thread(myAnonymousRunnable, "thread 3");
    Thread threadWithLambdaRunnable = new Thread(myLambdaRunnable, "thread 4");

    myThread.start();
    threadWithRunnable.start();
    threadWithLambdaRunnable.start();
    threadWithAnonymousRunnable.start();

    try {
      Thread.sleep(100);
      myThread.setRunning(false);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
