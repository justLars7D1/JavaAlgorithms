package MachineLearning.NeuralNetwork.Activations;

public class ReLu implements ActivationFunctions{
    public double evaluate(double x) {
        if (x <= 0) return 0;
        else return x;
    }
}
