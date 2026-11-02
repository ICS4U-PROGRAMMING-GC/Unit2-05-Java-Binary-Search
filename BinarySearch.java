import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * BinarySearch program that generates random arrays, sorts them,
 * and allows the user to search for values using binary search
 * with crash-proof input validation.
 *
 * @author Carel Gumuyire
 * @version 1.0
 */
public final class BinarySearch {

    /**
     * Private constructor to hide implicit public one.
     */
    private BinarySearch() {
    }

    /**
     * Populates an array with 10 random integers between 0 and 100.
     *
     * @return an array of 10 random integers
     */
    public static int[] populateArray() {
        final Random random = new Random();
        final int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(101);
        }
        return array;
    }

    /**
     * Performs a binary search on a sorted array to find a target number.
     *
     * @param array the sorted array of numbers to search through
     * @param number the target number to find
     * @return the index of the number if found, or -1 if not found
     */
    public static int binarySearch(final int[] array, final int number) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            final int mid = (low + high) / 2;

            if (array[mid] == number) {
                return mid;
            } else if (array[mid] < number) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Main method running the interactive loop and handling user input.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        boolean running = true;

        do {
            final int[] array = populateArray();
            Arrays.sort(array);

            System.out.println("\nWhat number are you searching for in the "
                    + "list below? Enter \"q\" to quit.");
            for (int num : array) {
                System.out.print(num + " ");
            }

            System.out.print(" Number: ");
            String input = scanner.next();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("Thank you for playing!");
                running = false;
            } else {
                int number = -1;
                boolean valid = false;

                while (!valid) {
                    try {
                        number = Integer.parseInt(input);
                        valid = true;
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid input. Enter a valid "
                                + "number or \"q\" to quit: ");
                        input = scanner.next();
                        if (input.equalsIgnoreCase("q")) {
                            System.out.println("Thank you for playing!");
                            running = false;
                            break;
                        }
                    }
                }

                if (running) {
                    final int index = binarySearch(array, number);

                    if (index != -1) {
                        System.out.println("The number " + number
                                + " is found at index " + index + ".");
                    } else {
                        System.out.println("The number " + number
                                + " is not found in the list of numbers.");
                    }
                }
            }

        } while (running);

        scanner.close();
    }
}
