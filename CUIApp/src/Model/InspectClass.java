package Model;

import java.util.Arrays;

public class InspectClass implements ModelInterface {
    public static final String COMMAND = "inspectclass";
    /**
     * 指定したクラス、および先祖クラスのフィールド・メソッドを一覧で出力する。public・privateなど関係なく全ての修飾子のフィールドが出力される。
     * なお出力されるクラスはデフォルトコンストラクタを持つ具象クラスのみに限定される。
     * @param input クラスを完全名で指定する。
     * @return フィールド・メソッド一覧もしくはエラー文を返す。
     */
    @Override
    public String execute(String input) {
        Class<?> clazz;
        try {
            clazz = Class.forName(input);
            clazz.getConstructor(); // デフォルトコンストラクタを持っているか判定
        } catch (NoSuchMethodException e) {
            return "the class doesn't have a default constructor";
        } catch (ClassNotFoundException e) {
            return "class not found";
        }

        var fieldResult = "fields:\n";
        var methodResult = "methods:\n";
        while (true) {
            // clazzのインスタンスを用意する
            Object instance;
            try {
                instance = clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                // デフォルトコンストラクタをもたないクラスの場合はループを抜ける
                e.printStackTrace();
                break;
            }

            var fields = Arrays.asList(clazz.getDeclaredFields());
            var methods = Arrays.asList(clazz.getDeclaredMethods());

            // fields
            for (var field : fields) {
                fieldResult += field.toString();
                try {
                    field.setAccessible(true);
    
                    // fieldの初期値を取得する
                    var value = " = " + field.get(instance).toString();
                    fieldResult += value;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                fieldResult += "\n";
            }

            // methods
            for (var method : methods) {
                methodResult += method.toString();
                methodResult += "\n";
            }

            // 親クラスの走査の用意
            clazz = clazz.getSuperclass();
        }
        
        return fieldResult + "\n" + methodResult;
    }
}
