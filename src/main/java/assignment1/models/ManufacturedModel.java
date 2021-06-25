package assignment1.models;

import assignment1.constants.Constant;
import assignment1.exceptions.InvalidException;
import assignment1.exceptions.InvalidInputException;

public class ManufacturedModel extends ItemModel {

  // method to calculate manufactured tax.
  public double calcTax(final double total) throws InvalidException {
    if (total < Constant.ZERO) throw new InvalidInputException(Constant.TOTAL_CANNOT_BE_NEGATIVE);
    double tax = Constant.ZERO;
    tax += total * Constant.ONE_TWENTY_FIVE / (Constant.TEN * Constant.ONE_HUNDRED);
    tax += (total + tax) * Constant.TWO / Constant.ONE_HUNDRED;
    return tax;
  }
}
