public class IsEqual {
    static boolean isEqual1(int m, int n) {
        return m == n;
    }

    static boolean isEqual2(Integer m, Integer n) {
        return m == n;
    }

    public static void main(String[] args) {
        System.out.println(isEqual1(250, 250));
        System.out.println(isEqual2(250, 250));
    }
}