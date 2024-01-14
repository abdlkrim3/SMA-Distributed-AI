module com.example.tp5smasecyrity {
    requires javafx.controls;
    requires javafx.fxml;


    opens ma.enset.tp5smasecyrity to javafx.fxml;
    exports ma.enset.tp5smasecyrity;
}