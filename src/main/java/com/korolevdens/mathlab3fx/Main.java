package com.korolevdens.mathlab3fx;

import com.korolevdens.mathlab3fx.integrating.*;

public class Main {

    public static void main(String[] args) throws WrongInputException, IncorrectCalculationException {


        Integral integral = new Integral("(1/10)x^4 + (1/5)x^2 - 7", 1, 2, 0.0001, Methods.LEFT_RECTANGLE_METHOD);

        IntegralCalculator integralCalculator = new IntegralCalculator(integral);
        integralCalculator.calculate();
        System.out.println(integral.getResult());
        System.out.println(integral.getNumberOfPartitions());



        Integral integral1 = new Integral("(1/10)x^4 + (1/5)x^2 - 7", 1, 2, 0.0001, Methods.RIGHT_RECTANGLE_METHOD);

        IntegralCalculator integralCalculator1 = new IntegralCalculator(integral1);
        integralCalculator1.calculate();
        System.out.println(integral1.getResult());
        System.out.println(integral1.getNumberOfPartitions());


        Integral integral2 = new Integral("(1/10)x^4 + (1/5)x^2 - 7", 1, 2, 0.0001, Methods.MIDDLE_RECTANGLE_METHOD);

        IntegralCalculator integralCalculator2 = new IntegralCalculator(integral2);
        integralCalculator2.calculate();
        System.out.println(integral2.getResult());
        System.out.println(integral2.getNumberOfPartitions());


        Integral integral3 = new Integral("(1/10)x^4 + (1/5)x^2 - 7", 1, 2, 0.0001, Methods.TRAPEZOID_METHOD);

        IntegralCalculator integralCalculator3 = new IntegralCalculator(integral3);
        integralCalculator3.calculate();
        System.out.println(integral3.getResult());
        System.out.println(integral3.getNumberOfPartitions());


        Integral integral4 = new Integral("(1/10)x^4 + (1/5)x^2 - 7", 1, 2, 0.0001, Methods.SIMPSON_METHOD);

        IntegralCalculator integralCalculator4 = new IntegralCalculator(integral4);
        integralCalculator4.calculate();
        System.out.println(integral4.getResult());
        System.out.println(integral4.getNumberOfPartitions());

    }
}
