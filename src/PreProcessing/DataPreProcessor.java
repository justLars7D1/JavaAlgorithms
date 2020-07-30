package PreProcessing;

import Mathematics.LinearAlgebra.Vector;

public class DataPreProcessor {

    private Vector mean;
    private Vector stDev;

    public DataPreProcessor(Vector[] dataPoints) {
        int dims = dataPoints[0].getDimensions();
        mean = new Vector(dims); stDev = new Vector(dims);

        calculateMean(dataPoints);
        calculateStDev(dataPoints);
    }

    private void calculateMean(Vector[] dps) {
        for (Vector dp : dps) {
            mean.add(dp);
        }
        mean.scale(1./dps.length);
    }

    private void calculateStDev(Vector[] dps) {
        for (Vector dp: dps) {
            Vector squaredErr = dp.getSubtracted(mean);
            squaredErr.exponentScale(2);
            stDev.add(squaredErr);
        }
        stDev.scale(1./dps.length);
        stDev.exponentScale(0.5);
    }

    public Vector[] standardize(Vector[] dps) {
        Vector[] newDps = new Vector[dps.length];
        for (int i = 0; i < dps.length; i++) {
            newDps[i] = dps[i].getSubtracted(mean);
            newDps[i].divide(stDev);
        }
        return newDps;
    }

    public Vector[] unstandardize(Vector[] dps) {
        Vector[] newDps = new Vector[dps.length];
        for (int i = 0; i < dps.length; i++) {
            newDps[i] = dps[i].getMultiplied(stDev);
            newDps[i].add(mean);
        }
        return newDps;
    }

    public Vector getMean() {
        return mean;
    }

    public Vector getStDev() {
        return stDev;
    }
}
