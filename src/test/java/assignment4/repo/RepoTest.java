package assignment4.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RepoTest {

  static Statement statement = mock(Statement.class);
  static Repo repo;

  @BeforeAll
  static void setUp() {
    repo = new Repo(statement);
  }

  @Test
  void testReadDatabase() {
    final String query = "select * from ItemTable;";
    ResultSet resultSet = mock(ResultSet.class);
    try {
      when(statement.executeQuery(query)).thenReturn(resultSet);
      assertEquals(resultSet, repo.readDatabase(query));
    } catch (Exception exception) {
      assert false;
    }
  }

  @Test
  void testWriteDatabase() {
    final String query = "select * from ItemTable;";
    ResultSet resultSet = mock(ResultSet.class);
    try {
      when(statement.executeUpdate(query)).thenReturn(1);
      repo.writeDatabase(query);
    } catch (Exception exception) {
      assert false;
    }
  }

  @Test
  void testRepoConstructor() {
    try {
      Repo repoTestObject = new Repo();
    } catch (Exception exception) {
      assert false;
    }
  }
}
