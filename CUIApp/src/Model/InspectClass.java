package Model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
        Object instance;
        try {
            clazz = Class.forName(input);
            instance = clazz.getConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException e) {
            return "the class doesn't have a default constructor";
        } catch (ClassNotFoundException e) {
            return "class not found";
        } catch (IllegalAccessException e) {
            return "failed to access the constructor";
        } catch (InvocationTargetException e) {
            return "failed to create an instacne of the specified class";
        }

        var fieldResult = "fields:\n";
        var methodResult = "methods:\n";
        while (clazz != null) {
            // クラスがデフォルトコンストラクタをもたない場合は処理をスキップ
            try {
                clazz.getConstructor();
            } catch (NoSuchMethodException e) {
                clazz = clazz.getSuperclass();
                continue;
            }

            var fields = Arrays.asList(clazz.getDeclaredFields());
            var methods = Arrays.asList(clazz.getDeclaredMethods());

            // fields
            for (var field : fields) {
                fieldResult += createFieldString(clazz, instance, field);
                fieldResult += "\n";
            }

            // methods
            for (var method : methods) {
                methodResult += createMethodString(clazz, method);
                methodResult += "\n";
            }

            // 親クラスの走査の用意
            clazz = clazz.getSuperclass();
        }
        
        return fieldResult + "\n" + methodResult;
    }

    /**
     * 指定したクラス、フィールドとその初期値をつなげた文字列を返す。
     * @param clazz
     * @param instance フィールド初期値の取得にクラスのインスタンスが必要となる
     * @param field
     * @return クラス名: フィールド = 初期値
     */
    private String createFieldString(Class<?> clazz, Object instance, Field field) {
        var result = "";
        result += clazz.getName() + ": " + field.toString();

        try {
            field.setAccessible(true);

            // fieldの初期値を取得する
            var value = " = " + field.get(instance).toString();
            result += value;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 指定したクラス名とメソッドを繋げた文字列を返す
     * @param clazz
     * @param method
     * @return クラス名: メソッド
     */
    private String createMethodString(Class<?> clazz, Method method) {
        return clazz.getName() + ": " + method.toString();
    }
}
