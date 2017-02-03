package com.github.palmeidaprog.checklisting.main;

/*
* Check Listing
* @author Paulo R. Almeida Filho
* http://www.github.com/palmeidaprog/check-listing
* @email palmeidaprogramming@gmail.com
*/

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PasswordController implements Initializable {
    @FXML private PasswordField passTF;
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
            unlock = true;
            passwordStage.close();
            MainController.getInstance().lock(false);
        }
        else {
            passTF.setText("");
            for(int i = 0; i < 3; i++) {
                passTF.requestFocus();
            }
        }
    }

    // exit button event
    public void exitBtnClick() {
        Platform.exit();
    }

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


}
