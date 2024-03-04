package org.example.simpleststepfunctionwebapp;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class FunctionService {

    public double calculateY(double b, double x) {
        if (x <= 0.45) {
            return b * x - Math.tan(b * x);
        } else {
            return b * x + Math.log10(b * x);
        }
    }

    public int calculateNumberOfSteps(StepFunction function) {
        return (int) ((function.getEndX() - function.getInitialX()) / function.getStep()) + 1;
    }

    public double[] createYArray(StepFunction function) {
        int size = calculateNumberOfSteps(function);
        double[] yArray = new double[size];
        double x = function.getInitialX();
        for (int i = 0; i < size; i++) {
            yArray[i] = calculateY(function.getB(), x);
            x += function.getStep();
        }
        return yArray;
    }

    public double[] createXArray(StepFunction function) {
        int size = calculateNumberOfSteps(function);
        double[] xArray = new double[size];
        double x = function.getInitialX();
        for (int i = 0; i < size; i++) {
            xArray[i] = x;
            x += function.getStep();
        }
        return xArray;
    }

    public int getIndexOfMaxValueOfY(StepFunction function) {
        double[] yArray = createYArray(function);
        double maxY = yArray[0];
        int maxYIndex = 0;
        for (int i = 1; i < yArray.length; i++) {
            if (yArray[i] > maxY) {
                maxY = yArray[i];
                maxYIndex = i;
            }
        }
        return maxYIndex;
    }

    public int getIndexOfMinValueOfY(StepFunction function) {
        double[] yArray = createYArray(function);
        double minY = yArray[0];
        int minYIndex = 0;
        for (int i = 1; i < yArray.length; i++) {
            if (yArray[i] < minY) {
                minY = yArray[i];
                minYIndex = i;
            }
        }
        return minYIndex;
    }

    public double getYValuesSum(StepFunction function) {
        double[] yArray = createYArray(function);
        return Arrays.stream(yArray).sum();
    }

    public double getArithmeticMeanOfYValues(StepFunction function) {
        return getYValuesSum(function) / calculateNumberOfSteps(function);
    }

    public String getMaxValueOfY(StepFunction function) {
        int indexOfMaxValueOfY = getIndexOfMaxValueOfY(function);
        double[] yArray = createYArray(function);
        double maxY = yArray[0];
        for (int i = 1; i < yArray.length; i++) {
            if (yArray[i] > maxY) {
                maxY = yArray[i];
            }
        }
        return "Max y has index " + indexOfMaxValueOfY + " and value " + maxY;
    }

    public String getMinValueOfY(StepFunction function) {
        int indexOfMinValueOfY = getIndexOfMinValueOfY(function);
        double[] yArray = createYArray(function);
        double minY = yArray[0];
        for (int i = 1; i < yArray.length; i++) {
            if (yArray[i] < minY) {
                minY = yArray[i];
            }
        }
        return "Min y has index " + indexOfMinValueOfY + " and value " + minY;
    }
}
