package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainFormController {
    public TextArea txtEditor;
    public Button btnRun;
    public TextArea txtOutput;

    public void btnRun_OnAction(ActionEvent event) {
    try{
        String data = "public class DEP8IDEDemo{\n" + "public static void main(String args[]){\n" + txtEditor.getText() + "\n}\n" + "}";

        String tempDir = System.getProperty("java.io.tmpdir");
        Path tempFilePath = Paths.get(tempDir, "DEP8IDEDemo.java");
        Files.write(tempFilePath, data.getBytes());

        Process javac = Runtime.getRuntime().exec("javac " + tempFilePath);
        int exitCode = javac.waitFor();

    }catch(IOException | InterruptedException e){
        e.printStackTrace();
    }

    }
}
