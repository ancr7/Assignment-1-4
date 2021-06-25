package assignment1.utils;

import assignment1.constants.Constant;
import assignment1.exceptions.InvalidException;
import assignment1.exceptions.InvalidFormatException;
import assignment1.factory.GetModelFactory;
import assignment1.mapper.ExtractData;
import assignment1.models.ItemModel;

public class ParserUtil {
    ValidatorUtil validatorUtil;
    ExtractData extractData;
    GetModelFactory getModelFactory;
    public ParserUtil(){
        validatorUtil  = new ValidatorUtil();
        extractData = new ExtractData();
        getModelFactory = new GetModelFactory();
    }
    public ParserUtil(ValidatorUtil validatorUtil,ExtractData extractData,GetModelFactory getModelFactory){
        this.validatorUtil  = validatorUtil;
        this.extractData = extractData;
        this.getModelFactory = getModelFactory;
    }
    // extract input method
    public ItemModel extractData(final String inputString) throws InvalidException {

        ItemModel model;
        String name, quantity, price, type;
        try {
            name = extractData.extractWord(inputString,
                    inputString.indexOf(Constant.DELIMETER + Constant.NAME) + Constant.FIVE).trim();
        } catch (InvalidException e) {
            throw new InvalidFormatException(
                    e.getMessage() + Constant.SPACE + Constant.DELIMETER + Constant.NAME);
        }
        try {
            quantity = extractData.extractWord(inputString,
                    inputString.indexOf(Constant.DELIMETER + Constant.QUANTITY) + Constant.NINE).trim();
        } catch (InvalidException e) {
            throw new InvalidFormatException(
                    e.getMessage() + Constant.SPACE + Constant.DELIMETER + Constant.QUANTITY);
        }
        try {
            price = extractData.extractWord(inputString,
                    inputString.indexOf(Constant.DELIMETER + Constant.PRICE) + Constant.SIX).trim();
        } catch (InvalidException e) {
            throw new InvalidFormatException(
                    e.getMessage() + Constant.SPACE + Constant.DELIMETER + Constant.PRICE);
        }
        try {
            type = extractData.extractWord(inputString,
                    inputString.indexOf(Constant.DELIMETER + Constant.TYPE) + Constant.FIVE).trim()
                    .toUpperCase();
        } catch (InvalidException e) {
            throw new InvalidFormatException(
                    e.getMessage() + Constant.SPACE + Constant.DELIMETER + Constant.TYPE);
        }

        validatorUtil.isInputValid(name, quantity, price, type);

        model = getModelFactory.getModel(type);

        // store data into model
        model.setName(name);
        model.setPrice(Double.parseDouble(price));
        model.setQuantity(Integer.parseInt(quantity));

        return model;
    }
}