package assignment1.services;

import assignment1.exceptions.InvalidException;
import assignment1.models.ImportedModel;
import assignment1.models.ItemModel;
import assignment1.models.ManufacturedModel;
import assignment1.models.RawModel;
import assignment1.utils.ParserUtil;
import assignment1.utils.ValidatorUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AssignmentServiceTest {
    static ValidatorUtil validatorUtil = mock(ValidatorUtil.class);
    static ParserUtil parserUtil  = mock(ParserUtil.class);
    static AssignmentService assignmentService;
    @BeforeAll
    public static void setUp()
    {
        assignmentService = new AssignmentService(validatorUtil,parserUtil);
    }
    @Test
    public void testProcessInputRaw()
    {
    try {
            ItemModel inputModel = new RawModel();
            inputModel.setName("milk");
            inputModel.setPrice(20);
            inputModel.setQuantity(2);
            
            final String input = "-name milk -price 20 -quantity 2 -type raw";

            doNothing().when(validatorUtil).isValidFormat(input);
            when(parserUtil.extractData(input)).thenReturn(inputModel);

            ItemModel outputModel = assignmentService.processInput(input);
            assertEquals(45.0,outputModel.getTotal());

        }catch(InvalidException exception) {
            assert false;
        }
    }
    @Test
  void testProcessInputImported() {
        try {
            ItemModel inputModel = new ImportedModel();
            inputModel.setName("abc");
            inputModel.setPrice(10);
            inputModel.setQuantity(2);

            final String input = "-name abc -type imported -quantity 2 -price 10";

            doNothing().when(validatorUtil).isValidFormat(input);
            when(parserUtil.extractData(input)).thenReturn(inputModel);

            ItemModel outputModel = assignmentService.processInput(input);
            assertEquals(27,outputModel.getTotal());

        }catch(InvalidException exception) {
            assert false;
        }
  }

  @Test
  void testProcessInputManufactured() {
      try {
          ItemModel inputModel = new ManufacturedModel();
          inputModel.setName("abc");
          inputModel.setPrice(10);
          inputModel.setQuantity(2);

          final String input = "-name abc -type manufactured -quantity 2 -price 10";

          doNothing().when(validatorUtil).isValidFormat(input);
          when(parserUtil.extractData(input)).thenReturn(inputModel);

          ItemModel outputModel = assignmentService.processInput(input);
          assertEquals(22.95,outputModel.getTotal());

      }catch(InvalidException exception) {
          assert false;
      }
  }
  @Test
  void checkCalculateCostTypeRaw() {
          try {
              ItemModel inputModel = new RawModel();
              inputModel.setName("abc");
              inputModel.setPrice(10);
              inputModel.setQuantity(2);

              final String input = "-name abc -type raw -quantity 2 -price 10";

              doNothing().when(validatorUtil).isValidFormat(input);
              when(parserUtil.extractData(input)).thenReturn(inputModel);

              ItemModel outputModel = assignmentService.processInput(input);
              assertEquals(22.5,outputModel.getTotal());

          }catch(InvalidException exception) {
              assert false;
          }
  }
@Test
    void testAssignmentServiceConstructors()
    {
        try {
            AssignmentService assignmentServiceTestObject = new AssignmentService();
        }catch(Exception e){assert false;}
    }


}
