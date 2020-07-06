package Mathematics.LinearAlgebra;

import Mathematics.LinearAlgebra.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    @Test
    void multiplicationTest(){
        Matrix m1 = new Matrix(new double[][]{{1,2,3},{4,5,6}});
        Matrix m2 = new Matrix(new double[][]{{1,2},{3,4},{5,6}});
        Matrix mult = new Matrix(new double[][]{{22,28},{49,64}});
        assertTrue(m1.multiply(m2).equals(mult));
    }
}
