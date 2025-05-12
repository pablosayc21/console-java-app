import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;

public class ConsoleApp {

    public ConsoleApp() {
    }

    public void fizzBuzz() {

        for (int iterator = 1; iterator <= 100; iterator++) {

            if (iterator % 3 == 0 && iterator % 5 == 0) {

                System.out.println("FizzBuzz");

            } else if (iterator % 5 == 0) {

                System.out.println("Buzz");

            } else if (iterator % 3 == 0) {

                System.out.println("Fizz");

            } else {

                System.out.println(iterator);

            }

        }
    }

    public boolean isPalindrome(String text) {

        if (text.length() == 0)
            return false;

        text = text.toLowerCase().replaceAll("\\s+", "");

        int endIndex = text.length();

        for (int i = 0; i < text.length() / 2; i++) {

            if (text.charAt(i) != text.charAt(endIndex - 1 - i))
                return false;

        }

        return true;
    }

    public int sumOfUniqueElements(int[] nums) {

        int sum = 0;

        Map<Integer, Integer> numsCounter = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {

            if (!numsCounter.containsKey(nums[i])) {
                numsCounter.put(nums[i], 1);

            } else {

                int actualCount = numsCounter.get(nums[i]);
                numsCounter.replace(nums[i], actualCount + 1);

            }
        }

        Set<Integer> keys = numsCounter.keySet();

        for (Integer key : keys) {

            if (numsCounter.get(key) == 1)
                sum += key;

        }

        return sum;
    }

    public void testPalindrome(Scanner scanner) {

        System.out.println("Select type of testing (1 as default):");
        System.out.println("1. Testcases.");
        System.out.println("2. Enter a string.");

        int test = this.validInputNumber(scanner, " \nSelect an option: ");

        if (test == 2) {

            scanner.nextLine();
            System.out.println("Enter text: ");
            String text = scanner.nextLine();
            System.out.println(text + ": " + this.isPalindrome(text));

        } else {

            System.out.println("Radar: " + this.isPalindrome("Radar"));
            System.out.println("Hello: " + this.isPalindrome("Hello"));
            System.out.println("A man a plan a canal Panama: " + this.isPalindrome("A man a plan a canal Panama"));

        }
    }

    public int[] fillArrayWithNumbers(Scanner scanner) {

        int[] numbers = new int[5]; // 5 elements by default

        for (int i = 0; i < 5;) {

            System.out.print("Enter an element: ");

            if (scanner.hasNextInt()) {

                numbers[i] = scanner.nextInt();
                i++;

            } else {

                System.out.println("Invalid input. Enter a number.");
                scanner.next();

            }

        }
        return numbers;
    }

    public void testSumeOfUniqueElements(Scanner scanner) {

        System.out.println("Select type of testing (1 as default):");
        System.out.println("1. Testcases."); // The ones from the pdf
        System.out.println("2. Enter test manually (5 numbers)"); // Limited to five

        int test = this.validInputNumber(scanner, " \nSelect an option: ");

        if (test == 2) {

            scanner.nextLine();
            int[] testValues = this.fillArrayWithNumbers(scanner);
            System.out.println(
                    Arrays.toString(testValues).replace(" ", "") + ": " + this.sumOfUniqueElements(testValues));

        } else {

            System.out.println("[1,2,3,2,4]: " + this.sumOfUniqueElements(new int[] { 1, 2, 3, 2, 4 }));
            System.out.println("[5,5,5,5,5]: " + this.sumOfUniqueElements(new int[] { 5, 5, 5, 5, 5 }));

        }
    }

    public void printMenuOptions() {
        System.out.println("\nWelcome to Console App!");
        System.out.println("1. FizzBuzz.");
        System.out.println("2. Palindrome Checker.");
        System.out.println("3. Sum of Unique Elements.");
        System.out.println("4. To Do App.");
        System.out.println("5. Exit.\n");
    }

    public int validInputNumber(Scanner scanner, String description) {

        System.out.println(description);

        while (!scanner.hasNextInt()) {

            System.out.println("Invalid input. Enter a number.");
            scanner.next();
            System.out.print(description);

        }

        return scanner.nextInt();
    }

    public void menu() {

        int option;
        Scanner scanner = new Scanner(System.in);

        do {

            this.printMenuOptions();

            option = this.validInputNumber(scanner, " \nSelect an option");
            scanner.nextLine();

            switch (option) {

                case 1:
                    this.fizzBuzz();
                    break;
                case 2:
                    this.testPalindrome(scanner);
                    break;
                case 3:
                    this.testSumeOfUniqueElements(scanner);
                    break;
                case 4:
                    ToDo toDoApp = new ToDo(scanner);
                    toDoApp.menu();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Choose an option from 1 to 5");
                    break;

            }

        } while (option != 5);
        scanner.close();
    }

    public static void main(String[] args) {

        ConsoleApp consoleApp = new ConsoleApp();
        consoleApp.menu();

    }
}