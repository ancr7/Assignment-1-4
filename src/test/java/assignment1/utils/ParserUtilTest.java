package assignment1.utils;

import assignment1.constants.Constant;
import assignment1.exceptions.InvalidException;
import assignment1.factory.GetModelFactory;
import assignment1.mapper.ExtractData;
import assignment1.models.ItemModel;
import assignment1.models.RawModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ParserUtilTest {
    static ValidatorUtil validatorUtil = mock(ValidatorUtil.class);
    static ExtractData extractData = mock(ExtractData.class);
    static GetModelFactory getModelFactory  = mock(GetModelFactory.class);

    static ParserUtil parserUtil;
    @BeforeAll
    public static void setUp()
    {
        parserUtil = new ParserUtil(validatorUtil,extractData,getModelFactory);
    }

    @Test
  void checkExtractData() {
    try {
            ItemModel rawModel = new RawModel();
            rawModel.setName("abc");
            rawModel.setPrice(10.0);
            rawModel.setQuantity(2);


            final String input = "-name abc -type raw -quantity 2 -price 10";

            doNothing().when(validatorUtil).isInputValid("abc","2","10","RAW");
            when(getModelFactory.getModel(Constant.RAW)).thenReturn(new RawModel());

            when(extractData.extractWord(input,5)).thenReturn("abc");
            when(extractData.extractWord(input,15)).thenReturn("raw");
            when(extractData.extractWord(input,29)).thenReturn("2");
            when(extractData.extractWord(input,38)).thenReturn("10");

            ItemModel model1 = parserUtil.extractData(input);

            assertEquals(rawModel.getName(), model1.getName());
            assertEquals(rawModel.getPrice(), model1.getPrice());
            assertEquals(rawModel.getQuantity(), model1.getQuantity());

        } catch (InvalidException e) { assert(false); }
    }

    @Test
    void testParserUtilConstructors() {
        try {
            ParserUtil parserUtilTestObject = new ParserUtil();
        }catch(Exception e){assert false;}
    }
    @Test
    void testParserUtilNameError() {
        try {
            final String input = "-name 123 -type raw -quantity 2 -price 10";

            doNothing().when(validatorUtil).isInputValid("123","raw","2","10");
            when(getModelFactory.getModel(Constant.RAW)).thenReturn(new RawModel());

            when(extractData.extractWord(input,5)).thenThrow(new InvalidException("Invalid input"));
            when(extractData.extractWord(input,15)).thenReturn("raw");
            when(extractData.extractWord(input,29)).thenReturn("2");
            when(extractData.extractWord(input,38)).thenReturn("10");

            ItemModel model1 = parserUtil.extractData(input);

        }catch(Exception e){
            String message = e.getMessage();
            assertEquals("Invalid input -name format",message);}
    }

    @Test
    void testParserUtilTypeError() {
        try {
            final String input = "-name abc -type 111 -quantity 2 -price 10";

            doNothing().when(validatorUtil).isInputValid("abc","2","10","1");
            when(getModelFactory.getModel(Constant.RAW)).thenReturn(new RawModel());

            when(extractData.extractWord(input,5)).thenReturn("abc");
            when(extractData.extractWord(input,15)).thenThrow(new InvalidException("Invalid input"));
            when(extractData.extractWord(input,29)).thenReturn("2");
            when(extractData.extractWord(input,38)).thenReturn("10");

            ItemModel model1 = parserUtil.extractData(input);

        }catch(Exception e){
            String message = e.getMessage();
            assertEquals("Invalid input -type format",message);}
    }
    @Test
    void testParserUtilQuantityError() {
        try {
            final String input = "-name abc -type raw -quantity a -price 10";

            doNothing().when(validatorUtil).isInputValid("abc","a","10","RAW");
            when(getModelFactory.getModel(Constant.RAW)).thenReturn(new RawModel());

            when(extractData.extractWord(input,5)).thenReturn("abc");
            when(extractData.extractWord(input,15)).thenReturn("raw");
            when(extractData.extractWord(input,29)).thenThrow(new InvalidException("Invalid input"));
            when(extractData.extractWord(input,38)).thenReturn("10");

            ItemModel model1 = parserUtil.extractData(input);

        }catch(Exception e){
            String message = e.getMessage();
            assertEquals("Invalid input -quantity format",message);}
    }
    @Test
    void testParserUtilPriceError() {
        try {
            final String input = "-name abc -type raw -quantity 2 -price aa";

            doNothing().when(validatorUtil).isInputValid("abc","2","a","RAW");
            when(getModelFactory.getModel(Constant.RAW)).thenReturn(new RawModel());

            when(extractData.extractWord(input,5)).thenReturn("abc");
            when(extractData.extractWord(input,15)).thenReturn("raw");
            when(extractData.extractWord(input,29)).thenReturn("2");
            when(extractData.extractWord(input,38)).thenThrow(new InvalidException("Invalid input"));

            ItemModel model1 = parserUtil.extractData(input);

        }catch(Exception e){
            String message = e.getMessage();
            assertEquals("Invalid input -price format",message);}
    }

}
