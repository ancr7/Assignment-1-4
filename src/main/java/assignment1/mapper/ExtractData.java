package assignment1.mapper;

import assignment1.constants.Constant;
import assignment1.exceptions.InvalidException;

public class ExtractData {

  public String extractWord(final String inputString, int pos) throws InvalidException {
    if (inputString.isEmpty() || pos < Constant.ZERO || pos >= inputString.length())
      throw new InvalidException(Constant.INVALID_INPUT);
    StringBuilder name = new StringBuilder();
    while (pos < inputString.length() && inputString.charAt(pos) != Constant.DELIMETER) {
      name.append(inputString.charAt(pos++));
    }
    return name.toString().trim();
  }
}
