package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainFormController {


    public TextArea txtOutput;
    public JFXTextArea txtEditor;
    public JFXButton btnRun;
    public JFXButton btnClear;

    public void btnRun_OnAction(ActionEvent event) {
        try {
            String data="public class JavaAppRunner{\n" +
                    "public static void main(String args[]){\n" +
                    txtEditor.getText()+"\n" +
                    "}\n" +
                    "}";
            String tmpDir = System.getProperty("java.io.tmpdir");
            Path tempFilePath = Paths.get(tmpDir, "JavaAppRunner.java");
            Files.write(tempFilePath,data.getBytes());

            Process javac = Runtime.getRuntime().exec("javac " + tempFilePath);
            int exitCode = javac.waitFor();

            if(exitCode==0){
                Process java = Runtime.getRuntime().exec("java -cp " + tmpDir + " JavaAppRunner");
                exitCode = java.waitFor();

                if(exitCode==0){
                    readStream(java.getInputStream());
                }else {
                    readStream(java.getErrorStream());
                }
            }
            else{
                readStream(javac.getErrorStream());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }finally {
            try {
                Path classFilePath = Paths.get(System.getProperty("java.io.tmpdir"), "JavaAppRunner.class");
                Path javaFilePath = Paths.get(System.getProperty("java.io.tmpdir"), "JavaAppRunner.java");
                Files.deleteIfExists(classFilePath);
                Files.deleteIfExists(javaFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    private void readStream(InputStream is) {
        try {
            byte[] buffer= new byte[is.available()];
            is.read(buffer);
            txtOutput.setText(new String(buffer));
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnClear_OnAction(ActionEvent event) {
        txtEditor.setText("");
        txtOutput.setText("");
    }
}
