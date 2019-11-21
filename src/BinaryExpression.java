import java.util.List;


/**
 * The type Binary expression.
 */
public abstract class BinaryExpression implements Expression {
    private Expression x;
    private Expression y;


    /**
     * Instantiates a new Binary expression.
     *
     * @param x the x
     * @param y the y
     */
    public BinaryExpression(Expression x, Expression y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Instantiates a new Binary expression.
     *
     * @param x the x
     * @param y the y
     */
    public BinaryExpression(Expression x, double y) {
        this.x = x;
        this.y = new Num(y);
    }

    /**
     * Instantiates a new Binary expression.
     *
     * @param x the x
     * @param y the y
     */
    public BinaryExpression(double x, Expression y) {
        this.x = new Num(x);
        this.y = y;
    }

    /**
     * Instantiates a new Binary expression.
     *
     * @param x the x
     * @param y the y
     */
    public BinaryExpression(Expression x, String y) {
        this.x = x;
        this.y = new Var(y);
    }

    /**
     * Instantiates a new Binary expression.
     *
     * @param x the x
     * @param y the y
     */
    public BinaryExpression(String x, Expression y) {
        this.x = new Var(x);
        this.y = y;
    }

    /**
     * Instantiates a new Binary expression.
     *
     * @param x the x
     * @param y the y
     */
    public BinaryExpression(double x, double y) {
        this.x = new Num(x);
        this.y = new Num(y);
    }

    /**
     * Instantiates a new Binary expression.
     *
     * @param x the x
     * @param y the y
     */
    public BinaryExpression(double x, String y) {
        this.x = new Num(x);
        this.y = new Var(y);
    }

    /**
     * Instantiates a new Binary expression.
     *
     * @param x the x
     * @param y the y
     */
    public BinaryExpression(String x, double y) {
        this.x = new Var(x);
        this.y = new Num(y);
    }

    /**
     * Instantiates a new Binary expression.
     *
     * @param x the x
     * @param y the y
     */
    public BinaryExpression(String x, String y) {
        this.x = new Var(x);
        this.y = new Var(y);
    }

    /**
     * Gets .
     *
     * @return the
     */
    public Expression getx() {
        return this.x;
    }

    /**
     * Gets .
     *
     * @return the
     */
    public Expression gety() {
        return this.y;
    }

    /**
     * To stringx string.
     *
     * @return the string
     */
    public String toStringx() {
        return this.x.toString();
    }

    @Override
    public List<String> getVariables() {
        List<String> listx = getx().getVariables();
        List<String> listy = gety().getVariables();
        for (int i = 0; i < listy.size(); i++) {
            if (!listx.contains(listy.get(i))) {
                listx.add(listy.get(i));
            }
        }
        return listx;
    }
}

