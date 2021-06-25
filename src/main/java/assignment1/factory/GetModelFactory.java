package assignment1.factory;

import static assignment1.constants.Constant.IMPORTED;
import static assignment1.constants.Constant.MANUFACTURED;
import static assignment1.constants.Constant.RAW;

import assignment1.constants.Constant;
import assignment1.exceptions.InvalidException;
import assignment1.exceptions.InvalidInputException;
import assignment1.models.ImportedModel;
import assignment1.models.ItemModel;
import assignment1.models.ManufacturedModel;
import assignment1.models.RawModel;

public class GetModelFactory {

  public ItemModel getModel(final String type) throws InvalidException {
    if (type == null) {
      throw new InvalidInputException(Constant.INVALID+" "+Constant.TYPE);
    }
    switch (type) {
      case RAW:
        return new RawModel();
      case IMPORTED:
        return new ImportedModel();
      case MANUFACTURED:
        return new ManufacturedModel();
    }
    throw new InvalidInputException(Constant.INVALID+" "+Constant.TYPE);
//    return null;
  }
}
