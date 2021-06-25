package assignment4.dbConnection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DBConnectTest {

  static DBConnect dbConnect;

  @BeforeAll
  static void getInstanceTest() {
    try {
      dbConnect = DBConnect.getInstance();
    } catch (Exception exception) {
      assert false;
    }
  }

  @Test
  void getConnectionNullTest() {
    try {
      assertNotNull(dbConnect.getConnection());
    } catch (Exception exception) {
      assert false;
    }
  }
}
