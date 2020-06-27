package MachineLearning.NeuralNetwork.Activations;

import Mathematics.Functions.ScalarFunction;

public class ArcTan implements ScalarFunction {
    public double evaluate(double x) {
        return Math.atan(x);
    }
}
