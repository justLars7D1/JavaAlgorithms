package LeastSquares;

import LeastSquares.function.Function;

public class Basis {

    private final Function[] basisElements;

    public Basis(final Function ... functions) {
        this.basisElements = functions;
    }

    public int getNumElements() {
        return basisElements.length;
    }

    public Function[] getBasisElements() {
        return basisElements;
    }
}
