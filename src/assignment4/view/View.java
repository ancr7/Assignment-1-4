package assignment4.view;

import assignment1.constants.Constant;
import assignment4.repo.Repo;
import assignment4.service.WriteServiceImpl;
import java.sql.ResultSet;

public class View implements Runnable {

  private Thread readThread;
  private ResultSet dbsnapshot;

  private final String dbName = "ItemTable";
  private final String query = "select * from ItemTable,TaxTable where ItemTable.id = TaxTable.id";
  private final String seperator =
      "-----------------------------------------------------------------";


  static boolean alive = false;
  private final Repo repo;

  public View() throws Exception {
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
//        System.out.println(seperator);
        String queryOutput =
            Constant.NAME + Constant.COLON + dbsnapshot.getString(Constant.NAME) + Constant.SPACE
                + Constant.TYPE + Constant.COLON + dbsnapshot.getString(Constant.TYPE)
                + Constant.SPACE + Constant.PRICE + Constant.COLON + dbsnapshot
                .getString(Constant.PRICE) + Constant.SPACE + Constant.QUANTITY + Constant.COLON
                + dbsnapshot.getString(Constant.QUANTITY);
//        System.out.println(queryOutput);
//        System.out.println(seperator);
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
