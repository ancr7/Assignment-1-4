package assignment1.models;

import assignment1.exceptions.InvalidException;
import assignment1.exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RawModelTest {
    ItemModel rawModel = new RawModel();

    @Test
  void checkRawTaxInputIsValid() {
      Exception exception = assertThrows(InvalidInputException.class, () -> {
      rawModel.calcTax(-1); });

    String expectedMessage = "Total cant be -ve";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
      @Test
  void rawTaxWhenTotalIs34() {
    try {
      assertEquals(4.25, rawModel.calcTax(34));
    } catch (InvalidException e) { assert(false); }
  }
}
