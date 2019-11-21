import java.util.List;

/**
 * The type Unary expression.
 */
public abstract class UnaryExpression implements Expression {
    private Expression x;


    /**
     * Instantiates a new Unary expression.
     *
     * @param x the x
     */
    public UnaryExpression(double x) {
        this.x = new Num(x);
    }

    /**
     * Instantiates a new Unary expression.
     *
     * @param x the x
     */
    public UnaryExpression(int x) {
        this.x = new Num(x);
    }

    /**
     * Instantiates a new Unary expression.
     *
     * @param x the x
     */
    public UnaryExpression(String x) {
        this.x = new Var(x);
    }

    /**
     * Instantiates a new Unary expression.
     *
     * @param x the x
     */
    public UnaryExpression(Expression x) {
        this.x = x;
    }

    /**
     * Gets .
     *
     * @return the
     */
    public Expression getme() {
        return this.x;
    }
    @Override
    public List<String> getVariables() {
        return this.x.getVariables();
    }

    @Override
    public String toString() {
        return this.x.toString();
    }
}
