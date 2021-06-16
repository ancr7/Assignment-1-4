package assignment1.utils;

import assignment1.exceptions.InvalidException;
import assignment1.exceptions.InvalidFormatException;
import assignment1.exceptions.InvalidInputException;

public class ValidatorUtil {

  public static void isValidFormat(String inputString) throws InvalidException {

    if(inputString.isEmpty()) {
      throw new InvalidFormatException("Invalid input");
    }

    // input can have only 1 name argument
    if (inputString.split("-name", -1).length - 1 != 1) {
      throw new InvalidFormatException("Invalid -name");
    }

    // input can have only 1 price argument
    if (inputString.split("-price", -1).length - 1 != 1) {
      throw new InvalidFormatException("Invalid -price");
    }

    // input can have only 1 quantity argument
    if (inputString.split("-quantity", -1).length - 1 != 1) {
      throw new InvalidFormatException("Invalid -quantity");
    }

    // input can have only 1 type argument
    if (inputString.split("-type", -1).length - 1 != 1) {
      throw new InvalidFormatException("Invalid -type");
    }

    // input should begin with name argument
    if (inputString.indexOf("-name") != 0) {
      throw new InvalidFormatException("Invalid -name");
    }
  }

  // validate input method
  public static void isInputValid(String name, String quantity, String price, String type)
      throws InvalidException {
    // check name empty
    if (name.isEmpty() || !name.matches("^[ A-Za-z]+$")) {
      throw new InvalidInputException("Invalid name");
    }
    // check price empty price should only have numbers and can have decimal (.) once.
    if (price.isEmpty() || !price.matches("[0-9.]*")
        || price.length() - price.replace(".", "").length() > 1) {
      throw new InvalidInputException("Invalid price");
    }
    // check quantity empty quantity should only have numbers.
    if (quantity.isEmpty() || !quantity.matches("[0-9]*") || Integer.parseInt(quantity) <= 0) {
      throw new InvalidInputException("Invalid quantity");
    }
    // check type empty
    if (type.isEmpty()) {
      throw new InvalidInputException("Empty type");
    }
    switch (type) {
      case "RAW":
      case "IMPORTED":
      case "MANUFACTURED":
        break;
      default:
        throw new InvalidInputException("Invalid type");
    }
  }
}
