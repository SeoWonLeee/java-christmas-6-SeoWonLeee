package christmas;

public class Application {
    public static void main(String[] args) {
        Menu menu = new Menu();
        OutputView outputView = new OutputView();
        Controller controller = new Controller(menu, outputView);
        controller.run();
    }
}
