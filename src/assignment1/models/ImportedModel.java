package assignment1.models;

import assignment1.exceptions.InvalidException;
import assignment1.exceptions.InvalidInputException;

public class ImportedModel extends ItemModel {

  // method to calculate imported tax.
  public double calcTax(double total)  throws InvalidException {
    if (total < 0) throw new InvalidInputException("Total cant be -ve");
    double tax = total * 0.1;
    if (total <= 100) tax += 5;
    else if (total <= 200) tax += 10;
    else tax += (total + tax) * 0.05;
    return tax;
  }
}
