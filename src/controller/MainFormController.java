package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MainFormController {
    public TextArea txtEditor;
    public Button btnRun;
    public TextArea txtOutput;

    public void btnRun_OnAction(ActionEvent event) {
        String data = "public class DEP8IDEDemo{\n" + "public static void main(String args[]){\n" + txtEditor.getText() + "\n}\n" + "}";

    }
}
