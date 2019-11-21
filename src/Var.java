import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Var.
 */
public class Var implements Expression {
    /**
     * The X.
     */
   private  String x;


    /**
     * Instantiates a new Var.
     *
     * @param x the x
     */
    public Var(String x) {
        this.x = x;
    }

    /**
     * Instantiates a new Var.
     *
     * @param x the x
     */
    public Var(double x) {
        this.x = Double.toString(x);
    }

    /**
     * Instantiates a new Var.
     *
     * @param x the x
     */
    public Var(int x) {
        this.x = Integer.toString(x);
    }

    /**
     * Instantiates a new Var.
     *
     * @param x the x
     */
    public Var(Expression x) {
        double value;
        value = Double.parseDouble(x.toString());
        this.x = Double.toString(value);
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return assignment.get(this.x);
    }

    @Override
    public double evaluate() throws Exception {
        if (x.equals("e")) {
            Map<String, Double> assignment = new TreeMap<String, Double>();
            assignment.put("e", Math.E);
            return this.evaluate(assignment);
        }
        if (x.equals("Pi")) {
            Map<String, Double> assignment = new TreeMap<String, Double>();
            assignment.put("Pi", Math.PI);
            return this.evaluate(assignment);
        }
        return this.evaluate(Collections.emptyMap());
    }


    @Override
    public List<String> getVariables() {
        List list = new ArrayList<>();
        list.add(this.x);
        return list;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.x.equals(var)) {
            return expression;
        } else {
            return this;
        }
    }

    @Override
    public Expression differentiate(String var) {
        if (x.equals(var)) {
            return new Num(1);
        }
        return new Num(0);
    }
    @Override
    public String toString() {
        return this.x;
    }
    @Override
    public Expression simplify() {
        return this;
    }


}



