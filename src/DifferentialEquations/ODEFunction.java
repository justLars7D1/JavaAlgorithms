package DifferentialEquations;

import LinearAlgebra.Vector;

public abstract class ODEFunction {

    public abstract Vector evaluate(double t, Vector y);

}
