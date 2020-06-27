package Mathematics.Functions;

import Mathematics.LinearAlgebra.Vector;

public interface ODEFunction {

    Vector evaluate(double t, Vector y);

}
