package MachineLearning.NeuralNetwork.Activations;

public class ISRLU extends ISRU implements ActivationFunctions {
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
