package MachineLearning.NeuralNetwork.Activations;

public class SoftPlus implements ActivationFunctions {
    public double evaluate(double x) {
        return Math.log(1+Math.exp(x));
    }
}
