package assignment1.models;

import assignment1.exceptions.InvalidException;
import assignment1.exceptions.InvalidInputException;

public class ManufacturedModel extends ItemModel {

  // method to calculate manufactured tax.
  public double calcTax(double total)  throws InvalidException {
    if (total < 0) throw new InvalidInputException("Total cant be -ve");
    double tax = 0;
    tax += total * 12.5 / 100;
    tax += (total + tax) * 2.0 / 100;
    return tax;
  }
}
