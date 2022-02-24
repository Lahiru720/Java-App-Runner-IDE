package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainFormController {
    public TextArea txtEditor;
    public Button btnRun;
    public TextArea txtOutput;

    public void btnRun_OnAction(ActionEvent event) throws IOException {
    try{
        String data = "public class JavaAppRunner{\n" + "public static void main(String args[]){\n" + txtEditor.getText() + "\n}\n" + "}";

        String tempDir = System.getProperty("java.io.tmpdir");
        Path tempFilePath = Paths.get(tempDir, "JavaAppRunner.java");
        Files.write(tempFilePath, data.getBytes());

        Process javac = Runtime.getRuntime().exec("javac " + tempFilePath);
        int exitCode = javac.waitFor();

        if(exitCode==0){
            Process java = Runtime.getRuntime().exec("java -cp " + tempDir + "JavaAppRunner");
            exitCode=java.waitFor();

            if(exitCode==0){
                readStream(java.getInputStream());
            }else{
                readStream(java.getErrorStream());

            }

        }else{
readStream(javac.getErrorStream());
        }

    }catch(IOException | InterruptedException e){
        e.printStackTrace();
    }finally {
        Path classFilePath = Paths.get(System.getProperty("java.io.tmpdir"), "JavaAppRunner.java");
        Path javaFilePath = Paths.get(System.getProperty("java.io.tmpdir"), "JavaAppRunner.java");
        Files.delete(classFilePath);
        Files.delete(javaFilePath);
    }

    }

    public void readStream(InputStream is) throws IOException {
        byte[] buffer=new byte[is.available()];
        is.read(buffer);
        txtOutput.setText(new String(buffer));
        is.close();
    }
}
