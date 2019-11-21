import java.util.Map;
import java.util.TreeMap;

/**
 * The type Cos.
 */
public class Cos extends UnaryExpression implements Expression {

    /**
     * Instantiates a new Cos.
     *
     * @param x the x
     */
    public Cos(Expression x) {
        super(x);
    }

    /**
     * Instantiates a new Cos.
     *
     * @param x the x
     */
    public Cos(double x) {
        super(x);
    }

    /**
     * Instantiates a new Cos.
     *
     * @param x the x
     */
    public Cos(Var x) {
        super(x);
    }

    /**
     * Instantiates a new Cos.
     *
     * @param x the x
     */
    public Cos(int x) {
        super(x);
    }

    /**
     * Instantiates a new Cos.
     *
     * @param x the x
     */
    public Cos(String x) {
        super(x);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.cos(super.getme().evaluate(assignment));
    }

    @Override
    public double evaluate() throws Exception {
        Map<String, Double> empty = new TreeMap<String, Double>();
        return this.evaluate(empty);
    }
    @Override
    public String toString() {
        return "cos(" + super.getme().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(super.getme().assign(var, expression));
    }
    @Override
    public Expression differentiate(String sting) {
        if (super.getVariables().contains(sting)) {
            if (super.getme().toString().equals(sting)) {
                return new Mult(super.getme().differentiate(sting), new Neg(new Sin(super.getme())));
            }
        }
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        Expression x = super.getme().simplify();
        try {
            return new Num(this.evaluate());
        } catch (Exception ex) {
            return new Cos(x);
        }

    }
}
