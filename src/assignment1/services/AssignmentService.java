package assignment1.services;

import static assignment1.utils.ParserUtil.extractData;

import assignment1.exceptions.InvalidException;
import assignment1.models.ItemModel;
import assignment1.utils.ValidatorUtil;

public class AssignmentService {


  public AssignmentService() {}


  public ItemModel processInput(final String inputString) throws InvalidException {

    // 1. validate input
    ValidatorUtil.isValidFormat(inputString);

    // 2. if input is valid, extract input then store into model class
    ItemModel model = extractData(inputString);

    // 3. calculate tax and return final object to controller
    model.calculateCost();
    return model;
  }
}
