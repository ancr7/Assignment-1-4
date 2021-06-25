package assignment4.repo;

import assignment4.dbConnection.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Repo {

  private final Statement statement;

  public Repo(final Statement statement) {
    this.statement = statement;
  }

  public Repo() throws Exception{
      Connection connection = DBConnect.getInstance().getConnection();
      statement = connection.createStatement();
  }

  public ResultSet readDatabase(final String query) throws Exception {
      return statement.executeQuery(query);
  }

  public void writeDatabase(final String query)  throws Exception{
      statement.executeUpdate(query);
  }
}