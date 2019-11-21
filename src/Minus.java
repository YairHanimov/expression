
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Minus.
 */
public class Minus extends BinaryExpression implements Expression {


    /**
     * Instantiates a new Minus.
     *
     * @param x the x
     * @param y the y
     */
    public Minus(Expression x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new Minus.
     *
     * @param x the x
     * @param y the y
     */
    public Minus(Expression x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new Minus.
     *
     * @param x the x
     * @param y the y
     */
    public Minus(double x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new Minus.
     *
     * @param x the x
     * @param y the y
     */
    public Minus(Expression x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new Minus.
     *
     * @param x the x
     * @param y the y
     */
    public Minus(String x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new Minus.
     *
     * @param x the x
     * @param y the y
     */
    public Minus(double x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new Minus.
     *
     * @param x the x
     * @param y the y
     */
    public Minus(double x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new Minus.
     *
     * @param x the x
     * @param y the y
     */
    public Minus(String x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new Minus.
     *
     * @param x the x
     * @param y the y
     */
    public Minus(String x, String y) {
        super(x, y);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return super.getx().evaluate(assignment) - super.gety().evaluate(assignment);
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
        return "(" + super.getx().toString() + " - " + super.gety().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Minus(super.getx().assign(var, expression),
                super.gety().assign(var, expression));
    }
    @Override
    public Expression differentiate(String sting) {
        if (super.getVariables().contains(sting)) {
            return new Minus(super.getx().differentiate(sting),
                    super.gety().differentiate(sting));
        } else {
            return new Num(0);
        }
    }
    @Override
    public Expression simplify() {
        Expression x = super.getx().simplify();
        Expression y = super.gety().simplify();
        try {
            return new Num(new Minus(x, y).evaluate());
        } catch (Exception e) {
            int i = 0;
        }
        if ((((x.toString().equals("0.00")) || ((x.toString().equals("0.0")))) || (((x.toString().equals("0")))))) {
            return new Neg(y).simplify();
        } else if ((((y.toString().equals("0.00")) || (y.toString().equals("0.0"))) || (y.toString().equals("0")))) {
            return x.simplify();


        } else if (y.getVariables().size() == 0 && x.getVariables().size() != 0) {
            try {
                return new Minus(x, new Num(y.evaluate()).simplify()).simplify();
            } catch (Exception ex) {
                int i = 1;
            }
        }

        if (y.getVariables().size() != 0 && x.getVariables().size() == 0) {
            try {
                return new Minus(new Num(x.evaluate()), y).simplify();
            } catch (Exception ex) {
                int i = 1;
            }
        }
        if (x.toString().equals(y.toString())) {
            return new Num(0);
        } else if (y.getVariables().size() == 0 && x.getVariables().size() == 0) {

            try {
                return new Num(x.evaluate() - y.evaluate()).simplify();
            } catch (Exception e) {
                return new Minus(x, y);
            }


        }
        return new Minus(x, y);
    }

    /**
     * Bonus expression.
     *
     * @return the expression
     */
    public Expression bonus() {
        Expression y = super.gety().simplify();
        Expression x = super.getx().simplify();
        if (((x instanceof Mult) && (y instanceof Mult)) && (x.getVariables().get(0) == y.getVariables().get(0))) {

            if ((((Mult) x).getx().getVariables().size() == 0)
                    && (((Mult) y).getx().getVariables().size() == 0)) {
                return new Mult(new Minus(((Mult) x).getx(), ((Mult) y).getx()), ((Mult) x).gety()).simplify();
            } else if ((((Mult) x).gety().getVariables().size() == 0)
                    && (((Mult) y).gety().getVariables().size() == 0)) {
                return new Minus(new Minus(((Mult) x).gety(), ((Mult) y).gety()), ((Mult) x).getx()).simplify();

            } else if ((((Mult) x).getx().getVariables().size() == 0)
                    && (((Mult) y).gety().getVariables().size() == 0)) {
                return new Minus(new Minus(((Mult) x).getx(), ((Mult) y).gety()), ((Mult) x).gety()).simplify();

            } else if ((((Mult) x).gety().getVariables().size() == 0)
                    && (((Mult) y).getx().getVariables().size() == 0)) {
                return new Minus(new Minus(((Mult) y).getx(), ((Mult) x).gety()), ((Mult) x).getx()).simplify();

            }

        }

        return new Plus(x, y).simplify();
    }
}
