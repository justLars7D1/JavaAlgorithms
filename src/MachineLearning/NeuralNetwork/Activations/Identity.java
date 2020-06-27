package MachineLearning.NeuralNetwork.Activations;

import Mathematics.Functions.ScalarFunction;

public class Identity implements ScalarFunction {
    public double evaluate(double x) {
        return x;
    }
}
