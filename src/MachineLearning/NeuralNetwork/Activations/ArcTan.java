package MachineLearning.NeuralNetwork.Activations;

public class ArcTan implements ActivationFunctions{
    public double evaluate(double x) {
        return Math.atan(x);
    }
}
