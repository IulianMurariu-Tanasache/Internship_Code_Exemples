package threads;

public class CustomThread extends Thread{

  private boolean isRunning = false;

  public CustomThread(String name) {
    super(name);
  }

  public boolean isRunning() {
    return isRunning;
  }

  public synchronized void setRunning(boolean running) {
    isRunning = running;
  }

  @Override
  public synchronized void start() {
    System.out.println("Custom thread starting");
    setRunning(true);
    super.start();
  }

  @Override
  public void run() {
    while(isRunning())
    {
      System.out.println("Custom thread running on thread: " + Thread.currentThread().getName());
    }
    System.out.println("Custom thread stopping");
  }
}
