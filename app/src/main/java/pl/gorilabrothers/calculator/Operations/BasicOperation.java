package pl.gorilabrothers.calculator.Operations;

/**
 * Created by тольдобагинс on 15.09.2016.
 */
public class BasicOperation {

    public String Calc(String a, String b, String o) {
        double first = Double.parseDouble(a.replace(",", "."));
        double second = Double.parseDouble(b.replace(",", "."));
        double result = 0;

        if (o.equals("divide")) {
            result = first / second;
        } else if (o.equals("multime")) {
            result = first * second;
        } else if (o.equals("minus")) {
            result = first - second;
        } else if (o.equals("plus")) {
            result = first + second;
        }

        if (result % 1 == 0) {
            return String.valueOf((int)result);
        } else {
            return Double.toString(result).replace(".", ",");
        }
    }
}
