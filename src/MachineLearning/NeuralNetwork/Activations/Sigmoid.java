package MachineLearning.NeuralNetwork.Activations;

public class Sigmoid implements Activation {
    public double evaluate(double x) {
        return 1/(1+Math.exp(-x));
    }

    @Override
    public double evalDerivative(double x) {
        double eval = evaluate(x);
        return eval*(1 - eval);
    }
}
