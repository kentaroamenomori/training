package Model;

public class StarEcho implements ModelInterface {
    public static final String COMMAND = "starecho";

    @Override
    public String execute(String input) {
        return "*" + input + "*";
    }
}
