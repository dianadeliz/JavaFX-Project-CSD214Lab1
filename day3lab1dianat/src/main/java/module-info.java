module com.trios.day3lab1dianat {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires java.sql;

    opens com.trios.day3lab1dianat to javafx.fxml;
    exports com.trios.day3lab1dianat;
}