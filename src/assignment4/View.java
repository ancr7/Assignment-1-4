package assignment4;

import java.sql.ResultSet;

public class View implements Runnable {

  private Thread readThread;
  private ResultSet dbsnapshot;
  private final String dbName = "ItemTable";
  private final String query = "select * from ItemTable,TaxTable where ItemTable.id = TaxTable.id";
  static boolean alive = false;
  private Repo repo;

  View()  throws Exception {
    readThread = null;
    repo = new Repo();
  }

  static boolean isAlive() {
    return alive;
  }

  @Override
  synchronized public void run() {
    System.out.println(dbName);
    try {
      while (WriteServiceImpl.isAlive()) {
        Thread.sleep(500);
      }
      dbsnapshot = repo.readDatabase(query);
      while (dbsnapshot.next()) {
        System.out.println("-----------------------------------------------------------------");
        String queryOutput =
            "name: " + dbsnapshot.getString("name") + " type: " + dbsnapshot.getString("type")
                + " price: " + dbsnapshot.getString("price") + " quantity: " + dbsnapshot
                .getString("quantity");
        System.out.println(queryOutput);
        System.out.println("-----------------------------------------------------------------");
        Thread.sleep(500);
      }
      alive = false;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void start() {
    if (readThread == null) {
      readThread = new Thread(this, dbName);
      alive = true;
      readThread.start();
    }
  }
}
