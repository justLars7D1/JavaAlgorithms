package NumericalAnalysis.DifferentialEquations;

import LinearAlgebra.Vector;

public abstract class ODESolver {

    protected final double stepSize;
    private double roundOffError;

    public ODESolver() {
        stepSize = 10e-5;
        calculateRoundOffError(stepSize);
    }

    public ODESolver(double stepSize) {
        this.stepSize = stepSize;
        calculateRoundOffError(stepSize);
    }

    public Vector solve(ODEFunction function, double t0, double tf, Vector y0) {
        Vector result = (Vector) y0.clone();
        for (double step = t0; !((step - roundOffError < tf) && (step + roundOffError > tf)); step += stepSize) {
            result.add(approximateNext(function, result, step));
        }
        return result;
    }

    protected abstract Vector approximateNext(ODEFunction function, Vector currentValue, double step);

    private void calculateRoundOffError(double stepSize) {
        this.roundOffError = 10e-16 / stepSize;
    }

}
