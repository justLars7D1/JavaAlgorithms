package MachineLearning.NeuralNetwork.Activations;

public class LeakyReLu implements ActivationFunctions {
    public double evaluate(double x) {
        if (x <= 0) return 0.01*x;
        else return x;
    }
}
