package assignment1.utils;

import assignment1.exceptions.InvalidException;
import assignment1.exceptions.InvalidFormatException;
import assignment1.exceptions.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorUtilTest {
    ValidatorUtil validatorUtil = new ValidatorUtil();
    @Test
  @DisplayName("When input is Empty")
  void checkIfInputEmpty() {

    Exception exception = assertThrows(InvalidFormatException.class, () -> {
        validatorUtil.isValidFormat(""); });

    String expectedMessage = "Invalid input format";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
  @Test
  void processInputInvalid() {
    try {
      validatorUtil.isInputValid(
           "abc"  ,"-2","10","imported");
    } catch (InvalidException e) {
      String expectedMessage = "Invalid quantity";
      String actualMessage = e.getMessage();
      assertTrue(actualMessage.contains(expectedMessage)); }
  }
  @Test
  void inputValidityCheck() {
    try{
     validatorUtil.isValidFormat("-name abc -price 10.2 -quantity 2 -type manufactured");
    }
    catch(Exception e) {assert(false);}
   }
  @Test
  void testIsInputValidWhenInputIsValid() {
  try{
      validatorUtil.isInputValid("abc","2", "10","MANUFACTURED");
    }
    catch(Exception e) {assert(false);}
  }

  @Test
  @DisplayName("check type value is among (raw/imported/manufactured")
  void checkTypeIsValid() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      validatorUtil.isInputValid("abc", "12" , "2", "xyz"); });

    String expectedMessage = "Invalid type";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }
  @Test
  @DisplayName("check name comes first")
  void checkNameComesFirst() {
    Exception exception = assertThrows(InvalidFormatException.class, () -> {
      validatorUtil.isValidFormat("-type raw -name abc -price 10 -quantity 1"); });

    String expectedMessage = "Invalid -name";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
    @Test
  @DisplayName("check type appear once")
  void typeAppearOnlyOneTime() {
    Exception exception = assertThrows(InvalidFormatException.class, () -> {
      validatorUtil.isValidFormat("-name abc -price 12 -quantity 2 abc -type raw -type raw"); });

    String expectedMessage = "Invalid -type";
    String actualMessage = exception.getMessage();

    System.out.println(actualMessage);
    assertTrue(actualMessage.contains(expectedMessage));
  }
  @Test
  @DisplayName("check quantity appear once")
  void quantityAppearOnlyOneTime() {
    Exception exception = assertThrows(InvalidFormatException.class, () -> {
      validatorUtil.isValidFormat("-name abc -price 12 -quantity 2 -quantity 2 abc -type raw"); });

    String expectedMessage = "Invalid -quantity";
    String actualMessage = exception.getMessage();

    System.out.println(actualMessage);
    assertTrue(actualMessage.contains(expectedMessage));
  }
  @Test
  @DisplayName("check price appear once")
  void priceAppearOnlyOneTime() {
    Exception exception = assertThrows(InvalidFormatException.class, () -> {
      validatorUtil.isValidFormat("-name abc -price 12 -price 12 -quantity 2 abc -type raw"); });

    String expectedMessage = "Invalid -price";
    String actualMessage = exception.getMessage();

    System.out.println(actualMessage);
    assertTrue(actualMessage.contains(expectedMessage));
  }
  @Test
  @DisplayName("check name appear once")
  void nameAppearOnlyOneTime() {
    Exception exception = assertThrows(InvalidFormatException.class, () -> {
      validatorUtil.isValidFormat("-name abc -name def -price 12 -quantity 2 abc -type raw"); });

    String expectedMessage = "Invalid -name";
    String actualMessage = exception.getMessage();

    System.out.println(actualMessage);
    assertTrue(actualMessage.contains(expectedMessage));
  }
  @Test
  @DisplayName("check Empty Type")
  void checkEmptyType() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      validatorUtil.isInputValid("abc","2","12", ""); });

    String expectedMessage = "Empty type";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
  @Test
  @DisplayName("check Empty quantity")
  void checkEmptyQuantity() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      validatorUtil.isInputValid("abc" ,"","12" ,"raw"); });

    String expectedMessage = "Invalid quantity";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
    @Test
  @DisplayName("check Empty price")
  void checkEmptyPrice() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      validatorUtil.isInputValid("abc","1",  "","raw"); });

    String expectedMessage = "Invalid price";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
    @Test
  @DisplayName("check Empty name")
  void checkEmptyName() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      validatorUtil.isInputValid("","1","2","raw"); });

    String expectedMessage = "Invalid name";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
    @Test
  @DisplayName("check quantity when -ve")
  void checkQuantityNonNegative() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      validatorUtil.isInputValid("abc"  ,"-1"  ,"20", "raw"); });

    String expectedMessage = "Invalid quantity";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  @DisplayName("check price when -ve")
  void checkPriceNonNegative() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      validatorUtil.isInputValid("abc","1", "-2" ,"raw"); });

    String expectedMessage = "Invalid price";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
  @Test
  @DisplayName("When type format is wrong")
  void checkInputType() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      validatorUtil.isInputValid("abc", "2","10", "35"); });

    String expectedMessage = "Invalid type";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
  @Test
  @DisplayName("When quantity format is wrong")
  void checkInputQuantity() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      validatorUtil.isInputValid("abc" , "F", "10" ,"raw"); });

    String expectedMessage = "Invalid quantity";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
  @Test
  @DisplayName("When price format is wrong")
  void checkInputPrice() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      validatorUtil.isInputValid("abc" , "2", "ww" ,"raw"); });

    String expectedMessage = "Invalid price";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
  @Test
  @DisplayName("When invalid type")
  void checkInputTypeFormat() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      validatorUtil.isInputValid("abc" , "2", "10" ,"ABC"); });

    String expectedMessage = "Invalid type";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }
  @Test
  @DisplayName("When name format is wrong")
  void checkInputName() {
    Exception exception = assertThrows(InvalidInputException.class, () -> {
      validatorUtil.isInputValid("133" , "2", "10" ,"raw"); });

    String expectedMessage = "Invalid name";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
}
