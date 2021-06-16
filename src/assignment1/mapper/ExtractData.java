package assignment1.mapper;

import assignment1.exceptions.InvalidException;

public class ExtractData {
  public static String extractWord(String inputString, int pos) throws InvalidException {
    if (inputString.isEmpty() || pos < 0 || pos >= inputString.length())
      throw new InvalidException("Invalid input");
    StringBuilder name = new StringBuilder();
    while (pos < inputString.length() && inputString.charAt(pos) != '-') {
      name.append(inputString.charAt(pos++));
    }
    return name.toString();
  }
}
