package com.korolevdens.mathlab3fx;

import com.korolevdens.mathlab3fx.integrating.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField expressionInput;

    @FXML
    private TextField borderAInput;

    @FXML
    private TextField borderBInput;

    @FXML
    private TextField accuracyInput;

    @FXML
    private Label error;

    @FXML
    private Label result;

    @FXML
    private Label time;

    @FXML
    private Label n;

    @FXML
    protected void onLeft() {
        startComputation(Methods.LEFT_RECTANGLE_METHOD);
    }

    @FXML
    protected void onRight() {
        startComputation(Methods.RIGHT_RECTANGLE_METHOD);
    }

    @FXML
    protected void onMiddle() {
        startComputation(Methods.MIDDLE_RECTANGLE_METHOD);
    }

    @FXML
    protected void onTrapezoid() {
        startComputation(Methods.TRAPEZOID_METHOD);
    }

    @FXML
    protected void onSimpson() {
        startComputation(Methods.SIMPSON_METHOD);
    }

    private void startComputation(Methods method) {
        String expression;
        double borderB;
        double borderA;
        double accuracy;
        try {
            expression = expressionInput.getText();
            borderB = Double.parseDouble(borderBInput.getText());
            borderA = Double.parseDouble(borderAInput.getText());
            accuracy = Double.parseDouble(accuracyInput.getText());
        } catch (Exception e) {
            error.setText("Не все поля введены верно.");
            error.setStyle("-fx-text-fill: red;");
            return;
        }

        Integral integral = new Integral(expression, borderA, borderB, accuracy, method);
        IntegralCalculator integralCalculator = new IntegralCalculator(integral);
        try {
            integralCalculator.calculate();
            time.setText("Время расчёта: " + integral.getCalculationTime() + " с.");
            n.setText("Число разбиения: " + integral.getNumberOfPartitions());
            error.setText("");
            result.setText("Приближенное решение: " + integral.getResult());

        } catch (WrongInputException | IncorrectCalculationException e) {
            error.setText(e.getMessage());
            error.setStyle("-fx-text-fill: red;");
            time.setText("-");
            n.setText("-");
            result.setText("-");
        }

    }


}