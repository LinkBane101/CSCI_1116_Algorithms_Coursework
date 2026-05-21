import java.util.Scanner;

public class Reverse {

    public static void reverseDisplay(String value) {

        if (value.length() <= 1) {
            System.out.print(value);
        }
        else {

            System.out.print(value.charAt(value.length() - 1));

            reverseDisplay(value.substring(0, value.length() - 1));
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter a string: ");

        String text = input.nextLine();

        System.out.println("Reversed string: ");

        reverseDisplay(text);

        input.close();
    }
}
