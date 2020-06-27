package MachineLearning.NeuralNetwork.Activations;

public class ReLu implements Activation {
    public double evaluate(double x) {
        if (x <= 0) return 0;
        else return x;
    }

    @Override
    public double evalDerivative(double x) {
        if (x <= 0) return 0;
        else return 1;
    }
}
