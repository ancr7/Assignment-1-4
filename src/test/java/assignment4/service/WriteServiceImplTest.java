package assignment4.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import assignment1.exceptions.InvalidException;
import assignment1.models.ItemModel;
import assignment1.models.RawModel;
import assignment4.repo.Repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WriteServiceImplTest {

  private final Repo repo = mock(Repo.class);
  private static WriteServiceImpl writeService;

  @BeforeEach
  void setUp() {
    try {
      writeService = new WriteServiceImpl(repo);
    } catch (Exception exception) {
      assert false;
    }
  }

  @BeforeEach
  void fillData() {
    ItemModel model = new RawModel();
    model.setId(1);
    model.setName("Milk");
    model.setQuantity(2);
    model.setPrice(30);
    model.setTax(5);
    writeService.data.add(model);
  }

  @Test
  void testThread() {
    String query = "REPLACE INTO TaxTable values(1,5)";
    try {
      doNothing().when(repo).writeDatabase(anyString());
      writeService.start();
    } catch (Exception exception) {
      assert false;
    }
  }

  @Test
  void testWriteServiceImplConstructor() {
    try {
      WriteServiceImpl writeServiceTestObject = new WriteServiceImpl();
      assertFalse(WriteServiceImpl.isAlive());
    } catch (Exception exception) {
      assert false;
    }
  }

  @Test
  void testWriteServiceImplError() {
    final String query = "REPLACE INTO TaxTable values(1,7)";

    try {
      doThrow(new InvalidException("Error reading DataBase")).when(repo).writeDatabase(anyString());

      writeService.start();
      while (WriteServiceImpl.isAlive()) {
        Thread.sleep(2);
      }
      assertFalse(WriteServiceImpl.isAlive());
    } catch (Exception exception) {
      assert false;
    }
  }
}
/*
public class ReadServiceImpl {

  private final ResultSet resultSet = mock(ResultSet.class);
  private final AssignmentService assignmentService = mock(AssignmentService.class);
  private ReadServiceImpl readService;

  @BeforeEach
  void setUp() {
    readService = new ReadServiceImpl(resultSet, assignmentService);
  }

  @Test
  void testThread() {
    try {

      when(resultSet.next()).thenReturn(true).thenReturn(false);
      when(resultSet.getString(Constant.NAME)).thenReturn("Milk");
      when(resultSet.getString(Constant.TYPE)).thenReturn("RAW");
      when(resultSet.getString(Constant.PRICE)).thenReturn("30");
      when(resultSet.getString(Constant.QUANTITY)).thenReturn("2");
      when(resultSet.getString("Id")).thenReturn("1");

      ItemModel itemModel = new RawModel();
      itemModel.setName("Milk");
      itemModel.setTotal(60);

      String input = "-name Milk -type RAW -price 30 -quantity 2";

      when(assignmentService.processInput(input)).thenReturn(itemModel);

      readService.start();
      while (readService.isAlive()) {
        Thread.sleep(1);
      }

      assertEquals(1, readService.data.size());

    } catch (Exception throwables) {
      throwables.printStackTrace();
    }
  }

  @Test
  void testThreadWithError() {
    try {

      when(resultSet.next()).thenReturn(true).thenReturn(false);
      when(resultSet.getString(Constant.NAME)).thenReturn("Milk");
      when(resultSet.getString(Constant.TYPE)).thenReturn("RAW");
      when(resultSet.getString(Constant.PRICE)).thenReturn("30");
      when(resultSet.getString(Constant.QUANTITY)).thenReturn("2");
      when(resultSet.getString("Id")).thenReturn("1");

      ItemModel itemModel = new RawModel();
      itemModel.setName("Milk");
      itemModel.setTotal(-1);

      String input = "-name Milk -type RAW -price 30 -quantity 2";

      when(assignmentService.processInput(input)).thenReturn(itemModel);

      readService.start();
      while (readService.isAlive()) {
        Thread.sleep(1);
      }

      assertEquals(0, readService.data.size());

    } catch (Exception throwables) {
      throwables.printStackTrace();
    }
  }

  @Test
  void testReadServiceImplConstructor() {

    try {
      ReadServiceImpl readServiceTestObject = new ReadServiceImpl();
    } catch (Exception exception) {
      assert false;
    }
  }
}
 */
