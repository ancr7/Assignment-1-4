package assignment1.factory;

import static assignment1.constants.Constant.IMPORTED;
import static assignment1.constants.Constant.MANUFACTURED;
import static assignment1.constants.Constant.RAW;

import assignment1.models.ImportedModel;
import assignment1.models.ItemModel;
import assignment1.models.ManufacturedModel;
import assignment1.models.RawModel;

public class GetModelFactory {

  public static ItemModel getModel(String type) {
    if (type == null) {
      return null;
    }
    switch (type) {
      case RAW:
        return new RawModel();
      case IMPORTED:
        return new ImportedModel();
      case MANUFACTURED:
        return new ManufacturedModel();
    }
    return null;
  }
}
