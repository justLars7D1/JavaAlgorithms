package MachineLearning.NeuralNetwork.Activations;

public class Sin implements ActivationFunctions {
    public double evaluate(double x) {
        return Math.sin(x);
    }
}
