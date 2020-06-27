package MachineLearning.NeuralNetwork.Activations;

import Mathematics.Functions.ScalarFunction;

public class Sinc implements ScalarFunction {
    public double evaluate(double x) {
        if (x == 0) return 1;
        else return (Math.sin(x)/x);
    }
}
