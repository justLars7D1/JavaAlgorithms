package NumericalAnalysis.DifferentialEquations;

import LinearAlgebra.Vector;

public class ClassicalRK4 extends ODESolver {

    public ClassicalRK4(double stepSize) {
        super(stepSize);
    }

    public ClassicalRK4() {
    }

    @Override
    protected Vector approximateNext(ODEFunction function, Vector currentValue, double step) {
        Vector ki1 = ki1(function, currentValue, step);
        Vector ki2 = ki2(function, currentValue, step, ki1);
        Vector ki3 = ki3(function, currentValue, step, ki2);
        Vector ki4 = ki4(function, currentValue, step, ki3);
        ki2.scale(2); ki3.scale(2);
        ki1.add(ki2); ki1.add(ki3); ki1.add(ki4);
        ki1.scale(1./6.);
        return ki1;
    }

    private Vector ki1(ODEFunction function, Vector currentValue, double step) {
        Vector funcRes = function.evaluate(step, currentValue);
        funcRes.scale(stepSize);
        return funcRes;
    }

    private Vector ki2(ODEFunction function, Vector currentValue, double step, Vector ki1) {
        Vector val = currentValue.getAdded(ki1.getScaled(0.5));
        Vector funcRes = function.evaluate(step + 0.5*stepSize, val);
        funcRes.scale(stepSize);
        return funcRes;
    }

    private Vector ki3(ODEFunction function, Vector currentValue, double step, Vector ki2) {
        Vector funcRes = function.evaluate(step + 0.5*stepSize, currentValue.getAdded(ki2.getScaled(0.5)));
        funcRes.scale(stepSize);
        return funcRes;
    }

    private Vector ki4(ODEFunction function, Vector currentValue, double step, Vector ki3) {
        Vector funcRes = function.evaluate(step + stepSize, currentValue.getAdded(ki3));
        funcRes.scale(stepSize);
        return funcRes;
    }

}
