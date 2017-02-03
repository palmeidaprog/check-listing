package com.github.palmeidaprog.checklisting.main;

/*
* Check Listing
* @author Paulo R. Almeida Filho
* http://www.github.com/palmeidaprog/check-listing
* @email palmeidaprogramming@gmail.com
*/

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PasswordController implements Initializable {
    @FXML private PasswordField passTF;
    private Stage passwordStage;

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

    public void unlockBtnClick() {
        //close();
    }

    public void setStage(Stage stage) {
        passwordStage = stage;
    }
}
