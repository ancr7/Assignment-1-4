package assignment4.dbConnection;

import assignment4.constants.Constant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnect {

  private static DBConnect singleInstance = null;
  private Connection connection = null;

  private DBConnect() throws Exception {
    Class.forName(Constant.DRIVER);
    Properties info = new Properties();
    info.put(Constant.USER, Constant.ROOT);
    info.put(Constant.PASSWORD, Constant.USER_PASSWORD);

    connection = DriverManager.getConnection(Constant.URL, info);
  }

  public static DBConnect getInstance() throws Exception {
    if (singleInstance == null) {
      singleInstance = new DBConnect();
    }
    return singleInstance;
  }

  public Connection getConnection() {
    return connection;
  }

}
