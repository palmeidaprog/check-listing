package com.github.palmeidaprog.checklisting.main;

import com.github.palmeidaprog.checklisting.interfaces.PasswordControllable;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

/*
* new_password_dialog.fxml'ss Controller
* Check Listing
* @author Paulo R. Almeida Filho
* http://www.github.com/palmeidaprog/check-listing
* @email palmeidaprogramming@gmail.com
*/

public class NewPasswordController implements PasswordControllable, Initializable {
    private Stage passwordStage;
    private PassDialogMode mode = PassDialogMode.CHANGE;
    @FXML private TextField oldPassTF, newPassTF, retypePassTF;

    //--Singleton design--------------------------------------------------

    private volatile static NewPasswordController instance = null;
    private NewPasswordController() {  }
    public synchronized static NewPasswordController getInstance() {
        if(instance == null) {
            instance = new NewPasswordController();
        }
        return instance;
    }

    //--Initializable interface-------------------------------------------

    @Override
    public void initialize(URL u, ResourceBundle rb) {
        activateMode();
    }

    //--Events methods--------------------------------------------------------------

    // oldpassTF action
    public void oldPassAction() {
        if(oldPassTF.getText().equals(Settings.getInstance().getPassword())) {
            activateTextField(newPassTF);
        }
        else {
            oldPassTF.setText("");
            activateTextField(oldPassTF);
        }
    }

    // newPassTF action
    public void newPassAction() {
        activateTextField(retypePassTF);
    }

    // retypePassTF action
    public void retypePassAction() {
        if(retypePassTF.getText().equals(newPassTF.getText())) {
            Settings.getInstance().setPassword(newPassTF.getText());
            passwordStage.close();
        }
        else {
            newPassTF.setText("");
            retypePassTF.setText("");
            activateTextField(newPassTF);
        }
    }


    //---Support methods---------------------------------------------------------

    // Activate mode
    private void activateMode() {
        switch(mode) {
            case CHANGE:
                activateTextField(oldPassTF);
                break;
            case NEW:
                activateTextField(newPassTF);
                break;
        }
    }

    // activate proper TextField and request focus
    private void activateTextField(TextField tx) {
        oldPassTF.setDisable(true);
        newPassTF.setDisable(true);
        retypePassTF.setDisable(true);
        tx.setDisable(false);
        tx.requestFocus();
    }

    protected void setMode(PassDialogMode passDialogMode) {
        mode = passDialogMode;
    }

    protected PassDialogMode getMode() {
        return mode;
    }

    //--PasswordControllable Interface methods----------------------------

    @Override
    public void show() {
        System.out.println("show"); //@debug
        oldPassTF.setText("");
        newPassTF.setText("");
        retypePassTF.setText("");
        activateMode();
        passwordStage.showAndWait();
    }

    @Override
    public void setStage(Stage stage) {
        passwordStage = stage;
        passwordStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {

            }
        });
    }

    @Override
    public Stage getStage() {
        return passwordStage;
    }
}
