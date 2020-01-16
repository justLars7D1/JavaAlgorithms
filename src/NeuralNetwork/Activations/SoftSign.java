package NeuralNetwork.Activations;

public class SoftSign implements ActivationFunctions{
    public double evaluate(double x) {
        return x/(1+Math.abs(x));
    }
}
