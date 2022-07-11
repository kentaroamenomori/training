import Controller.Controller;

public class App {
    public static void main(String[] args) throws Exception {
        final var console = System.console();
        final var controller = new Controller();

        while (true) {
            String input = console.readLine();
            var result = controller.control(input);
            result.ifPresentOrElse(
                System.out::println, 
                () -> System.exit(0));
        }
    }
}
