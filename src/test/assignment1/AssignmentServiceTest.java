package test.assignment1;

import static assignment1.mapper.ExtractData.extractWord;
import static assignment1.utils.ValidatorUtil.isValidFormat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import assignment1.exceptions.InvalidException;
import assignment1.exceptions.InvalidFormatException;
import assignment1.exceptions.InvalidInputException;
import assignment1.models.ImportedModel;
import assignment1.models.ItemModel;
import assignment1.models.ManufacturedModel;
import assignment1.models.RawModel;
import assignment1.services.AssignmentService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AssignmentServiceTest {

  private static ItemModel rawModel, importedModel, manufacturedModel;
  private static AssignmentService assignmentService;
  @Test
  @BeforeAll
  static void createAllModelObjects() {
    rawModel = new RawModel();
    importedModel = new ImportedModel();
    manufacturedModel = new ManufacturedModel();
    assignmentService = new AssignmentService();
  }

  @Test
  @DisplayName("When input is Empty")
  void checkIfInputEmpty() {

    Exception exception = assertThrows(InvalidFormatException.class, () -> {
      assignmentService.processInput(""); });

    String expectedMessage = "Invalid input format";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  @DisplayName("When name format is wrong")
  void checkInputName() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      assignmentService.processInput("-name 233 -price 10 -quantity 2 -type raw"); });

    String expectedMessage = "Invalid name";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  @DisplayName("When price format is wrong")
  void checkInputPrice() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      assignmentService.processInput("-name abc -price ww -quantity 2 -type raw"); });

    String expectedMessage = "Invalid price";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
