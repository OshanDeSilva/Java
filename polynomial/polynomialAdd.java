import java.util.Scanner;

public class polynomialAdd {
    public static void main(String[] args) {
        String expression1 = "2X^3-4X^2+3";
        String expression2 = "-3X^2+2X-4";

        Scanner input = new Scanner(System.in);

        System.out.println("Type the First Polynomial expression: ");
        // expression1 = input.nextLine();
        String[] exp1Terms = splitString(expression1);
        printArray(exp1Terms);
        int orderOf1 = findOrder(exp1Terms);
        System.out.println("Order of exp1 is: " + orderOf1);

        System.out.println("Type the Second Polynomial expression: ");
        // expression2 = input.nextLine();
        String[] exp2Terms = splitString(expression2);
        printArray(exp2Terms);
        int orderOf2 = findOrder(exp2Terms);
        System.out.println("Order of exp1 is: " + orderOf2);
    }

    // Split expreion into a array of terms(quadratic,linear,constant and more)
    // Used lookahead aproach to preserve + and - signs in the splitted terms ====
    // "(?=[regex])"

    private static String[] splitString(String expression) {
        String regex = "(?=[+\\-])";
        String[] items = expression.split(regex, -1);
        return items;
    }

    // A method for printing arrays
    private static void printArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    // Find the highest power of a expression
    private static int findOrder(String[] terms) {
        int order = 0;
        for (int i = 0; i < terms.length; i++) {
            if (terms[i].contains("X^")) {
                String[] items = terms[i].split("[X^]");
                // Select 2 intead 1 because there is empty string being added to the item array
                // Empty string being created between X and ^
                String power = items[2];
                int powerVal = Integer.parseInt(power);
                if (powerVal > order) {
                    order = powerVal;
                }

            } else {
                int power;
                if (terms[i].contains("X")) {
                    power = 1;
                    if (power > order) {
                        order = power;
                    }
                }
            }
        }
        return order;
    }
}