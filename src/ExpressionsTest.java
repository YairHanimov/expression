import java.util.Map;
import java.util.TreeMap;

/**
 * The type Expressions test.
 */
public class ExpressionsTest {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        Expression e = new Plus(new Plus(new Mult(2, "x"),
                new Sin(new Mult(4, "y"))), new Pow("e", "x"));
        System.out.println(e.toString());
        Map<String, Double> assignment = new TreeMap<>();
        assignment.put("x", 2.00);
        assignment.put("y", 0.25);
        assignment.put("e", Math.E);
        System.out.println(e.evaluate(assignment));
        System.out.println(e.differentiate("x"));
        System.out.println(e.differentiate("x").evaluate(assignment));
        System.out.println(e.differentiate("x").simplify());
    }
}