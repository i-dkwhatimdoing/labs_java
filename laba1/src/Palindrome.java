
public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            // System.out.println("%s - %s", s , reverseString(s));
            System.out.printf("%s isPalindrome(%d)\n", s, (isPalindrome(s)) ? 1 : 0);
        }
    }

    public static boolean isPalindrome(String input) {
        String rev = reverseString(input);
        boolean ans = false;
        // Checking if both the strings are equal
        if (input.equals(rev)) {
            ans = true;
        }
        return ans;
    }

    public static String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}
