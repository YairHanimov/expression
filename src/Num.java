import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
/**
 * The type Num.
 */
public class Num implements Expression {


    private double x;


    /**
     * Instantiates a new Num.
     *
     * @param x the x
     */
    public Num(double x) {
        this.x = x;
    }


    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.x;
    }

    @Override
    public double evaluate() throws Exception {
        Map<String, Double> empty = new TreeMap<String, Double>();
        return this.evaluate(empty);
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }
    @Override
    public String toString() {
        return Double.toString(this.x);
    }

    @Override
    public Expression differentiate(String s) {
        return new Num(0);
    }

    @Override
    public Expression simplify() {

        return this;
    }
}

