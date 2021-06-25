package assignment4.service;

import static assignment4.constants.Constant.CLOSING_BRACE;
import static assignment4.constants.Constant.COMMA;
import static assignment4.constants.Constant.DB_WRITE;
import static assignment4.constants.Constant.ERROR_OCCURRED;
import static assignment4.constants.Constant.OPENING_BRACE;
import static assignment4.constants.Constant.REPLACE_INTO;
import static assignment4.constants.Constant.VALUES;

import assignment1.models.ItemModel;
import assignment4.constants.Constant;
import assignment4.repo.Repo;

public class WriteServiceImpl implements DBService, Runnable {

  private Thread writeThread = null;
  private final String dbName = "TaxTable";
  static boolean alive = false;
  private final Repo repo;

  public WriteServiceImpl() throws Exception {
    repo = new Repo();
  }

  public WriteServiceImpl(final Repo repo) {
    this.repo = repo;
  }

  public static boolean isAlive() {
    return alive;
  }

  @Override
  public void run() {
    try {
      while (ReadServiceImpl.isAlive() || !data.isEmpty()) {
        if (!data.isEmpty()) {
          ItemModel model = data.poll();
          System.out.println(DB_WRITE + model.getName());
          String query =
              new StringBuilder().append(REPLACE_INTO).append(dbName).append(Constant.SPACE).append(VALUES).append(
                  OPENING_BRACE)
                  .append(model.getId()).append(COMMA).append(model.getTax()).append(CLOSING_BRACE).toString();
          repo.writeDatabase(query);
        }
        Thread.sleep(1);
      }
    } catch (Exception e) {
      System.out.println(ERROR_OCCURRED + e.getMessage());
    }
    alive = false;
  }

  public void start() {
    if (writeThread == null) {
      writeThread = new Thread(this, dbName);
      alive = true;
      writeThread.start();
    }
  }
}
