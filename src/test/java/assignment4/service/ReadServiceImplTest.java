package assignment4.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import assignment1.constants.Constant;
import assignment1.models.ItemModel;
import assignment1.models.RawModel;
import assignment1.services.AssignmentService;
import java.sql.ResultSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReadServiceImplTest {

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
      while (ReadServiceImpl.isAlive()) {
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
