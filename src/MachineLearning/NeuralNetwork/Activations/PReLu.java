package MachineLearning.NeuralNetwork.Activations;

import Mathematics.Functions.ScalarFunction;

public class PReLu implements ScalarFunction {

    protected double alpha;

    public double evaluate(double x) {
        if (x <= 0) return alpha*x;
        else return x;
    }

    public PReLu() {
        this.alpha = 1;
    }

    public PReLu(double alpha) {
        this.alpha = alpha;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

}
