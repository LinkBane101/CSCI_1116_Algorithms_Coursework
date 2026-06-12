import java.util.Scanner;

public class sub {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a string 1: ");

            if (!input.hasNextLine()) {
                break;
            }

            String text1 = input.nextLine();

            System.out.print("Enter a string 2: ");
            String text2 = input.nextLine();

            if (text2.length() == 0) {
                System.out.println("Did you mean to type in nothing to break my code? Please don't.");
                System.out.println("Either way it technically is a substring");
            }
            else if (isSubstring(text1, text2)) {
                System.out.println(text2 + " is a substring of " + text1);
            }
            else {
                System.out.println(text2 + " is not a substring of " + text1);
            }

            System.out.println();
        }

        input.close();
    }
//O(n+m). In this case n is i and j is m.
    public static boolean isSubstring(String text, String pattern) {
        int[] lps = buildLPS(pattern);

        int i = 0; // index for text
        int j = 0; // index for pattern

        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;

                if (j == pattern.length()) {
                    return true;
                }
            }
            else {
                if (j > 0) {
                    j = lps[j - 1];
                }
                else {
                    i++;
                }
            }
        }
        return false;
    }

    /* KMP algorithm was used for this. Making this part of the code O(m).
    I used m as the variable pattern to carry the substring or the input text2
    */
    public static int[] buildLPS(String pattern) {
        int[] lps = new int[pattern.length()];

        int length = 0;
        int i = 1;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            }
            else {
                if (length > 0) {
                    length = lps[length - 1];
                }
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}