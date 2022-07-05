public class FizzBuzz {
    public static void main(String[] args) {
        for (int i = 1; i <= 50; i++) {
            String str = Integer.valueOf(i).toString();
            str += " ";

            if (i % 3 == 0) {
                str += "Fizz";
            }
            if (i % 5 == 0) {
                str += "Buzz";
            }
            if (i % 15 == 0) {
                str += "!!";
            }
            System.out.println(str);
        }
    }
}
