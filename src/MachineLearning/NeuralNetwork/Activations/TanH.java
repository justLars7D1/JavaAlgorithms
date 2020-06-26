package MachineLearning.NeuralNetwork.Activations;

public class TanH implements ActivationFunctions {
    public double evaluate(double x) {
        return Math.tanh(x);
    }
}
