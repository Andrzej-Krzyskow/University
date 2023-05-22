public class Main {

    // ex. 1
    // a)
    public static long iterativeFib(int n) {

        if (n < 0) {
            return -1;
        }

        long a = 0, b = 1;

        for (int i = 0; i < n; i++) {
            b = a + b;
            a = b - a;
        }

        return a;
    }

    // b)
    public static long recursiveFib(int n) {

        if (n < 0) {
            return -1;
        }

        return recHelpFib(n, 0, 1);
    }

    static long recHelpFib(int n, long a, long b) {

        if (n <= 0) {
            return a;
        }

        return recHelpFib(n - 1, b, a + b);

    }

    // ex. 2
    static class DividingByZeroException extends Exception {

        public DividingByZeroException() {
            super("Dividing by zero not allowed.");
        }

        public DividingByZeroException(String errorMessage) {
            super(errorMessage);
        }

    }

    public static double div(double a, double b) throws DividingByZeroException {

        if (b == 0) {
            throw new DividingByZeroException();
        }

        return a / b;
    }


    public static void main(String[] args) {
        System.out.println(iterativeFib(-3));
        System.out.println(iterativeFib(0));
        System.out.println(iterativeFib(1));
        System.out.println(iterativeFib(2));
        System.out.println(iterativeFib(3));
        System.out.println(iterativeFib(4));
        System.out.println(iterativeFib(10));
        System.out.println("------------------");
        System.out.println(recursiveFib(-3));
        System.out.println(recursiveFib(0));
        System.out.println(recursiveFib(1));
        System.out.println(recursiveFib(2));
        System.out.println(recursiveFib(3));
        System.out.println(recursiveFib(4));
        System.out.println(recursiveFib(10));

        try {
            div(2, 0);
        } catch (DividingByZeroException e) {
            System.err.println(e.getMessage());
        }

        /*

        ex. 3

Output:
    true (because we compare values, those are primitive types)
    false (because Integer is an object, and by "==" we compare the adresses of those objects)

        ex. 4

Output:
    true (because when we declare s2, we assign String "foo", but such object was already created,
            thus s2 will just point to the same object as s1)
    true (because s1 and s2 point to the same object with value "foo")
    false (because during declaration of s3 we explicitly create a new String object with a parameter "foo")
    true (becauase the value of the object pointed by s1 is the same, which is pointed by s3)

        */

    }
}