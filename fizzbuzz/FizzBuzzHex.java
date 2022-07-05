public class FizzBuzzHex {
    public static void main(String[] args) {
        for (int i = 1; i <= 10000; i++) {
            String str = Integer.toHexString(i);
            str += " ";
            
            if (str.startsWith("f")) {
                str += "Fizz";
            }
            if (i % 16 == 11) {
                str += "Buzz";
            }
            if (str.startsWith("f") && i % 16 == 11) {
                str += "!!";
            }
            System.out.println(str);
        }
    }
}
