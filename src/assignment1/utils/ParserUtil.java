package assignment1.utils;

import static assignment1.mapper.ExtractData.extractWord;

import assignment1.constants.Constant;
import assignment1.exceptions.InvalidException;
import assignment1.exceptions.InvalidFormatException;
import assignment1.factory.GetModelFactory;
import assignment1.models.ItemModel;

public class ParserUtil {

  // extract input method
  public static ItemModel extractData(final String inputString) throws InvalidException {
    ItemModel model;
    String name, quantity, price, type;
    try {
      name = extractWord(inputString,
          inputString.indexOf(Constant.DELIMETER + Constant.NAME) + Constant.FIVE).trim();
    } catch (InvalidException e) {
      throw new InvalidFormatException(
          e.getMessage() + Constant.SPACE + Constant.DELIMETER + Constant.NAME);
    }
    try {
      quantity = extractWord(inputString,
          inputString.indexOf(Constant.DELIMETER + Constant.QUANTITY) + Constant.NINE).trim();
    } catch (InvalidException e) {
      throw new InvalidFormatException(
          e.getMessage() + Constant.SPACE + Constant.DELIMETER + Constant.QUANTITY);
    }
    try {
      price = extractWord(inputString,
          inputString.indexOf(Constant.DELIMETER + Constant.PRICE) + Constant.SIX).trim();
    } catch (InvalidException e) {
      throw new InvalidFormatException(
          e.getMessage() + Constant.SPACE + Constant.DELIMETER + Constant.PRICE);
    }
    try {
      type = extractWord(inputString,
          inputString.indexOf(Constant.DELIMETER + Constant.TYPE) + Constant.FIVE).trim()
          .toUpperCase();
    } catch (InvalidException e) {
      throw new InvalidFormatException(
          e.getMessage() + Constant.SPACE + Constant.DELIMETER + Constant.TYPE);
    }

    ValidatorUtil.isInputValid(name, quantity, price, type);

    model = GetModelFactory.getModel(type);

    // store data into model
    model.setName(name);
    model.setPrice(Double.parseDouble(price));
    model.setQuantity(Integer.parseInt(quantity));
    return model;
  }
}