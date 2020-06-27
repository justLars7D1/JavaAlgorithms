package MachineLearning.NeuralNetwork.Activations;

import Mathematics.Functions.ScalarFunction;

public class SoftSign implements ScalarFunction {
    public double evaluate(double x) {
        return x/(1+Math.abs(x));
    }
}
