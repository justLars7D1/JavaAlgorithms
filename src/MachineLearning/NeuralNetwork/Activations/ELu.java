package MachineLearning.NeuralNetwork.Activations;

public class ELu implements ActivationFunctions {

    private double alpha;

    public double evaluate(double x) {
        if (x <= 0) return alpha*(Math.exp(x)-1);
        else return x;
    }

    public ELu() {
        this.alpha = -1;
    }

    public ELu(double alpha) {
        this.alpha = alpha;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }
}
