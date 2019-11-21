
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Neg.
 */
public class Neg extends UnaryExpression implements Expression {

    /**
     * Instantiates a new Neg.
     *
     * @param x the x
     */
    public Neg(Expression x) {

        super(x);
    }

    /**
     * Instantiates a new Neg.
     *
     * @param x the x
     */
    public Neg(double x) {

        super(x);
    }

    /**
     * Instantiates a new Neg.
     *
     * @param x the x
     */
    public Neg(int x) {

        super(x);
    }

    /**
     * Instantiates a new Neg.
     *
     * @param x the x
     */
    public Neg(Var x) {

        super(x);
    }

    /**
     * Instantiates a new Neg.
     *
     * @param x the x
     */
    public Neg(String x) {

        super(x);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return -1 * super.getme().evaluate(assignment);
    }

    @Override
    public double evaluate() throws Exception {
        Map<String, Double> empty = new TreeMap<String, Double>();
        return this.evaluate(empty);
    }

    @Override
    public Expression assign(String s, Expression expression) {
        return new Neg(super.getme().assign(s, expression));
    }

    @Override
    public Expression differentiate(String s) {
        if (super.getVariables().contains(s)) {
            if (super.getme().toString().equals(s)) {
                return new Neg(super.getme().differentiate(s));
            }
        }
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        Expression x = super.getme().simplify();
        try {
            return new Num(this.evaluate()).simplify();
        } catch (Exception e) {
            return new Neg(x);
        }
    }

    @Override
    public String toString() {
        try {
            if (super.getme().evaluate() < 0) {
                return new Num(super.getme().evaluate() * -1).toString();
            }
        } catch (Exception e) {
            int i = 1;
        }
        return "(-" + super.getme().toString() + ")";
    }

    /**
     * Bonus expression.
     *
     * @return the expression
     */
    public Expression bonus() {
        Expression x = super.getme().simplify();
        if (x instanceof Neg) {
            return ((Neg) x).getme();
        } else {
            return this;
        }
    }
}