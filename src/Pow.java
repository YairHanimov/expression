
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Pow.
 */
public class Pow extends BinaryExpression implements Expression {

    /**
     * Instantiates a new Pow.
     *
     * @param x the x
     * @param y the y
     */
    public Pow(Expression x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new Pow.
     *
     * @param x the x
     * @param y the y
     */
    public Pow(Expression x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new Pow.
     *
     * @param x the x
     * @param y the y
     */
    public Pow(double x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new Pow.
     *
     * @param x the x
     * @param y the y
     */
    public Pow(Expression x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new Pow.
     *
     * @param x the x
     * @param y the y
     */
    public Pow(String x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new Pow.
     *
     * @param x the x
     * @param y the y
     */
    public Pow(double x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new Pow.
     *
     * @param x the x
     * @param y the y
     */
    public Pow(double x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new Pow.
     *
     * @param x the x
     * @param y the y
     */
    public Pow(String x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new Pow.
     *
     * @param x the x
     * @param y the y
     */
    public Pow(String x, String y) {
        super(x, y);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.pow(super.getx().evaluate(assignment), super.gety().evaluate(assignment));
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
        return "(" + super.getx().toString() + "^" + super.gety().toString() + ")";
    }

    @Override
    public Expression assign(String sting, Expression expression) {
        return new Pow(super.getx().assign(sting, expression),
                super.gety().assign(sting, expression));
    }

    @Override
    public Expression differentiate(String st) {

        if (super.getVariables().contains(st)) {

            Expression left = this;
            Expression right = new Plus(
                    new Mult(super.getx().differentiate(st),
                            new Div(super.gety(), super.getx())),
                    new Mult(super.gety().differentiate(st),
                            new Log(new Var("e"), super.getx())));
            return new Mult(left, right);
        }
        return new Num(0);

    }

    @Override
    public Expression simplify() {
        Expression y = super.gety().simplify();
        Expression x = super.getx().simplify();

        try {
            if (x.evaluate() == 0.00) {
                return new Num(0);
            }
        } catch (Exception except) {
            int i = 1;
        }
        try {
            if (y.evaluate() == 0.00) {
                return new Num(1);
            }
        } catch (Exception except) {
            int i = 1;
        }
        try {
            if (y.evaluate() == 1.00) {
                return x;
            }
        } catch (Exception except) {
            int i = 1;
        }
        try {
            if (x.simplify().evaluate() == 1.00) {
                return new Num(1.0);
            }
        } catch (Exception except) {
            int i = 1;
        }
        try {
            return new Num(this.evaluate());
        } catch (Exception except) {
            int i = 1;
        }
        return new Pow(x, y);
    }


    /**
     * Bonus expression.
     *
     * @return the expression
     */
    public Expression bonus() {

        Expression y = super.gety().simplify();
        Expression x = super.getx().simplify();
        try {
            return new Pow(x, y.evaluate());
        } catch (Exception e) {
            int i = 0;
        }

        if (x instanceof Pow) {
            try {
                return new Pow(((Pow) x).getx(), new Mult(((Pow) x).gety(), y).evaluate()).bonus();
            } catch (Exception e) {
                return new Pow(((Pow) x).getx(), new Mult(((Pow) x).gety(), y)).bonus();
            }

        }
        if (y instanceof Pow) {
            try {
                return new Pow(x, new Mult(((Pow) y).getx(), ((Pow) y).gety()).simplify().evaluate()).bonus();
            } catch (Exception e) {
                return new Pow(x, new Mult(((Pow) y).getx(), ((Pow) y).gety())).bonus();
            }

        }
        if (y instanceof Minus) {
            try {
                return new Pow(x, new Minus(((Pow) y).getx(), ((Pow) y).gety()).simplify().evaluate()).bonus();
            } catch (Exception e) {
                return new Pow(x, y).simplify();
            }
        } else {
            return new Pow(x, y).simplify();
        }


    }

}

