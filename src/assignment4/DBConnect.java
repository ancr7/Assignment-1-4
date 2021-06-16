package assignment4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnect {

  private static DBConnect singleInstance;
  private Connection connection;

  private DBConnect() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      String url = "jdbc:mysql://127.0.0.1:3306/assignment";
      Properties info = new Properties();
      info.put("user", "admin");
      info.put("password", "Password@123");

      connection = DriverManager.getConnection(url, info);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static DBConnect getInstance() {
    if (singleInstance == null) {
      try {
        singleInstance = new DBConnect();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return singleInstance;
  }

  public Connection getConnection() {
    return connection;
  }

}

// BO, DAO.
// DAta access object, business obj
