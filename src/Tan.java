import java.util.Map;
import java.util.TreeMap;


/**
 * The type Tan.
 */
public class Tan extends UnaryExpression implements Expression {

    /**
     * Instantiates a new Tan.
     *
     * @param x the x
     */
    public Tan(Expression x) {
        super(x);
    }

    /**
     * Instantiates a new Tan.
     *
     * @param x the x
     */
    public Tan(double x) {
        super(x);
    }

    /**
     * Instantiates a new Tan.
     *
     * @param x the x
     */
    public Tan(Var x) {
        super(x);
    }

    /**
     * Instantiates a new Tan.
     *
     * @param x the x
     */
    public Tan(int x) {
        super(x);
    }

    /**
     * Instantiates a new Tan.
     *
     * @param x the x
     */
    public Tan(String x) {
        super(x);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.tan(super.getme().evaluate(assignment));
    }

    @Override
    public double evaluate() throws Exception {
        Map<String, Double> empty = new TreeMap<String, Double>();
        return this.evaluate(empty);
    }

    @Override
    public String toString() {
        return "Tan(" + super.getme().toString() + ")";
    }

    @Override
    public Expression assign(String sting, Expression expression) {
        return new Tan(super.getme().assign(sting, expression));
    }

    @Override
    public Expression differentiate(String s) {
        return new Div(new Sin(super.getme()), new Cos(super.getme())).differentiate("x");
    }

    @Override
   public Expression simplify() {
        Expression x = super.getme().simplify();
        try {
            return new Num(this.evaluate());
        } catch (Exception ex) {
            return new Tan(x);
        }

    }
}