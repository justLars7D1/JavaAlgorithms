package NeuralNetwork.Activations;

public class Sinc implements ActivationFunctions {
    public double evaluate(double x) {
        if (x == 0) return 1;
        else return (Math.sin(x)/x);
    }
}
