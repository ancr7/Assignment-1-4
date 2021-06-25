package assignment1.exceptions;

import assignment1.constants.Constant;

public class InvalidFormatException extends InvalidException {

  public InvalidFormatException(final String errorMessage) {
    super(errorMessage + Constant.SPACE + Constant.FORMAT);
  }

}
