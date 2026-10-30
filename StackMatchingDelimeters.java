
package StackMatchingDelimeters;

import java.util.Scanner;
import java.util.Stack;

public class StackMatchingDelimeters {

    static Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("=== DELIMITER VALIDATION PROGRAM ===");

        String expression;

        do {
            System.out.print("\nEnter an expression (EXIT to stop): ");
            expression = inputScanner.nextLine();

            if (expression.equalsIgnoreCase("EXIT")) {
                System.out.println("Thank you for using the program!");
                break;
            }

            Stack<Character> delimiterStack = new Stack<>();
            boolean isValid = true;
            String errorMessage = "";

            System.out.println("\n=== STACK PROCESS ===");

            for (int index = 0; index < expression.length(); index++) {

                char symbol = expression.charAt(index);

                System.out.println("\nCurrent Character: " + symbol);

                if (isOpening(symbol)) {

                    delimiterStack.push(symbol);

                    System.out.println("Action: PUSH " + symbol);

                } else if (isClosing(symbol)) {

                    if (delimiterStack.empty()) {

                        isValid = false;
                        errorMessage = "No opening delimiter found for " + symbol;
                        System.out.println("Action: INVALID");
                        System.out.println("Error: " + errorMessage);
                        printStack(delimiterStack);
                        break;

                    } else {

                        char openingSymbol = delimiterStack.peek();

                        System.out.println("Action: PEEK " + openingSymbol);
                        if (isPair(openingSymbol, symbol)) {

                            delimiterStack.pop();

                            System.out.println("Action: POP " + openingSymbol);

                        } else {

                   isValid = false;
         errorMessage = "Incorrect pair: " + openingSymbol
                                    + " cannot match " + symbol;

            System.out.println("Action: INVALID");
              System.out.println("Error: " + errorMessage);
         printStack(delimiterStack);
                  break;
                        }
                    }

                } else {

                    System.out.println("Action: SKIP (ordinary character)");
                }

                printStack(delimiterStack);
            }

            if (isValid && !delimiterStack.empty()) {

                isValid = false;
                errorMessage = "There are opening delimiters without closing pairs.";

                System.out.println("\nError: " + errorMessage);
               printStack(delimiterStack);
         }

         System.out.println("\n=== VALIDATION RESULT ===");
       System.out.println("Expression: "
               + (expression.isEmpty() ? "(No input)" : expression));

        if (isValid) {
            System.out.println("Status: VALID");
            System.out.println("All delimiters are properly matched.");
        } else {
        System.out.println("Status: INVALID");
          System.out.println("Reason: " + errorMessage);
        }

    } while (true);

        inputScanner.close();
    }

    static boolean isOpening(char symbol) {
        return symbol == '(' || symbol == '[' || symbol == '{';
    }

    static boolean isClosing(char symbol) {
        return symbol == ')' || symbol == ']' || symbol == '}';
    }

    static boolean isPair(char open, char close) {

        if (open == '(' && close == ')') {
            return true;
        }

        if (open == '[' && close == ']') {
            return true;
        }

        if (open == '{' && close == '}') {
            return true;
        }

        return false;
    }

    static void printStack(Stack<Character> delimiterStack) {

        System.out.print("Current Stack: ");

        if (delimiterStack.isEmpty()) {
            System.out.println("Empty");
            return;
        }

        for (char item : delimiterStack) {
            System.out.print(item + " ");
        }

        System.out.println();
    }
}
