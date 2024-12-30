module org.example.movierating {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;



    opens org.example.movierating.model to javafx.base;

    opens org.example.movierating to javafx.fxml;
    exports org.example.movierating;
}