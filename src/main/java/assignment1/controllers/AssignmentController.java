package assignment1.controllers;

import assignment1.constants.Constant;
import assignment1.exceptions.InvalidException;
import assignment1.models.ItemModel;
import assignment1.services.AssignmentService;
import java.util.Scanner;

public class AssignmentController {

  public static void main(String args[]) {
    System.out.println(Constant.ENTER_NAME_IN_GIVEN_FORMAT);
    System.out.println(Constant.NAME_FORMAT);

    // 1. read data from the console
    final Scanner input = new Scanner(System.in);
    String inputString = input.nextLine();
    inputString = inputString.trim();

    // 2. call service method to process the input (service.processInput();)
    final AssignmentService service = new AssignmentService();
    try {
      ItemModel model = service.processInput(inputString);
      // 3. print output in the console return from service method
      System.out.println(model.getTotal());
    }catch (InvalidException e)
    {
      System.out.println(e.getMessage());
    }
  }
}
