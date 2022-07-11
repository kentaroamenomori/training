package Model;

import java.math.BigDecimal;
import java.math.MathContext;

public class Calc implements ModelInterface {
    public static final String COMMAND = "calc";
    /**
     * 入力値から四則演算の演算子を検出し、その前後の数字の演算結果を返す。
     * 三つ以上の数値の演算はできない。
     * @param input 数値、演算子、数値の順で並んだ文字列。
     * @return 計算結果を文字列として返す。数値がない・複数の演算子があるなどの場合はエラー文を返す。
     */
    @Override
    public String execute(String input) {
        int index;
        SplitStringIntoBigDecimals numbers;
        BigDecimal result;

        try {
            if (input.contains("+")) {
                index = input.indexOf("+");
                numbers = new SplitStringIntoBigDecimals(input, index);
                result = numbers.add();

            } else if (input.contains("-")) {
                index = input.indexOf("-");
                numbers = new SplitStringIntoBigDecimals(input, index);
                result = numbers.subtract();

            } else if (input.contains("*")) {
                index = input.indexOf("*");
                numbers = new SplitStringIntoBigDecimals(input, index);
                result = numbers.multiply();

            } else if (input.contains("/")) {
                index = input.indexOf("/");
                numbers = new SplitStringIntoBigDecimals(input, index);
                result = numbers.divide();

            } else {
                return "error occured";
            }
        } catch (NumberFormatException e) {
            return "not numbers";
        }

        return result.toString();
    }

    private static class SplitStringIntoBigDecimals {
        final BigDecimal firstNumber;
        final BigDecimal secondNumber;

        /**
         * 文字列をBigDecimal二つに分解し格納する
         * @param str 数字二つを記載した文字列
         * @param index 指定したインデックスを基準に文字列を分解する。インデックスの文字自体は数字に含まれない。
         * @throws NumberFormatException 分解後に数字以外が含まれていた場合は例外を投げる
         */
        SplitStringIntoBigDecimals(String str, int index) throws NumberFormatException {
            try {
                firstNumber = new BigDecimal(str.substring(0, index).trim());
                secondNumber = new BigDecimal(str.substring(index + 1).trim());
            } catch (NumberFormatException e) {
                throw e;
            }
        }

        BigDecimal add() {
            return firstNumber.add(secondNumber, MathContext.DECIMAL128);
        }

        BigDecimal subtract() {
            return firstNumber.subtract(secondNumber, MathContext.DECIMAL128);
        }

        BigDecimal multiply() {
            return firstNumber.multiply(secondNumber, MathContext.DECIMAL128);
        }

        BigDecimal divide() {
            return firstNumber.divide(secondNumber, MathContext.DECIMAL128);
        }
    }
}
