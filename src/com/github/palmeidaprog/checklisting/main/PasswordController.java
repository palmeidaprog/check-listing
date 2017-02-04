package com.github.palmeidaprog.checklisting.main;

/*
* password_dialog.fxml's Controller
* Check Listing
* @author Paulo R. Almeida Filho
* http://www.github.com/palmeidaprog/check-listing
* @email palmeidaprogramming@gmail.com
*/

import com.github.palmeidaprog.checklisting.interfaces.PasswordControllable;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PasswordController implements Initializable, PasswordControllable {
    @FXML private PasswordField passTF;
    @FXML private Label label;
    private int tries = 0;
    private Stage passwordStage;
    private boolean unlock = false;
    private final String DUMMY = "1234"; // dummy password, just for testing purposes

    //--Singleton design--------------------------------------------

    private volatile static PasswordController instance = null;
    private PasswordController() {  }
    public synchronized static PasswordController getInstance() {
        if(instance == null) {
            instance = new PasswordController();
        }
        return instance;
    }

    //--------------------------------------------------------------

    public void initialize(URL u, ResourceBundle rb) {

    }

    // Unlock btn event
    public void unlockBtnClick() {
        if(passTF.getText().equals(DUMMY)) {
            tries = 0;
            unlock = true;
            passwordStage.close();
            label.setText("Please type the password to unlock:");
            passTF.setText("");
            label.setStyle(null);
            MainController.getInstance().lock(false, null);
        }
        else if(tries < 3){
            tries++;
            passTF.setText("");
            passTF.requestFocus();
            label.setText("WRONG PASSWORD! " + tries + " out of 3");
            label.setStyle("-fx-text-fill: red");
        }
        else {
            exitBtnClick();
        }
    }

    // exit button event
    public void exitBtnClick() {
        passwordStage.close();
        Platform.exit();
    }

    // PasswordControllable interface method
    @Override
    public void setStage(Stage stage) {
        passwordStage = stage;
        passwordStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                if(!unlock) {
                    Platform.exit();
                }
                else {
                    unlock = false;
                }
            }
        });
    }

    @Override
    public Stage getStage() {
        return passwordStage;
    }
}
