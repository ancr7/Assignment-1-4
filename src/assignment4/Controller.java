package assignment4;

public class Controller {

  public static void main(String[] args) {
    try {
      ReadServiceImpl readServiceImpl = new ReadServiceImpl();
      WriteServiceImpl writeServiceImpl = new WriteServiceImpl();
      View view = new View();
      readServiceImpl.start();
      writeServiceImpl.start();
      view.start();
    } catch (Exception e) {
      e.getMessage();
    }
  }
}
