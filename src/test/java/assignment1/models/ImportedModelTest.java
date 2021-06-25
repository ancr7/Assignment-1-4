package assignment1.models;

import assignment1.exceptions.InvalidException;
import assignment1.exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImportedModelTest {
    ItemModel importedModel = new ImportedModel();
    @Test
  void checkImportedTaxInputIsValid() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      importedModel.calcTax(-1); });

    String expectedMessage = "Total cant be -ve";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
      @Test
  void importedTaxWhenTotalIs90() {
    try {
      assertEquals(14, importedModel.calcTax(90));
    } catch (InvalidException e) { assert(false); }
  }
  @Test
  void importedTaxWhenTotalIs144() {
    try {
      assertEquals(24.4, importedModel.calcTax(144));
    } catch (InvalidException e) { assert(false); }
  }
      @Test
  void importedTaxWhenTotalIs443() {
    try {
      assertEquals(68.66499999999999, importedModel.calcTax(443));
    } catch (InvalidException e) { assert(false); }
  }
}
