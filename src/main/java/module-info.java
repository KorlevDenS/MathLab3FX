module com.korolevdens.mathlab3fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires exp4j;
    requires lombok;

    opens com.korolevdens.mathlab3fx to javafx.fxml;
    exports com.korolevdens.mathlab3fx;
    exports com.korolevdens.mathlab3fx.integrating;
    opens com.korolevdens.mathlab3fx.integrating to javafx.fxml;
}