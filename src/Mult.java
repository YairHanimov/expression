
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Mult.
 */
public class Mult extends BinaryExpression implements Expression {


    /**
     * Instantiates a new Mult.
     *
     * @param x the x
     * @param y the y
     */
    public Mult(Expression x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new Mult.
     *
     * @param x the x
     * @param y the y
     */
    public Mult(Expression x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new Mult.
     *
     * @param x the x
     * @param y the y
     */
    public Mult(double x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new Mult.
     *
     * @param x the x
     * @param y the y
     */
    public Mult(Expression x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new Mult.
     *
     * @param x the x
     * @param y the y
     */
    public Mult(String x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new Mult.
     *
     * @param x the x
     * @param y the y
     */
    public Mult(double x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new Mult.
     *
     * @param x the x
     * @param y the y
     */
    public Mult(double x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new Mult.
     *
     * @param x the x
     * @param y the y
     */
    public Mult(String x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new Mult.
     *
     * @param x the x
     * @param y the y
     */
    public Mult(String x, String y) {
        super(x, y);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return super.getx().evaluate(assignment) * super.gety().evaluate(assignment);
    }

    @Override
    public double evaluate() throws Exception {
        Map<String, Double> empty = new TreeMap<String, Double>();
        return this.evaluate(empty);
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public String toString() {
        return "(" + super.getx().toString() + " * " + super.gety().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Mult(super.getx().assign(var, expression),
                super.gety().assign(var, expression));
    }
    @Override
    public Expression differentiate(String sting) {

        return new Plus(new Mult(super.getx().differentiate(sting),
                super.gety()),
                new Mult(super.gety().differentiate(sting), super.getx()));
    }

    @Override
    public Expression simplify() {
        Expression x = super.getx().simplify();
        Expression y = super.gety().simplify();
        if ((((x.toString().equals("0")) || (x.toString().equals("0.0"))) || (x.toString().equals("0.00")))) {
            return x;
        }
        if ((((y.toString().equals("0")) || (y.toString().equals("0.0"))) || (y.toString().equals("0.00")))) {
            return y;
        }
        if ((((x.toString().equals("1")) || (x.toString().equals("1.0"))) || (x.toString().equals("1.00")))) {
            return y;
        }
        if ((((y.toString().equals("1")) || (y.toString().equals("1.0"))) || (y.toString().equals("1.00")))) {
            return x;
        }
        if ((y.getVariables().size() == 0.00) && (x.getVariables().size() == 0.00)) {
            try {
                return new Num(y.evaluate() * x.evaluate());
            } catch (Exception ex) {
                int i = 0;
            }
        }
        if ((y.getVariables().size() == 0.00) && (x.getVariables().size() != 0.00)) {
            try {
                return new Mult(x, y.evaluate());
            } catch (Exception ex) {
                int i = 0;
            }
        } else if ((y.getVariables().size() != 0.00) && (x.getVariables().size() == 0.00)) {
            try {
                return new Mult(x.evaluate(), y);
            } catch (Exception e) {
                int i = 0;
            }
        }

        return new Mult(x, y);

    }

    /**
     * Bonus expression.
     *
     * @return the expression
     */
    public Expression bonus() {
        Expression y = super.gety().simplify();
        Expression x = super.getx().simplify();

        if ((x instanceof Plus) && (y instanceof Plus)) {
            return new Mult(((Plus) x).bonus(), ((Plus) y).bonus()).bonus();
        } else if (x instanceof Plus) {
            return new Mult(((Plus) x).bonus(), y).bonus();
        } else if (y instanceof Plus) {
            return new Mult(y, ((Plus) y).bonus()).bonus();
        }
        if (x.simplify().toString().equals("0.0")) {
            return new Num(0);
        }
        if (y.simplify().toString().equals("0.0")) {
            return new Num(0);
        }
        if (x.simplify().toString().equals("1.0")) {
            return y.simplify();
        }
        if (y.simplify().toString().equals("1.0")) {
            return x.simplify();
        }

        return new Mult(x, y).simplify();
    }


}




