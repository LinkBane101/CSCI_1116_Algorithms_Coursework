import java.util.Scanner;

public class substringOrder {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

    while (true) {
        System.out.print("Enter a string: ");
        if (!input.hasNextLine()) {
            break;
        }
        String text = input.nextLine();

        if (text.length() == 0) {
            System.out.println("Are you trying to break my code?");
            System.out.println("Please don't");
            input.close();
            return;
        }

        int currentStart = 0;
        int maxStart = 0;
        int maxLength = 1;

        //(O)n. Linear function
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) <= text.charAt(i - 1)) {
                currentStart = i;
            }

            int currentLength = i - currentStart + 1;

            if (currentLength > maxLength) {
                maxLength = currentLength;
                maxStart = currentStart;
            }
        }

        String result = text.substring(maxStart, maxStart + maxLength);

        System.out.println("Maximum consecutive substring is " + result);
        }
        input.close();
    }
}