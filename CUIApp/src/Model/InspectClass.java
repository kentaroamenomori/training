package Model;

import java.util.Arrays;

public class InspectClass {
    public static final String command = "inspectclass";
    /**
     * 指定したクラス、および先祖クラスのフィールド・メソッドを一覧で出力する。public・privateなど関係なく全ての修飾子のフィールドが出力される。
     * なお出力されるクラスはデフォルトコンストラクタを持つ具象クラスのみに限定される。
     * @param input クラスを完全名で指定する。
     * @return フィールド・メソッド一覧もしくはエラー文を返す。
     */
    public String inspect(String input) {
        Class<?> clazz;
        try {
            clazz = Class.forName(input);
            clazz.getConstructor(); // デフォルトコンストラクタを持っているか判定
        } catch (NoSuchMethodException e) {
            return "the class doesn't have a default constructor";
        } catch (ClassNotFoundException e) {
            return "class not found";
        }

        var fieldList = Arrays.asList(clazz.getDeclaredFields());
        var methodList = Arrays.asList(clazz.getDeclaredMethods());

        while (true) {
            clazz = clazz.getSuperclass();

            // デフォルトコンストラクタがない場合はループを抜ける
            try {
                clazz.getConstructor();
            } catch (Exception e) {
                break;
            }
            fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
            methodList.addAll(Arrays.asList(clazz.getDeclaredMethods()));
        }
        
        // field
        var result = "fields:\n";
        for (var field : fieldList) {
            result += field.toString();
            try {
                field.setAccessible(true);

                // fieldの初期値を取得する
                var value = " = " + field.get(field).toString();
                result += value;
            } catch (Exception e) {}
            result += "\n";
        }

        // method
        result += "\nmethods:\n";
        for (var method : methodList) {
            result += method.toString();
            result += "\n";
        }

        return result;
    }
}
