package LeastSquares.function;

public abstract class Function {

    String func;

    public Function(String func) {
        this.func = func;
    }

    public abstract double evaluate(final double ... X);

    @Override
    public String toString() {
        return func;
    }
}
