module project.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.oracle.database.jdbc;
    requires java.sql;
    requires java.desktop;
    requires jasperreports;
    requires AnimateFX;


    opens project.project to javafx.fxml;
    exports project.project;
}