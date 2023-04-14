package com.korolevdens.mathlab3fx.integrating;

import lombok.Getter;
import lombok.Setter;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@Getter
@Setter
public class Integral {

    private double calculationTime;
    private String expressionString;

    private double accuracy;

    private int numberOfPartitions;

    private Expression expression;
    private double borderA;
    private double borderB;

    // For keeping result after calculating or null value
    private Double result;

    // Keeps method used last time to manage output if it's necessary
    private Methods method;

    private boolean isConstructed;

    public Integral(String expression, double borderA, double borderB, double accuracy, Methods method) {
        this.expressionString = expression;
        this.borderA = borderA;
        this.borderB = borderB;
        this.accuracy = accuracy;
        this.method = method;
    }

    private void constructExpression() throws WrongInputException {
        this.expression = new ExpressionBuilder(expressionString).variable("x").build();
    }

    public double calcExpression(double x) throws WrongInputException, IncorrectCalculationException {
        try {
            if (!isConstructed) constructExpression();
            if (x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY || Double.isNaN(x))
                throw new IncorrectCalculationException("При подсчёте F(x) получилось число x = " + x
                        + ". Подсчёт данного интеграла невозможен.");
            this.expression.setVariable("x", x);
            return this.expression.evaluate();
        } catch (IncorrectCalculationException | WrongInputException e) {
            throw e;
        } catch (ArithmeticException e) {
            throw new IncorrectCalculationException("При подсчёте F(x) возникло деление на 0. Подсчёт данного интеграла невозможен.");
        } catch (Exception e) {
            throw new WrongInputException("Подынтегральное выражение введено некорректно.");
        }
    }
}
