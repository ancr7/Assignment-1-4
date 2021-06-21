package assignment4.service;

import assignment1.constants.Constant;
import assignment1.models.ItemModel;
import assignment1.services.AssignmentService;
import assignment4.repo.Repo;
import java.sql.ResultSet;

public class ReadServiceImpl implements DBService, Runnable {

  private Thread readThread;
  private final ResultSet dbSnapshot;
  private final String dbName = "ItemTable";
  static boolean alive = false;

  public ReadServiceImpl() throws Exception {
    readThread = null;
    String query = "select * from " + dbName;
    this.dbSnapshot = new Repo().readDatabase(query);
  }

  static boolean isAlive() {
    return alive;
  }

  @Override
  public void run() {
    System.out.println(dbName);
    try {
      while (dbSnapshot.next()) {
        String queryInput = Constant.DELIMETER + Constant.NAME + Constant.SPACE + dbSnapshot
            .getString(Constant.NAME) + Constant.SPACE + Constant.DELIMETER + Constant.TYPE
            + Constant.SPACE + dbSnapshot.getString(Constant.TYPE) + Constant.SPACE
            + Constant.DELIMETER + Constant.PRICE + Constant.SPACE + dbSnapshot
            .getString(Constant.PRICE) + Constant.SPACE + Constant.DELIMETER + Constant.QUANTITY
            + Constant.SPACE + dbSnapshot.getString(Constant.QUANTITY);

        AssignmentService assignmentService = new AssignmentService();

        ItemModel model = assignmentService.processInput(queryInput);
        model.setId(Integer.parseInt(dbSnapshot.getString("id")));

        if (model.getTotal() >= 0) {
          System.out.println("DB Read " + model.getName());
          data.add(model);
        } else {
          System.out.println(Constant.DATA_INVALID);
        }
        Thread.sleep(1);
      }
      alive = false;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void start() {
//    System.out.println("Starting " +  DBName );
    if (readThread == null) {
      readThread = new Thread(this, dbName);
      alive = true;
      readThread.start();
    }
  }
}
