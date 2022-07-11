package Controller;

import java.util.Optional;

import Model.*;

public class Controller {
    final StarEcho starEcho = new StarEcho();
    final WorldClock worldClock = new WorldClock();
    final Calc calc = new Calc();
    final BoldKeyword boldKeyword = new BoldKeyword();
    final InspectClass inspectClass = new InspectClass();
    /**
     * 入力コマンドに応じて動作を変更する。qでnullを返す。
     * @param str 入力文字列
     * @return ターミナルに出力する文字列
     */
    public Optional<String> control(String str) {
        int spaceIndex = str.indexOf(" ");

        String command; // スペース以前をコマンドとして格納
        String input; // スペース以降を入力内容として格納

        if (spaceIndex == -1) {
            command = str;
            input = "";
        } else {
            command = str.substring(0, spaceIndex);
            input = str.substring(spaceIndex + 1);
        }

        String result;

        switch (command) {
            case StarEcho.COMMAND:
                result = starEcho.execute(input);
                break;
            case WorldClock.COMMAND:
                result = worldClock.execute(input);
                break;
            case Calc.COMMAND:
                result = calc.execute(input);
                break;
            case BoldKeyword.COMMAND:
                result = boldKeyword.execute(input);
                break;
            case InspectClass.COMMAND:
                result = inspectClass.execute(input);
                break;
            case "q":
                result = null;
                break;
            default:
                result = "invalid command";
        }

        return Optional.ofNullable(result);
    }
}
