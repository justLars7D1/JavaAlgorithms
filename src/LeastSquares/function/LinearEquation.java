package LeastSquares.function;

import LeastSquares.Basis;

import java.util.Arrays;

public class LinearEquation {

    private final Basis basis;
    private final double[] coefficients;

    public LinearEquation(final Basis basis, final double[] coefficients) {
        assert basis.getNumElements() == coefficients.length;
        this.basis = basis;
        this.coefficients = coefficients;
    }

    public double[] evaluate(final double[] xs) {
        int numDataPoints = xs.length;

        double[] ys = new double[numDataPoints];
        for (int i = 0; i < numDataPoints; i++) {
            ys[i] = evaluate(xs[i]);
        }

        return ys;
    }

    public double evaluate(final double x) {
        double y = 0;
        int numBasisFunctions = basis.getNumElements();
        Function[] basisFunctions = basis.getBasisElements();

        for (int i = 0; i < numBasisFunctions; i++) {
            y += coefficients[i] * basisFunctions[i].evaluate(x);
        }

        return y;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("l(x) = ");

        int numComponents = basis.getNumElements();
        for (int i = 0; i < numComponents; i++) {
            res.append(coefficients[i]).append("*").append("φ(").append(i).append(")");
            if (i + 1 != numComponents) {
                res.append(" + ");
            }
        }

        return res.toString();
    }
}
