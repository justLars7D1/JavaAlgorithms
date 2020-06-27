package NumericalAnalysis.DifferentialEquations;

import Mathematics.Functions.ODEFunction;
import Mathematics.LinearAlgebra.Vector;

public class Euler extends ODESolver {

    public Euler(double stepSize) {
        super(stepSize);
    }

    public Euler() {
    }

    @Override
    protected Vector approximateNext(ODEFunction function, Vector currentValue, double step) {
        Vector funcRes = function.evaluate(step, currentValue);
        funcRes.scale(stepSize);
        return funcRes;
    }
}
