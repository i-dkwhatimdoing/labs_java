public class Primes {
    public static void main(String[] args) {
        System.out.println("");
        //int quantity = 0;
        for (int i = 2; i <= 100; i++) {
            if (isPrime(i)) {
                System.out.println(String.format("%d is prime! +", i));
                //quantity++;
            // } else {
            //     System.out.println(String.format("%d is not prime! -", i));
            // }
        }
       // System.out.println(quantity);
        // System.out.println("Strings!");
    }
    }
    //if number prime return 1 else 0
    public static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
        return true;
    }
}
