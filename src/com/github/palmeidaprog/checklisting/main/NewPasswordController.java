package com.github.palmeidaprog.checklisting.main;

import com.github.palmeidaprog.checklisting.data.EncryptPass;
import com.github.palmeidaprog.checklisting.interfaces.PasswordControllable;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
    @FXML private Label labelError, titleLbl, oldLabel;
    @FXML private Button okBtn;

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

    // ok button event
    public void okBtnClick() {
        if(!oldPassTF.isDisable()) {
            oldPassAction();
        }
        else if(!newPassTF.isDisable()) {
            newPassAction();
        }
        else {
            retypePassAction();
        }
    }

    // cancel button event
    public void cancelBtnClick() {
        passwordStage.close();
    }

    // oldpassTF action
    public void oldPassAction() {
        if(oldPassTF.getText().equals(Settings.getInstance().getPassword())) {
            activateTextField(newPassTF);
        }
        else {
            oldPassTF.setText("");
            activateTextField(oldPassTF);
            displayErrorMsg("Wrong Password");
        }
    }

    // newPassTF action
    public void newPassAction() {
        if(!newPassTF.getText().equals("")) {
            activateTextField(retypePassTF);
            switch(mode) {
                case NEW:
                    okBtn.setText("SET");
                    break;
                case CHANGE:
                    okBtn.setText("CHANGE");
                    break;
            }
        }
        else {
            displayErrorMsg("The password cannot be empty");
            newPassTF.requestFocus();
        }
    }

    // retypePassTF action
    public void retypePassAction() {
        if(retypePassTF.getText().equals(newPassTF.getText())) {
            try {
                Settings.getInstance().setSalt(EncryptPass.getSalt());
                Settings.getInstance().setPassword(EncryptPass
                        .generateStorngPasswordHash(retypePassTF.getText(),
                                Settings.getInstance().getSalt()));
            } catch(NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }
            passwordStage.close();
        }
        else {
            newPassTF.setText("");
            retypePassTF.setText("");
            activateTextField(newPassTF);
            displayErrorMsg("The retyped password doesn't match the new");
        }
    }


    //---Support methods---------------------------------------------------------

    // display a messsage error within a Label below TextFields
    private void displayErrorMsg(String message) {
        labelError.setText(message);
        labelError.setVisible(true);
    }

    // Activate mode
    private void activateMode() {
        switch(mode) {
            case CHANGE:
                passwordStage.setTitle("Change Password");
/*                oldLabel.setDisable(false);
                titleLbl.setText("Change Password");*/
                activateTextField(oldPassTF);
                break;
            case NEW:
                oldLabel.setDisable(true);
/*                titleLbl.setText("Create Password");
                passwordStage.setTitle("Create Password");*/
                activateTextField(newPassTF);
                break;
        }
    }

    // activate proper TextField and request focus
    private void activateTextField(TextField tx) {
        labelError.setVisible(false);
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
