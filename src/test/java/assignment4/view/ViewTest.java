package assignment4.view;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import assignment1.constants.Constant;
import assignment4.repo.Repo;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ViewTest {

  Repo repo = mock(Repo.class);
  final ResultSet dbsnapshot = mock(ResultSet.class);
  View view;

  @BeforeEach
  void setUp() {
    try {
      view = new View(repo, dbsnapshot);
    } catch (Exception exception) {
      assert false;
    }
  }

  @Test
  void testThread() {
    try {
      when(dbsnapshot.next()).thenReturn(true).thenReturn(false);
      when(dbsnapshot.getString(Constant.NAME)).thenReturn("Milk");
      when(dbsnapshot.getString(Constant.TYPE)).thenReturn("RAW");
      when(dbsnapshot.getString(Constant.PRICE)).thenReturn("30");
      when(dbsnapshot.getString(Constant.QUANTITY)).thenReturn("2");

      view.start();
    } catch (Exception exception) {
      assert false;
    }
  }

  @Test
  void testViewError() {
    try {
      when(dbsnapshot.next()).thenThrow(new SQLException("Error occurred"));

      view.start();
    } catch (Exception exception) {
      assert false;
    }
  }
}
