package assignment4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Repo {

  private Statement statement;

  Repo() throws Exception{
      Connection connection = DBConnect.getInstance().getConnection();
      statement = connection.createStatement();
  }

  public ResultSet readDatabase(String query) throws Exception {
      return statement.executeQuery(query);
  }

  public void writeDatabase(String query)  throws Exception{
      statement.executeUpdate(query);
  }
}