package christmas;

public class Application {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        Controller controller = new Controller(outputView);
        controller.run();
    }
}