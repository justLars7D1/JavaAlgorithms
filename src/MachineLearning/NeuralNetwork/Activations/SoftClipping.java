package MachineLearning.NeuralNetwork.Activations;

import Mathematics.Functions.ScalarFunction;

public class SoftClipping implements ScalarFunction {

    private double alpha;

    public double evaluate(double x) {
        return (1/alpha)*(Math.log10((1+Math.exp(alpha*x))/(1+Math.exp(alpha*(x-1)))));
    }

    public SoftClipping() {
        this.alpha = 1;
    }

    public SoftClipping(double alpha) {
        this.alpha = alpha;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }
}
