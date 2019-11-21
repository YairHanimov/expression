import java.util.Map;
import java.util.TreeMap;

/**
 * The type Sin.
 */
public class Sin extends UnaryExpression implements Expression {

    /**
     * Instantiates a new Sin.
     *
     * @param x the x
     */
    public Sin(Expression x) {
        super(x);
    }

    /**
     * Instantiates a new Sin.
     *
     * @param x the x
     */
    public Sin(double x) {
        super(x);
    }

    /**
     * Instantiates a new Sin.
     *
     * @param x the x
     */
    public Sin(Var x) {
        super(x);
    }

    /**
     * Instantiates a new Sin.
     *
     * @param x the x
     */
    public Sin(int x) {
        super(x);
    }

    /**
     * Instantiates a new Sin.
     *
     * @param x the x
     */
    public Sin(String x) {
        super(x);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.sin(super.getme().evaluate(assignment));
    }

    @Override
    public double evaluate() throws Exception {
        Map<String, Double> empty = new TreeMap<String, Double>();
        return this.evaluate(empty);
    }

    @Override
    public String toString() {
        return "sin(" + super.getme().toString() + ")";
    }

    @Override
    public Expression assign(String sting, Expression expression) {
        return new Sin(super.getme().assign(sting, expression));
    }

    @Override
    public Expression differentiate(String s) {
        return new Mult(super.getme().differentiate(s), new Cos(super.getme()));
    }

    @Override
    public Expression simplify() {
        Expression x = super.getme().simplify();
        try {
            return new Num(this.evaluate());
        } catch (Exception ex) {
            return new Sin(x);
        }

    }

    /**
     * Bonus expression.
     *
     * @return the expression
     */
    public Expression bonus() {
        Expression x = super.getme().simplify();
        if (x instanceof Mult) {
            try {
                if (((Mult) x).getx().evaluate() == 2) {
                    return new Mult(new Mult(2, new Sin(((Mult) x)
                            .gety().simplify())), new Cos(((Mult) x).gety().simplify()));
                }
            } catch (Exception e) {
                return new Sin(x);
            }
        }
        return new Sin(x);
    }
}
