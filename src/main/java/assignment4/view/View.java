package assignment4.view;

import assignment1.constants.Constant;
import assignment4.repo.Repo;
import assignment4.service.WriteServiceImpl;
import java.sql.ResultSet;

public class View implements Runnable {

  private Thread readThread = null;
  private ResultSet dbsnapshot = null;

  private final String dbName = "ItemTable";
  private final String query = "select * from ItemTable,TaxTable where ItemTable.id = TaxTable.id";
  private final String seperator =
      "-----------------------------------------------------------------";


  static boolean alive = false;
  private final Repo repo;

  public View() throws Exception {
    repo = new Repo();
  }

  public View(final Repo repo, final ResultSet dbsnapshot) throws Exception {
    this.repo = repo;
    this.dbsnapshot = dbsnapshot;
  }

  @Override
  public void run() {
    System.out.println(dbName);
    try {
      while (WriteServiceImpl.isAlive()) {
        Thread.sleep(500);
      }
      if (dbsnapshot == null) dbsnapshot = repo.readDatabase(query);
      while (dbsnapshot.next()) {
        System.out.println(seperator);
        String queryOutput = new StringBuilder().append(Constant.NAME).append(Constant.COLON)
            .append(dbsnapshot.getString(Constant.NAME)).append(Constant.SPACE)
            .append(Constant.TYPE).append(Constant.COLON)
            .append(dbsnapshot.getString(Constant.TYPE)).append(Constant.SPACE)
            .append(Constant.PRICE).append(Constant.COLON)
            .append(dbsnapshot.getString(Constant.PRICE)).append(Constant.SPACE)
            .append(Constant.QUANTITY).append(Constant.COLON)
            .append(dbsnapshot.getString(Constant.QUANTITY)).toString();
        System.out.println(queryOutput);
        System.out.println(seperator);
        Thread.sleep(500);
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    alive = false;
  }

  public void start() {
    if (readThread == null) {
      readThread = new Thread(this, dbName);
      alive = true;
      readThread.start();
    }
  }
}
