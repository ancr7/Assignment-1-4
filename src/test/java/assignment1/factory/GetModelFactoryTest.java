package assignment1.factory;

import assignment1.exceptions.InvalidException;
import assignment1.models.ImportedModel;
import assignment1.models.ManufacturedModel;
import assignment1.models.RawModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetModelFactoryTest {
    GetModelFactory getModelFactory = new GetModelFactory();
    @Test
    void testRawGetFactory()
    {
        try {
            assertEquals(RawModel.class,getModelFactory.getModel("RAW").getClass());
        } catch (InvalidException e) {
            assert false;
        }
    }
    @Test
    void testImportedGetFactory()
    {
        try {
            assertEquals(ImportedModel.class,getModelFactory.getModel("IMPORTED").getClass());
        } catch (InvalidException e) {
            assert false;
        }
    }
    @Test
    void testManufacturedGetFactory()
    {
        try {
            assertEquals(ManufacturedModel.class,getModelFactory.getModel("MANUFACTURED").getClass());
        } catch (InvalidException e) {
            assert false;
        }
    }
    @Test
    void testNullGetFactory()
    {
        try {
            getModelFactory.getModel(null);
            assert false;
        } catch (InvalidException e) {
            assertEquals("Invalid type",e.getMessage());
        }
    }
    @Test
    void testInvalidTypeGetFactory()
    {
        try {
            getModelFactory.getModel("Invalid Type");
            assert false;
        } catch (InvalidException e) {
            assertEquals("Invalid type",e.getMessage());
        }
    }
}
