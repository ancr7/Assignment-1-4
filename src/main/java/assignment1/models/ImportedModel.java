package assignment1.models;

import assignment1.constants.Constant;
import assignment1.exceptions.InvalidException;
import assignment1.exceptions.InvalidInputException;

public class ImportedModel extends ItemModel {

  // method to calculate imported tax.
  public double calcTax(final double total) throws InvalidException {
    if (total < Constant.ZERO) throw new InvalidInputException(Constant.TOTAL_CANNOT_BE_NEGATIVE);
    double tax = total * Constant.TEN / Constant.ONE_HUNDRED;
    if (total <= Constant.ONE_HUNDRED) tax += Constant.FIVE;
    else if (total <= Constant.TWO_HUNDRED) tax += Constant.TEN;
    else tax += (total + tax) * Constant.FIVE / Constant.ONE_HUNDRED;
    return tax;
  }
}
