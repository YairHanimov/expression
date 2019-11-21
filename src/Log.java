
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Log.
 */
public class Log extends BinaryExpression implements Expression {


    /**
     * Instantiates a new Log.
     *
     * @param x the x
     * @param y the y
     */
    public Log(Expression x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new Log.
     *
     * @param x the x
     * @param y the y
     */
    public Log(Expression x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new Log.
     *
     * @param x the x
     * @param y the y
     */
    public Log(double x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new Log.
     *
     * @param x the x
     * @param y the y
     */
    public Log(Expression x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new Log.
     *
     * @param x the x
     * @param y the y
     */
    public Log(String x, Expression y) {
        super(x, y);
    }

    /**
     * Instantiates a new Log.
     *
     * @param x the x
     * @param y the y
     */
    public Log(double x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new Log.
     *
     * @param x the x
     * @param y the y
     */
    public Log(double x, String y) {
        super(x, y);
    }

    /**
     * Instantiates a new Log.
     *
     * @param x the x
     * @param y the y
     */
    public Log(String x, double y) {
        super(x, y);
    }

    /**
     * Instantiates a new Log.
     *
     * @param x the x
     * @param y the y
     */
    public Log(String x, String y) {
        super(x, y);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.log(super.gety().evaluate(assignment)) / Math.log(super.getx().evaluate(assignment));
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
        return "log" + "(" + super.getx().toString() + "," + super.gety().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(super.getx().assign(var, expression),
                super.gety().assign(var, expression));
    }
    @Override
    public Expression differentiate(String sting) {

        if (super.getx().getVariables().contains(sting)) {
            return new Div(new Log("e", super.gety()),
                    new Log("e", super.getx().differentiate(sting)));
        }
        return new Div(super.gety().differentiate(sting),
                new Mult(super.gety(), new Log(new Var("e"), super.getx())));
    }
    @Override
    public Expression simplify() {
        Expression x = super.getx().simplify();
        Expression y = super.gety().simplify();

        if (x.toString().equals(y.toString())) {
            return new Num(1.0);
        }
        try {
            if (x.evaluate() == 1.0) {
                return new Num(0);
            }
        } catch (Exception e) {
            int i = 0;
        }

        try {
            return new Num(this.evaluate());
        } catch (Exception ex) {
            int i = 0;
        }
        return new Log(x.simplify(), y.simplify());
    }

}
