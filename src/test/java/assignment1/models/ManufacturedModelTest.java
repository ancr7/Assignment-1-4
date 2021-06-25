package assignment1.models;

import assignment1.exceptions.InvalidException;
import assignment1.exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ManufacturedModelTest {
    ItemModel manufacturedModel = new ManufacturedModel();
      @Test
  void checkManufacturedTaxInputIsValid() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      manufacturedModel.calcTax(-1); });

    String expectedMessage = "Total cant be -ve";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void manufacturedTaxWhenTotalIs25() {
    try {
      assertEquals(3.6875, manufacturedModel.calcTax(25));
    } catch (InvalidException e) { assert(false); }
  }
}
