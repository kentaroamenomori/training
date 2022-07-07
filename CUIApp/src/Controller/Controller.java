package Controller;

import Model.*;

public class Controller {
    // TODO: インスタンスする？staticにする？
    final StarEcho starEcho = new StarEcho();
    final WorldClock worldClock = new WorldClock();
    final Calc calc = new Calc();
    final BoldKeyword boldKeyword = new BoldKeyword();
    /**
     * 入力コマンドに応じて動作を変更する。qでシステムごと終了する。
     * @param str 入力文字列
     * @return ターミナルに出力する文字列
     */
    public String controll(String str) {
        // qで終了
        if (str.equals("q")) {
            System.exit(0);
        }

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

        switch (command) {
            case StarEcho.command:
                return starEcho.addStars(input);
            case WorldClock.command:
                return worldClock.fetchTimeOf(input);
            case Calc.command:
                return calc.calculate(input);
            case BoldKeyword.command:
                return boldKeyword.bolden(input);
            default:
                return "invalid command";
        }
    }
}
