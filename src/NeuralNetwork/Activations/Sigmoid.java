package NeuralNetwork.Activations;

public class Sigmoid implements ActivationFunctions{
    public double evaluate(double x) {
        return 1/(1+Math.exp(-x));
    }
}
