module ma.enset.tp_qlearning_sma {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires jade;

    opens ma.enset.tp_qlearning_sma to javafx.fxml;
    exports ma.enset.tp_qlearning_sma;
    exports ma.enset.tp_qlearning_sma.sequential to javafx.graphics;
    exports ma.enset.tp_qlearning_sma.sma to jade;
}