import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Div.
 */
public class Div extends BinaryExpression implements Expression {


    /**
     * Instantiates a new Div.
     *
     * @param x the x
     * @param y the y
     */
    public Div(Expression x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new Div.
     *
     * @param x the x
     * @param y the y
     */
    public Div(Expression x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new Div.
     *
     * @param x the x
     * @param y the y
     */
    public Div(double x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new Div.
     *
     * @param x the x
     * @param y the y
     */
    public Div(Expression x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new Div.
     *
     * @param x the x
     * @param y the y
     */
    public Div(String x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new Div.
     *
     * @param x the x
     * @param y the y
     */
    public Div(double x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new Div.
     *
     * @param x the x
     * @param y the y
     */
    public Div(double x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new Div.
     *
     * @param x the x
     * @param y the y
     */
    public Div(String x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new Div.
     *
     * @param x the x
     * @param y the y
     */
    public Div(String x, String y) {
        super(x, y);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return super.getx().evaluate(assignment) / super.gety().evaluate(assignment);
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
        return "(" + super.getx().toString() + " / " + super.gety().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Div(super.getx().assign(var, expression),
                super.gety().assign(var, expression));
    }

    @Override
    public Expression differentiate(String sting) {
        return new Div(
                new Minus(
                        new Mult(super.getx().differentiate(sting), super.gety()),
                        new Mult(super.gety().differentiate(sting), super.getx())),
                new Pow(super.gety(), new Num(2)));
    }

    @Override
    public Expression simplify() {
        Expression x = super.getx().simplify();
        Expression y = super.gety().simplify();
        if (((x.toString().equals("0")) || (x.toString().equals("0.0"))) || (x.toString().equals("0.00"))) {
            return new Num(0);
        }
        if (((y.toString().equals("1")) || (y.toString().equals("1.0"))) || (y.toString().equals("1.00"))) {
            return x;
        }
        if (y.getVariables().size() == 0.00 && x.getVariables().size() == 0.00) {
            try {
                return new Num(x.evaluate() / y.evaluate());
            } catch (Exception ex) {
                int i = 0;
            }
        }
        if (y.getVariables().size() == 0.00 && x.getVariables().size() != 0.00) {
            try {
                return new Div(x, y.evaluate());
            } catch (Exception ex) {
                int i = 0;
            }
        } else if (y.getVariables().size() != 0.00 && x.getVariables().size() == 0.00) {
            try {
                return new Div(x.evaluate(), y);
            } catch (Exception ex) {
                int i = 0;
            }
        }
        return this;

    }

    /**
     * Bonus expression.
     *
     * @return the expression
     */
    public Expression bonus() {
        Expression y = super.gety().simplify();
        Expression x = super.getx().simplify();
        if ((x instanceof Sin) && (y instanceof Cos)) {
            Expression one = ((Sin) x).getme();
            Expression two = ((Cos) y).getme();
            if ((one.toString()).equals(two.toString())) {
                return new Tan(((Sin) x).getme());
            }

        }
        if ((((y.getVariables().size() != 0)) && (x.getVariables().size() != 0))
                && ((x instanceof Mult) && (y instanceof Mult))) {
            if (((Mult) y).getx().getVariables().get(0) == ((Mult) x).getx().getVariables().get(0)) {
                return new Div(((Mult) x).gety(), ((Mult) y).gety()).bonus();
            } else if (((Mult) y).gety().getVariables().get(0) == ((Mult) x).getx().getVariables().get(0)) {
                return new Div(((Mult) x).gety(), ((Mult) y).getx()).bonus();
            }
            if (((Mult) y).getx().getVariables().get(0) == ((Mult) x).gety().getVariables().get(0)) {
                return new Div(((Mult) x).getx(), ((Mult) y).gety()).bonus();
            }
            if (((Mult) y).gety().getVariables().get(0) == ((Mult) x).gety().getVariables().get(0)) {
                return new Div(((Mult) x).getx(), ((Mult) y).getx()).bonus();
            }
        }
        if ((x instanceof Pow) && (y instanceof Pow)) {
            if (((Pow) x).getx().toString() == ((Pow) y).getx().toString()) {
                try {
                    double myy = ((Pow) y).gety().evaluate();
                    double mxx = ((Pow) x).gety().evaluate();
                    double myminus = new Minus(((Pow) x).gety(), ((Pow) y).gety()).simplify().evaluate();
                    return new Pow(((Pow) x).getx(), new Num(myminus));
                } catch (Exception e) {
                    return new Pow(((Pow) x).getx(), new Minus(((Pow) x).gety(), ((Pow) y).gety()).simplify());
                }
            }
        }
        return new Div(x, y).simplify();
    }
}


