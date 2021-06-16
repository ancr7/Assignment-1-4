package assignment1.models;

import assignment1.exceptions.InvalidException;
import assignment1.exceptions.InvalidInputException;

public class RawModel extends ItemModel {
  // method to calculate raw tax.
  public double calcTax(double total) throws InvalidException {
    if (total < 0) throw new InvalidInputException("Total cant be -ve");
    double tax = 0;
    tax += total * 12.50 / 100;
    return tax;
  }
}
