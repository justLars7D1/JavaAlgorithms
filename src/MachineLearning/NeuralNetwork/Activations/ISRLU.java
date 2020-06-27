package MachineLearning.NeuralNetwork.Activations;

import Mathematics.Functions.ScalarFunction;

public class ISRLU extends ISRU implements ScalarFunction {
    @Override
    public double evaluate(double x) {
        if (x <= 0) return x/(Math.sqrt(1+(alpha*x*x)));
        else return x;
    }

    public ISRLU() {
        super();
    }

    public ISRLU(double alpha) {
        super(alpha);
    }
}
