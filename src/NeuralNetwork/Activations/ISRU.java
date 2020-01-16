package NeuralNetwork.Activations;

public class ISRU implements ActivationFunctions{

    protected double alpha;

    public ISRU() {
        this.alpha = 1;
    }

    public ISRU(double alpha) {
        this.alpha = alpha;
    }

    public double evaluate(double x) {
        return x/(Math.sqrt(1+(alpha*x*x)));
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

}
