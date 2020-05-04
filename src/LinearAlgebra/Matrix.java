package LinearAlgebra;

public class Matrix {

    private final double[][] grid;

    public Matrix(final double[][] grid) {
        this.grid = grid;
    }

    public Matrix(final int i, final int j) {
        grid = new double[i][j];
    }

    public Matrix(final Vector vector) {
        double[] vectorCoords = vector.getCoordinates();
        int dims = vector.getDimensions();

        grid = new double[dims][1];
        for (int i = 0; i < dims; i++) {
            grid[i][0] = vectorCoords[i];
        }
    }

    public double get(final int i, final int j) {
        return grid[i][j];
    }

    public void set(final int i, final int j, final double value) {
        grid[i][j] = value;
    }

    public Matrix multiply(Matrix otherMatrix) {
        int[] size = getSize(); int[] otherSize = otherMatrix.getSize();
        assert size[1] == otherSize[0];

        Matrix resultMatrix = new Matrix(size[0], otherSize[1]);

        for (int i = 0; i < size[0]; i++) {
            for (int j = 0; j < otherSize[1]; j++) {
                double tmpValue = 0;
                for (int k = 0; k < size[1]; k++) {
                    tmpValue += get(i, k) * otherMatrix.get(k, j);
                }
                resultMatrix.set(i, j, tmpValue);
            }
        }

        return resultMatrix;
    }

    public Vector multiply(Vector otherVector) {
        Matrix otherMatrix = new Matrix(otherVector);
        Matrix result = multiply(otherMatrix);
        return matrixToVector(result);
    }

    private static Vector matrixToVector(Matrix matrix) {
        int[] size = matrix.getSize();
        assert size[1] == 1;

        double[] coords = new double[size[0]];
        for (int i = 0; i < size[0]; i++) {
            coords[i] = matrix.get(i, 0);
        }

        return new Vector(coords);
    }

    public double[][] getGrid() {
        return grid;
    }

    public int[] getSize() {
        return new int[] {grid.length, grid[0].length};
    }

}
