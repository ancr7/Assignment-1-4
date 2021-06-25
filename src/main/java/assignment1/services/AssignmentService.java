package assignment1.services;

import assignment1.exceptions.InvalidException;
import assignment1.models.ItemModel;
import assignment1.utils.ParserUtil;
import assignment1.utils.ValidatorUtil;

public class AssignmentService {

  ValidatorUtil validatorUtil;
  ParserUtil parserUtil;

  public AssignmentService() {
    validatorUtil = new ValidatorUtil();
    parserUtil    = new ParserUtil();
  }

  public AssignmentService(ValidatorUtil validatorUtil,ParserUtil parserUtil) {
    this.validatorUtil = validatorUtil;
    this.parserUtil = parserUtil;
  }

  public ItemModel processInput(final String inputString) throws InvalidException {

    // 1. validate input
    validatorUtil.isValidFormat(inputString);

    // 2. if input is valid, extract input then store into model class
    ItemModel model = parserUtil.extractData(inputString);

    // 3. calculate tax and return final object to controller
    model.calculateCost();
    return model;
  }
}
