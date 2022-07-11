import Controller.Controller;

public class App {
    public static void main(String[] args) throws Exception {
        final var console = System.console();
        final var controller = new Controller();

        while (true) {
            String input = console.readLine();
            String result = controller.control(input);
            if (result == null) {
                System.exit(0);
            }
            System.out.println(result);
        }
    }
}
