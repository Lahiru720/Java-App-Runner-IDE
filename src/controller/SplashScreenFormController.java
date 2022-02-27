package controller;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
//import util.DBConnection;DBConnection

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SplashScreenFormController {
    private final SimpleDoubleProperty progress = new SimpleDoubleProperty(0.0);
    private final SimpleStringProperty statusText = new SimpleStringProperty("Initializing...");
    public Label lblStatus;
    public ProgressBar pgb;

    public void initialize() {
        lblStatus.textProperty().bind(statusText);
        pgb.progressProperty().bind(progress);

      
    }


    private void createDB() throws Throwable {


    }



}