//    assertEquals(false, isInputValid("-name abc -price ww -quantity 2 -type raw"));
  }

  @Test
  @DisplayName("When quantity format is wrong")
  void checkInputQuantity() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      assignmentService.processInput("-name abc -price 10 -quantity F -type raw"); });

    String expectedMessage = "Invalid quantity";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  @DisplayName("When type format is wrong")
  void checkInputType() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      assignmentService.processInput("-name abc -price 10 -quantity 2 -type 35"); });

    String expectedMessage = "Invalid type";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }


  @Test
  @DisplayName("check price when -ve")
  void checkPriceNonNegative() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      assignmentService.processInput("-name abc -price -2 -quantity 1 -type raw"); });

    String expectedMessage = "Invalid price";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
  @Test
  @DisplayName("check quantity when -ve")
  void checkQuantityNonNegative() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      assignmentService.processInput("-name abc -price 20 -quantity -1 -type raw"); });

    String expectedMessage = "Invalid quantity";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
  @Test
  @DisplayName("check Empty name")
  void checkEmptyName() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      assignmentService.processInput("-name   -price -2 -quantity 1 -type raw"); });

    String expectedMessage = "Invalid name";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
  @Test
  @DisplayName("check Empty price")
  void checkEmptyPrice() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      assignmentService.processInput("-name abc -price   -quantity 1 -type raw"); });

    String expectedMessage = "Invalid price";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  @DisplayName("check Empty quantity")
  void checkEmptyQuantity() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      assignmentService.processInput("-name abc -price 12 -quantity   -type raw"); });

    String expectedMessage = "Invalid quantity";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  @DisplayName("check Empty Type")
  void checkEmptyType() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      assignmentService.processInput("-name abc -price 12 -quantity 2 -type  "); });

    String expectedMessage = "Empty type";
    String actualMessage = exception.getMessage();

    System.out.println(actualMessage);
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  @DisplayName("check name appear once")
  void nameAppearOnlyOneTime() {
    Exception exception = assertThrows(InvalidFormatException.class, () -> {
      assignmentService.processInput("-name abc -name def -price 12 -quantity 2 abc -type raw"); });

    String expectedMessage = "Invalid -name";
    String actualMessage = exception.getMessage();

    System.out.println(actualMessage);
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  @DisplayName("check price appear once")
  void priceAppearOnlyOneTime() {
    Exception exception = assertThrows(InvalidFormatException.class, () -> {
      assignmentService.processInput("-name abc -price 12 -price 12 -quantity 2 abc -type raw"); });

    String expectedMessage = "Invalid -price";
    String actualMessage = exception.getMessage();

    System.out.println(actualMessage);
    assertTrue(actualMessage.contains(expectedMessage));
   }

  @Test
  @DisplayName("check quantity appear once")
  void quantityAppearOnlyOneTime() {
    Exception exception = assertThrows(InvalidFormatException.class, () -> {
      assignmentService.processInput("-name abc -price 12 -quantity 2 -quantity 2 abc -type raw"); });

    String expectedMessage = "Invalid -quantity";
    String actualMessage = exception.getMessage();

    System.out.println(actualMessage);
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  @DisplayName("check type appear once")
  void typeAppearOnlyOneTime() {
    Exception exception = assertThrows(InvalidFormatException.class, () -> {
      assignmentService.processInput("-name abc -price 12 -quantity 2 abc -type raw -type raw"); });

    String expectedMessage = "Invalid -type";
    String actualMessage = exception.getMessage();

    System.out.println(actualMessage);
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  @DisplayName("check name comes first")
  void checkNameComesFirst() {
    Exception exception = assertThrows(InvalidFormatException.class, () -> {
      assignmentService.processInput("-type raw -name abc -price 10 -quantity 1"); });

    String expectedMessage = "Invalid -name";
    String actualMessage = exception.getMessage();

    System.out.println(actualMessage);
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  @DisplayName("check type value is among (raw/imported/manufactured")
  void checkTypeIsValid() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      assignmentService.processInput("-name abc -price 12 -quantity 2 -type xyz"); });

    String expectedMessage = "Invalid type";
    String actualMessage = exception.getMessage();

    System.out.println(actualMessage);
    assertTrue(actualMessage.contains(expectedMessage));
  }

  // Tax Tests

  @Test
  void checkRawTaxInputIsValid() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      rawModel.calcTax(-1); });

    String expectedMessage = "Total cant be -ve";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void checkManufacturedTaxInputIsValid() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      manufacturedModel.calcTax(-1); });

    String expectedMessage = "Total cant be -ve";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void checkImportedTaxInputIsValid() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      importedModel.calcTax(-1); });

    String expectedMessage = "Total cant be -ve";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  @DisplayName("check when position is invalid")
  void checkExtractWordEdgeCases() {
//    AssignmentService service = new AssignmentService();
    Exception exception = assertThrows(InvalidException.class, () -> {
      extractWord("-name abc -type imported -quantity 2 -price 10", -1); });

    String expectedMessage = "Invalid input";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void rawTaxWhenTotalIs34() {
    try {
      assertEquals(4.25, rawModel.calcTax(34));
    } catch (InvalidException e) { assert(false); }
  }

  @Test
  void manufacturedTaxWhenTotalIs25() {
    try {
      assertEquals(3.6875, manufacturedModel.calcTax(25));
    } catch (InvalidException e) { assert(false); }
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

  @Test
  void inputValidityCheck() {
    try{
     isValidFormat("-name abc -price 10.2 -quantity 2 -type raw");
    }
    catch(Exception e) {assert(false);}
    }

  @Test
  void checkExtractData() {
    rawModel.setName("abc");
    rawModel.setPrice(10.0);
    rawModel.setQuantity(2);

    ItemModel model1 = null;
    try {
      model1 = assignmentService.processInput("-name abc -type raw -quantity 2 -price 10");
    } catch (InvalidException e) { assert(false); }
    assertEquals(rawModel.getName(), model1.getName());
    assertEquals(rawModel.getPrice(), model1.getPrice());
    assertEquals(rawModel.getQuantity(), model1.getQuantity());
  }
  @Test
  void checkCalculateCostTypeRaw() {
    ItemModel model1 = null;
    try {
      model1 = assignmentService.processInput(
            "-name abc -type raw -quantity 2 -price 10");
    } catch (InvalidException e) { assert(false); }
    assertEquals(22.5, model1.getTotal());
  }

  @Test
  void checkCalculateCostTypeManufactured() {
    ItemModel model1 = null;
    try {
      model1 = assignmentService.processInput(
          "-name abc -type manufactured -quantity 2 -price 10");
    } catch (InvalidException e) { assert(false); }
    assertEquals(22.95, model1.getTotal());
  }

  @Test
  void checkCalculateCostTypeImported() {
    ItemModel model1 = null;
    try {
      model1 = assignmentService.processInput(
          "-name abc -type imported -quantity 2 -price 10");
    } catch (InvalidException e) { assert(false); }
    assertEquals(27, model1.getTotal());
  }

  @Test
  void processInputInvalid() {
    ItemModel model1 = null;
    try {
      model1 = assignmentService.processInput(
          "-name abc -type imported -quantity -2 -price 10");
    } catch (InvalidException e) {
      String expectedMessage = "Invalid quantity";
      String actualMessage = e.getMessage();
      assertTrue(actualMessage.contains(expectedMessage)); }
  }

  @Test
  void processInputValid() {
    ItemModel model1 = null;
    try {
      model1 = assignmentService.processInput(
          "-name abc -type imported -quantity 2 -price 10");
    } catch (InvalidException e) { assert false;}
    assertEquals(27, model1.getTotal());
  }
}