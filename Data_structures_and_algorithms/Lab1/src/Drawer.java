public class Drawer {

    private static void drawLine(int n, char ch) {

        System.out.println(Character.toString(ch).repeat(n));
    }

    public static void drawPyramid(int n) {

        drawPyramid(n, 0);
    }

    private static void drawPyramid(int n, int spaces) {

        for (int i = 1; i <= n; i++) {
            System.out.print(" ".repeat(n - i + spaces));
            drawLine(2*i-1, 'X');
        }
    }

    public static void drawChristmassTree(int n) {

        for (int i = 1; i <= n; i++) {
            drawPyramid(i, n - i);
        }
    }

    public static void drawLetterZ(int n) {

        if (!(n > 2 && n < 21)) {
            System.out.println("Number out of range");
            return;
        }

        drawLine(n,'Z');
        for (int i = 2; i < n; i++) {
            System.out.println(" ".repeat(n-i)+'Z');
        }
        drawLine(n, 'Z');
    }

}

