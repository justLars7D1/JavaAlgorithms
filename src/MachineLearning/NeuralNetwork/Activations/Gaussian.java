package MachineLearning.NeuralNetwork.Activations;

public class Gaussian implements ActivationFunctions {
    public double evaluate(double x) {
        return Math.exp(-1*x*x);
    }
}
