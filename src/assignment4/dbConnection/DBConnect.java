package assignment4.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnect {

  private static DBConnect singleInstance;
  private Connection connection;
  private final static String driver = "com.mysql.cj.jdbc.Driver";
  private final static String url = "jdbc:mysql://127.0.0.1:3306/assignment";

  private DBConnect() throws Exception {
    Class.forName(driver);
    Properties info = new Properties();
    info.put("user", "admin");
    info.put("password", "Password@123");

    connection = DriverManager.getConnection(url, info);
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

// BO, DAO.
// DAta access object, business obj
