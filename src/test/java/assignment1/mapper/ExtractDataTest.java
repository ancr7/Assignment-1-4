package assignment1.mapper;

import assignment1.exceptions.InvalidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExtractDataTest {

    @Test
    @DisplayName("check when position is invalid")
    void checkExtractWordEdgeCases() {
        Exception exception = assertThrows(InvalidException.class, () -> {
            ExtractData extractData = new ExtractData();
            extractData.extractWord("-name abc -type imported -quantity 2 -price 10", -1); });

        String expectedMessage = "Invalid input";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    @DisplayName("check when extracted data")
    void checkExtractWord() {
        try{
           ExtractData extractData = new ExtractData();
           String name = extractData.extractWord("-name abc -type imported -quantity 2 -price 10", 5);
           assertEquals("abc",name);
        }catch (Exception exception) {
            assert false;
        }
    }

}
