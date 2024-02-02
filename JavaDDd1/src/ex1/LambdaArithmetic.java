package ex1;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
interface ArithmeticOperation {
    int perform(int a, int b);
}

public class LambdaArithmetic {

    // Lambda expressions for each arithmetic operation
    static ArithmeticOperation addition = (a, b) -> a + b;
    static ArithmeticOperation subtraction = (a, b) -> a - b;
    static ArithmeticOperation multiplication = (a, b) -> a * b;
    static ArithmeticOperation division = (a, b) -> a / b;
    static ArithmeticOperation modulus = (a, b) -> a % b;
    static ArithmeticOperation power = (a, b) -> (int) Math.pow(a, b);

    public static int operate(int a, int b, ArithmeticOperation op) {
        return op.perform(a, b);
    }
    public static int[] operate(int[] a, int[] b, ArithmeticOperation op) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Arrays must be of the same length.");
        }
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = op.perform(a[i], b[i]);
        }
        return result;
    }
    public static void main(String[] args) {
        // Testing individual operations
        System.out.println("Addition: " + operate(5, 3, addition));
        System.out.println("Subtraction: " + operate(5, 3, subtraction));
        System.out.println("Multiplication: " + operate(5, 3, multiplication));
        System.out.println("Division: " + operate(5, 3, division));
        System.out.println("Modulus: " + operate(5, 3, modulus));
        System.out.println("Power: " + operate(5, 3, power));

        // Testing array operations
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {4, 5, 6};
        int[] result = operate(arr1, arr2, addition);
        int[] resultpower = operate(arr1, arr2, power);
        int[] resultmultiplication = operate(arr1, arr2, multiplication);
        System.out.println("Array Addition: " + Arrays.toString(result));
        System.out.println("Array Power: " + Arrays.toString(resultpower));
        System.out.println("Array Multiplication: " + Arrays.toString(resultmultiplication));
    }
}


