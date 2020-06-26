package MachineLearning.NeuralNetwork.Activations;

public class BinaryStep implements ActivationFunctions {
    public double evaluate(double x) {
        if (x <= 0) return 0;
        else return 1;
    }
}
