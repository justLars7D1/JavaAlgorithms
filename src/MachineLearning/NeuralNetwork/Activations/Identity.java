package MachineLearning.NeuralNetwork.Activations;

public class Identity implements ActivationFunctions {
    public double evaluate(double x) {
        return x;
    }
}
