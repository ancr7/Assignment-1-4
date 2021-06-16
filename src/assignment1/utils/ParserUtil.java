package assignment1.utils;

import static assignment1.mapper.ExtractData.extractWord;

import assignment1.exceptions.InvalidException;
import assignment1.exceptions.InvalidFormatException;
import assignment1.factory.GetModelFactory;
import assignment1.models.ItemModel;

public class ParserUtil {

  // extract input method
  public static ItemModel extractData(String inputString) throws InvalidException {
    ItemModel model;
    String name, quantity, price, type;
    try {
      name = extractWord(inputString, inputString.indexOf("-name") + 5).trim();
    } catch (InvalidException e) {
      throw new InvalidFormatException(e.getMessage() + " -name");
    }
    try {
      quantity = extractWord(inputString, inputString.indexOf("-quantity") + 9).trim();
    } catch (InvalidException e) {
      throw new InvalidFormatException(e.getMessage() + " -quantity");
    }
    try {
      price = extractWord(inputString, inputString.indexOf("-price") + 6).trim();
    } catch (InvalidException e) {
      throw new InvalidFormatException(e.getMessage() + " -price");
    }
    try {
      type = extractWord(inputString, inputString.indexOf("-type") + 5).trim().toUpperCase();
    } catch (InvalidException e) {
      throw new InvalidFormatException(e.getMessage() + " -type");
    }

    ValidatorUtil.isInputValid(name, quantity, price, type);

    //    model.setType(ItemModel.types.valueOf(type.toUpperCase()));
    model = GetModelFactory.getModel(type);
    // store data into model
    model.setName(name);
    model.setPrice(Double.parseDouble(price));
    model.setQuantity(Integer.parseInt(quantity));
    return model;
  }
}