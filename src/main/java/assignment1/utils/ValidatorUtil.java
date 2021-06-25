package assignment1.utils;

import assignment1.constants.Constant;
import assignment1.exceptions.InvalidException;
import assignment1.exceptions.InvalidFormatException;
import assignment1.exceptions.InvalidInputException;

public class ValidatorUtil {

  public void isValidFormat(final String inputString) throws InvalidException {

    if (inputString.isEmpty()) {
      throw new InvalidFormatException(Constant.INVALID_INPUT);
    }

    // input can have only 1 name argument
    if (inputString.split(Constant.DELIMETER + Constant.NAME, -Constant.ONE).length - Constant.ONE
        != Constant.ONE) {
      throw new InvalidFormatException(
          Constant.INVALID + Constant.SPACE + Constant.DELIMETER + Constant.NAME);
    }

    // input can have only 1 price argument
    if (inputString.split(Constant.DELIMETER + Constant.PRICE, -Constant.ONE).length - Constant.ONE
        != Constant.ONE) {
      throw new InvalidFormatException(
          Constant.INVALID + Constant.SPACE + Constant.DELIMETER + Constant.PRICE);
    }

    // input can have only 1 quantity argument
    if (inputString.split(Constant.DELIMETER + Constant.QUANTITY, -Constant.ONE).length
        - Constant.ONE != Constant.ONE) {
      throw new InvalidFormatException(
          Constant.INVALID + Constant.SPACE + Constant.DELIMETER + Constant.QUANTITY);
    }

    // input can have only 1 type argument
    if (inputString.split(Constant.DELIMETER + Constant.TYPE, -Constant.ONE).length - Constant.ONE
        != Constant.ONE) {
      throw new InvalidFormatException(
          Constant.INVALID + Constant.SPACE + Constant.DELIMETER + Constant.TYPE);
    }

    // input should begin with name argument
    if (inputString.indexOf(Constant.DELIMETER + Constant.NAME) != Constant.ZERO) {
      throw new InvalidFormatException(
          Constant.INVALID + Constant.SPACE + Constant.DELIMETER + Constant.NAME);
    }
  }
  // validate input method
  public void isInputValid(final String name, final String quantity, final String price,
                                  final String type) throws InvalidException {
    // check name empty
    if (name.isEmpty() || !name.matches(Constant.ALPHABET_REGEX)) {
      throw new InvalidInputException(Constant.INVALID + Constant.SPACE + Constant.NAME);
    }
    // check price empty price should only have numbers and can have decimal (.) once.
    if (price.isEmpty() || !price.matches(Constant.DECIMAL_REGEX)
        || price.length() - price.replace(Constant.DOT, Constant.EMPTY_STRING).length()
        > Constant.ONE) {
      throw new InvalidInputException(Constant.INVALID + Constant.SPACE + Constant.PRICE);
    }
    // check quantity empty quantity should only have numbers.
    if (quantity.isEmpty() || !quantity.matches(Constant.NUMBER_REGEX)
        || Integer.parseInt(quantity) <= Constant.ZERO) {
      throw new InvalidInputException(Constant.INVALID + Constant.SPACE + Constant.QUANTITY);
    }
    // check type empty
    if (type.isEmpty()) {
      throw new InvalidInputException(Constant.EMPTY + Constant.SPACE + Constant.TYPE);
    }
    switch (type) {
      case Constant.RAW:
      case Constant.IMPORTED:
      case Constant.MANUFACTURED: break;
      default: throw new InvalidInputException(Constant.INVALID + Constant.SPACE + Constant.TYPE);
    }
  }
}
