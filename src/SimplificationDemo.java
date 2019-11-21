


/**
 * The type Testt.
 */
public class SimplificationDemo {


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        Expression e = new Neg(new Neg(new Neg("x")));
        System.out.println("non-simplified: " + e.simplify());
        System.out.println("bonus - simplifed:" + ((Neg) e).bonus());
        System.out.println();
        System.out.println();
        Expression e2 = new Pow(new Pow("x", "y"), "z");
        Expression e3 = new Pow(new Pow(new Pow("x", "y"), "z"), "k");
        System.out.println("non - simplified: " + e2.simplify());
        System.out.println("bonus - simplifed:" + ((Pow) e2).bonus());
        System.out.println();
        System.out.println("non - simplified: " + e3.simplify());
        System.out.println("bonus - simplifed:" + ((Pow) e3).bonus());
        System.out.println();
        Expression e4 = new Plus(new Mult(2, "x"), new Mult(4, "x"));
        System.out.println("non - simplified: " + e4.simplify());
        System.out.println("bonus - simplifed:" + ((Plus) e4).bonus());
        System.out.println();
        Expression e5 = new Div(new Mult("x", 2), new Mult("x", 4));
        System.out.println("non-simplified: " + e5.simplify());
        System.out.println("bonus - simplifed:" + ((Div) e5).bonus());
        System.out.println();
        Expression e6 = new Div(new Pow("x", "Y"), new Pow("x", "Z"));
        System.out.println("non-simplified: " + e6.simplify());
        System.out.println("bonus - simplifed:" + ((Div) e6).bonus());
        System.out.println();
        Expression e7 = new Sin(new Mult(2, "x"));
        System.out.println("non-simplified: " + e7);
        System.out.println("bonus - simplifed:" + ((Sin) e7).bonus());
        System.out.println();
        Expression e8 = new Div(new Sin(new Mult(3, "x")), new Cos(new Mult(3, "x")));
        System.out.println("non-simplified: " + e8);
        System.out.println("bonus - simplifed: " + ((Div) e8).bonus());
        System.out.println();
        Expression e10 = new Plus(new Pow(new Sin("x"), 2), (new Pow(new Cos("x"), 2)));
        System.out.println("non-simplified: " + e10);
        System.out.println("bonus - simplifed: " + ((Plus) e10).bonus());
    }
}