public class App {
    public static void main(String[] args) throws Exception {
        final LinkedList<String> newList = new LinkedList<>();

        newList.add("first");
        newList.add("second");
        newList.add("third");
        newList.add("forth");
        newList.remove();
        newList.remove();
        newList.add("first");

        final BinaryTreeMap<String, String> newMap = new BinaryTreeMap<>();
        newMap.put("one", "first");
        newMap.put("zero", "second");
        newMap.put("two", "third");
        var value4 = newMap.put("two", "not third");

        var value = newMap.get("zero");
        var value2 = newMap.get("one");
        var value3 = newMap.get("two");
        System.out.println(value);
        System.out.println(value2);
        System.out.println(value3);
        System.out.println(value4);

        // var values = newMap.values();
        // values.add("key", "value");

        // var count = values.size();
        // System.out.println(count);

        // var value = newMap.get("key");
        // System.out.println(value);
        

        // String str = newList.get(1);
        // System.out.println(str);

        for (var it = newList.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
    }
}
