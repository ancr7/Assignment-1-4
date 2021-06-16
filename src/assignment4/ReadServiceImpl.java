package assignment4;

import assignment1.models.ItemModel;
import assignment1.services.AssignmentService;
import java.sql.ResultSet;

class ReadServiceImpl implements DBService, Runnable {

  private Thread readThread;
  private final ResultSet dbSnapshot;
  private final String dbName = "ItemTable";
  static boolean alive = false;

  ReadServiceImpl()  throws Exception{
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
        String queryInput =
            "-name " + dbSnapshot.getString("name") + " -type " + dbSnapshot.getString("type")
                + " -price " + dbSnapshot.getString("price") + " -quantity " + dbSnapshot
                .getString("quantity");


        AssignmentService assignmentService = new AssignmentService();


        ItemModel model = assignmentService.processInput(queryInput);
        model.setId(Integer.parseInt(dbSnapshot.getString("id")));

        if (model.getTotal() >= 0) {
          System.out.println("DB Read " + model.getName());
          data.add(model);
        } else {
          System.out.println("Data Invalid!");
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
