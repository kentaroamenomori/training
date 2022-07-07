import Controller.Controller;

public class App {
    public static void main(String[] args) throws Exception {
        final var console = System.console();
        final var controller = new Controller();

        while (true) {
            String input = console.readLine();
            String result = controller.controll(input);
            System.out.println(result);
        }
    }
}
