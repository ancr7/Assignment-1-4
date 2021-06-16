package assignment4;

import assignment1.models.ItemModel;

public class WriteServiceImpl implements DBService, Runnable {

  private Thread writeThread;
  private final String dbName = "TaxTable";
  static boolean alive = false;
  private Repo repo;

  public WriteServiceImpl()  throws Exception{
    writeThread = null;
    repo = new Repo();
  }

  static boolean isAlive() {
    return alive;
  }

  @Override
  public void run() {
    try {
      while (ReadServiceImpl.isAlive() || !data.isEmpty()) {
        if (data.isEmpty()) {
//          System.out.println("wait");
        } else {
          ItemModel model = data.poll();
          System.out.println("DB write " + model.getName());
          String query =
              "REPLACE INTO " + dbName + " values(" + model.getId() + "," + model.getTax() + ")";
          repo.writeDatabase(query);
        }
        Thread.sleep(1);
      }
      alive = false;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void start() {
    if (writeThread == null) {
      writeThread = new Thread(this, dbName);
      alive = true;
      writeThread.start();
    }
  }
}
