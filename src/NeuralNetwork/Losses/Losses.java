package NeuralNetwork.Losses;

public interface Losses {
    double calc_loss(double[] y_act, double[] y_pred);
}
