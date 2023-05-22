import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Stack;

public class RpnCalculator {

    public static double toInfixNotation(String RpnExpression) {
        String[] data = RpnExpression.split(", ");
        Stack<Double> stack = new Stack<>();

        for (String expression : data) {
            try {
                stack.push(Double.parseDouble(expression));

            } catch (Exception e) {
                double lastNumber = stack.pop();
                double preLastNumber = stack.pop();

                switch (expression) {
                    case "+" -> stack.push(preLastNumber + lastNumber);
                    case "-" -> stack.push(preLastNumber - lastNumber);
                    case "*" -> stack.push(preLastNumber * lastNumber);
                    case "/" -> stack.push(preLastNumber / lastNumber);
                    case "^" -> stack.push(Math.pow(preLastNumber, lastNumber));
                }
            }

        }
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.ENGLISH);
        numberFormat.setMaximumFractionDigits(2);
        return Double.parseDouble(numberFormat.format(stack.pop()));
    }

}
