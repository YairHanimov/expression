import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Base expression.
 */
abstract class BaseExpression implements Expression {

    private Expression x;
    private List<String> list = new ArrayList<>();


    /**
     * Gets varibles list.
     *
     * @return the varibles list
     */
    public List getVariblesList() {
        return list;
    }
    @Override
    public double evaluate() throws Exception {
        Map<String, Double> empty = new TreeMap<String, Double>();
        return this.evaluate(empty);
    }
}
