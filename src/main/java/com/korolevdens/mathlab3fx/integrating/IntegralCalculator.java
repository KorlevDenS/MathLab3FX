package com.korolevdens.mathlab3fx.integrating;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class IntegralCalculator {

    public final Integral integral;

    public IntegralCalculator(Integral integral) {
        this.integral = integral;
    }

    public void calculate() throws WrongInputException, IncorrectCalculationException {
        long startTime = System.nanoTime();

        integral.setNumberOfPartitions(2);
        double I_0 = integrate();
        while (true) {
            double I1 = integrate();

            //System.out.println(I1 + "   " + integral.getNumberOfPartitions());

            if (checkRungeRule(I_0, I1, integral.getMethod().getAccuracyOrder())) {
                integral.setResult(roundByAccuracy(I1));
                break;
            } else I_0 = I1;
        }

        long endTime = System.nanoTime();
        this.integral.setCalculationTime((endTime - startTime)/1000000000d);
    }

    private double roundByAccuracy(double res) {
        int newScale = BigDecimal.valueOf(integral.getAccuracy()).scale();
        BigDecimal newRes = BigDecimal.valueOf(res).setScale(newScale, RoundingMode.HALF_UP);
        return newRes.doubleValue();
    }

    private double integrate() throws WrongInputException, IncorrectCalculationException {
        integral.setNumberOfPartitions(integral.getNumberOfPartitions() * 2);
        double I;
        double H_0 = (integral.getBorderB() - integral.getBorderA()) / integral.getNumberOfPartitions();
        switch (integral.getMethod()) {
            case LEFT_RECTANGLE_METHOD -> I = calc_left_rectangle(integral.getNumberOfPartitions(), H_0);
            case RIGHT_RECTANGLE_METHOD -> I = calc_right_rectangle(integral.getNumberOfPartitions(), H_0);
            case MIDDLE_RECTANGLE_METHOD -> I = calc_middle_rectangle(integral.getNumberOfPartitions(), H_0);
            case TRAPEZOID_METHOD -> I = calc_trapezoid(integral.getNumberOfPartitions(), H_0);
            case SIMPSON_METHOD -> I = calc_simpson(integral.getNumberOfPartitions(), H_0);
            default -> throw new RuntimeException();
        }
        return I;
    }

    private boolean checkRungeRule(double I0, double I1, int accuracyOrder) {
        //System.out.println(Math.abs(I1 - I0) / Math.pow(2, accuracyOrder) + "  ?  " + this.integral.getAccuracy());
        return (Math.abs(I1 - I0) / Math.pow(2, accuracyOrder)) < this.integral.getAccuracy();
    }

    private double calc_left_rectangle(int n, double h) throws WrongInputException, IncorrectCalculationException {
        double sum = 0;
        double x = integral.getBorderA();
        for (int i = 1; i <= n; i++) {
            sum += integral.calcExpression(x);
            x += h;
        }
        return sum * h;
    }

    private double calc_right_rectangle(int n, double h) throws WrongInputException, IncorrectCalculationException {
        double sum = 0;
        double x = integral.getBorderA() + h;
        for (int i = 1; i <= n; i++) {
            sum += integral.calcExpression(x);
            x += h;
        }
        return sum * h;
    }

    private double calc_middle_rectangle(int n, double h) throws WrongInputException, IncorrectCalculationException {
        double sum = 0;
        double x = integral.getBorderA() + h/2;
        for (int i = 1; i <= n; i++) {
            sum += integral.calcExpression(x);
            x += h;
        }
        return sum * h;
    }

    private double calc_trapezoid(int n, double h) throws WrongInputException, IncorrectCalculationException {
        double sum = 0;
        double x = integral.getBorderA() + h;
        for (int i = 1; i <= n - 1; i++) {
            sum += integral.calcExpression(x);
            x += h;
        }
        sum += (integral.calcExpression(integral.getBorderA()) + integral.calcExpression(integral.getBorderB())) / 2;
        return sum * h;
    }

    private double calc_simpson(int n, double h) throws WrongInputException, IncorrectCalculationException {
        double sum = 0;
        double x = integral.getBorderA() + h;
        for (int i = 1; i <= n - 1; i++) {
            if (i % 2 == 0) sum += 2 * integral.calcExpression(x);
            else sum += 4 * integral.calcExpression(x);
            x += h;
        }
        sum += integral.calcExpression(integral.getBorderA()) + integral.calcExpression(integral.getBorderB());
        return sum * h/3;
    }
}
