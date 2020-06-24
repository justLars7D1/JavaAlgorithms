package DifferentialEquations;

import LinearAlgebra.Vector;

public class Test {

    public static void main(String[] args) {

        ODEFunction ex = new ODEFunction() {
            @Override
            public Vector evaluate(double t, Vector y) {
                Vector v = new Vector(1);
                v.set(0, t);
                return v;
            }
        };

        ClassicalRK4 RK4 = new ClassicalRK4(1);
        Vector oneD = new Vector(1);
        oneD.set(0, 0);
        System.out.println(RK4.solve(ex, 0, 1, oneD));

    }

}
