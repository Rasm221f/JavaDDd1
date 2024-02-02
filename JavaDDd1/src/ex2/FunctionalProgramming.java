package ex2;

interface MyTransformingType {
    int transform(int a);
}
interface MyValidatingType{
    boolean validate(int a);
}

public class FunctionalProgramming {

    static int[] map(int[] a, MyTransformingType op) {
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = op.transform(a[i]);
        }
        return result;
    }
    static int[] filter(int[] a, MyValidatingType op){
        int count = 0;
        for (int i : a) {
            if (op.validate(i)){
                count++;
            }
        }
        int [] result = new int[count];
        int index = 0;
        for (int i : a) {
            if (op.validate(i)) {
                result[index++] = i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] myArray = {1, 2, 3, 4, 5};

        int[] doubledArray = map(myArray, (x) -> x * 2);
        for (int value : doubledArray) {
            System.out.print(value + " ");
        }
        System.out.println();

        int[] evenArray = filter(myArray, (x) -> x % 2 == 0);
        for (int value : evenArray) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    }
