
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Plus.
 */
public class Plus extends BinaryExpression implements Expression {


    /**
     * Instantiates a new Plus.
     *
     * @param x the x
     * @param y the y
     */
    public Plus(Expression x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new Plus.
     *
     * @param x the x
     * @param y the y
     */
    public Plus(Expression x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new Plus.
     *
     * @param x the x
     * @param y the y
     */
    public Plus(double x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new Plus.
     *
     * @param x the x
     * @param y the y
     */
    public Plus(Expression x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new Plus.
     *
     * @param x the x
     * @param y the y
     */
    public Plus(String x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new Plus.
     *
     * @param x the x
     * @param y the y
     */
    public Plus(double x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new Plus.
     *
     * @param x the x
     * @param y the y
     */
    public Plus(double x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new Plus.
     *
     * @param x the x
     * @param y the y
     */
    public Plus(String x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new Plus.
     *
     * @param x the x
     * @param y the y
     */
    public Plus(String x, String y) {
        super(x, y);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return super.getx().evaluate(assignment) + super.gety().evaluate(assignment);
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
        return "(" + super.getx().toString() + " + " + super.gety().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(super.getx().assign(var, expression),
                super.gety().assign(var, expression));
    }

    @Override
    public Expression differentiate(String sting) {
        if (super.getVariables().contains(sting)) {
            return new Plus(super.getx().differentiate(sting),
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
            return new Num(new Plus(x, y).evaluate());
        } catch (Exception e) {
            int i = 0;
        }
        if ((((x.toString().equals("0.00")) || ((x.toString().equals("0.0")))) || (((x.toString().equals("0")))))) {
            return y.simplify();
        } else if ((((y.toString().equals("0.00")) || (y.toString().equals("0.0"))) || (y.toString().equals("0")))) {
            return x.simplify();
        } else if (y.getVariables().size() == 0 && x.getVariables().size() == 0) {

            try {
                return new Num(y.evaluate() + x.evaluate()).simplify();
            } catch (Exception e) {
                int i = 0;
            }

        } else if (y.getVariables().size() == 0 && x.getVariables().size() != 0) {
            try {
                return new Plus(x, new Num((y.evaluate())));
            } catch (Exception ex) {
                int i = 1;
            }
        }

        if (y.getVariables().size() != 0 && x.getVariables().size() == 0) {
            try {
                return new Plus(new Num(x.evaluate()), y);
            } catch (Exception ex) {
                int i = 1;
            }
        }
        return new Plus(x, y);

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

            if ((((Mult) x).getx().getVariables().size() == 0) && (((Mult) y).getx().getVariables().size() == 0)) {
                return new Mult(new Plus(((Mult) x).getx(), ((Mult) y).getx()), ((Mult) x).gety()).simplify();
            } else if ((((Mult) x).gety().getVariables().size() == 0)
                    && (((Mult) y).gety().getVariables().size() == 0)) {
                return new Mult(new Plus(((Mult) x).gety(), ((Mult) y).gety()), ((Mult) x).getx()).simplify();

            } else if ((((Mult) x).getx().getVariables().size() == 0)
                    && (((Mult) y).gety().getVariables().size() == 0)) {
                return new Mult(new Plus(((Mult) x).getx(), ((Mult) y).gety()), ((Mult) x).gety()).simplify();

            } else if ((((Mult) x).gety().getVariables().size() == 0)
                    && (((Mult) y).getx().getVariables().size() == 0)) {
                return new Mult(new Plus(((Mult) y).getx(), ((Mult) x).gety()), ((Mult) x).getx()).simplify();
            }
        }
        if (x instanceof Pow) {
            if (((Pow) x).getx() instanceof Sin) {
                try {
                    if ((((Pow) x).gety()).evaluate() == 2) {
                        if (y instanceof Pow) {
                            if (((Pow) y) instanceof Pow) {
                                if ((((Pow) y).gety()).evaluate() == 2.0) {
                                    if (x.getVariables().get(0) == y.getVariables().get(0)) {
                                        return new Num(1);
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    int i = 1;
                }
            }
        }
        return new Plus(x, y).simplify();
    }
}