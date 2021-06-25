package assignment4.controller;

import assignment4.view.View;
import assignment4.service.ReadServiceImpl;
import assignment4.service.WriteServiceImpl;

public class Controller {

  public static void main(String[] args) {
    try {
      final ReadServiceImpl readServiceImpl = new ReadServiceImpl();
      final WriteServiceImpl writeServiceImpl = new WriteServiceImpl();
      final View view = new View();
      readServiceImpl.start();
      writeServiceImpl.start();
      view.start();
    } catch (Exception e) {
      e.getMessage();
    }
  }
}
