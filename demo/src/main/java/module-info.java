module ma.enset.tpjdbc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires  java.base;



    opens ma.enset.tpjdbc.Dao.entities to javafx.base;
    opens ma.enset.tpjdbc.presentation.controllers to javafx.fxml,javafx.controls;
    opens ma.enset.tpjdbc to javafx.fxml,javafx.graphics;
    exports ma.enset.tpjdbc to javafx.fxml, javafx.graphics;


}