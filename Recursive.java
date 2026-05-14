import java.util.Scanner;

public class Recursive {

    public static int gcd(int m, int n) {

        if (m % n == 0) {
            return n;
        }

        return gcd(n, m % n);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter first integer: ");
        int num1 = input.nextInt();

        System.out.print("Enter second integer: ");
        int num2 = input.nextInt();

        int result = gcd(num1, num2);

        System.out.println("The GCD is: " + result);

        input.close();
    }
}