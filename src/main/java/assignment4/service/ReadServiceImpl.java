package assignment4.service;

import static assignment4.constants.Constant.DB_READ;
import static assignment4.constants.Constant.ID;
import static assignment4.constants.Constant.SELECT_ALL;
import static assignment4.constants.Constant.STARTING;

import assignment1.constants.Constant;
import assignment1.exceptions.InvalidException;
import assignment1.models.ItemModel;
import assignment1.services.AssignmentService;
import assignment4.repo.Repo;
import java.sql.ResultSet;

public class ReadServiceImpl implements DBService, Runnable {

  private Thread readThread = null;
  private final ResultSet dbSnapshot;
  private final String dbName = "ItemTable";
  private final AssignmentService assignmentService;
  static boolean alive = false;

  public ReadServiceImpl() throws Exception {
    String query = SELECT_ALL + dbName;
    this.dbSnapshot = new Repo().readDatabase(query);
    assignmentService = new AssignmentService();
  }

  public ReadServiceImpl(final ResultSet dbSnapshot, final AssignmentService assignmentService) {
    this.dbSnapshot = dbSnapshot;
    this.assignmentService = assignmentService;
  }

  static boolean isAlive() {
    return alive;
  }

  @Override
  public void run() {
    System.out.println(dbName);
    try {
      while (dbSnapshot.next()) {
        String queryInput = new StringBuilder().append(Constant.DELIMETER).append(Constant.NAME)
            .append(Constant.SPACE).append(dbSnapshot.getString(Constant.NAME))
            .append(Constant.SPACE).append(Constant.DELIMETER).append(Constant.TYPE)
            .append(Constant.SPACE).append(dbSnapshot.getString(Constant.TYPE))
            .append(Constant.SPACE).append(Constant.DELIMETER).append(Constant.PRICE)
            .append(Constant.SPACE).append(dbSnapshot.getString(Constant.PRICE))
            .append(Constant.SPACE).append(Constant.DELIMETER).append(Constant.QUANTITY)
            .append(Constant.SPACE).append(dbSnapshot.getString(Constant.QUANTITY)).toString();
        ItemModel model = assignmentService.processInput(queryInput);
        model.setId(Integer.parseInt(dbSnapshot.getString(ID)));

        if (model.getTotal() >= 0) {
          System.out.println(DB_READ + model.getName());
          data.add(model);
        } else {
          throw new InvalidException(Constant.DATA_INVALID);
        }
        Thread.sleep(1);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    alive = false;
  }

  public void start() {
    System.out.println(STARTING + dbName);
    if (readThread == null) {
      readThread = new Thread(this, dbName);
      alive = true;
      readThread.start();
    }
  }
}
