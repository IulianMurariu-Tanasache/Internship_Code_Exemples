package memory_model;

public class MemoryModelMainClass {

  public static void main(String[] args) {
      Runnable runnable1 = new MemoryModelRunnableImpl();

      //same runnable object for both
      Thread thread1 = new Thread(runnable1, "Thread1");
      Thread thread2 = new Thread(runnable1, "Thread2");

      thread1.start();
      thread2.start();

      try {
        thread1.join();
        thread2.join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      //different runnable objects
      runnable1 = new MemoryModelRunnableImpl();
      Runnable runnable2 = new MemoryModelRunnableImpl();

      thread1 = new Thread(runnable1, "Thread1");
      thread2 = new Thread(runnable2, "Thread2");

      thread1.start();
      thread2.start();

      try {
        thread1.join();
        thread2.join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

  }
}
