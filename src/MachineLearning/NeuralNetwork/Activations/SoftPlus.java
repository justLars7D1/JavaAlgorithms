package MachineLearning.NeuralNetwork.Activations;

import Mathematics.Functions.ScalarFunction;

public class SoftPlus implements ScalarFunction {
    public double evaluate(double x) {
        return Math.log(1+Math.exp(x));
    }
}
