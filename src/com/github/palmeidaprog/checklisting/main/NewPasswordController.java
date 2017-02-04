package com.github.palmeidaprog.checklisting.main;

import com.github.palmeidaprog.checklisting.interfaces.PasswordControllable;
import javafx.stage.Stage;

/*
* new_password_dialog.fxml'ss Controller
* Check Listing
* @author Paulo R. Almeida Filho
* http://www.github.com/palmeidaprog/check-listing
* @email palmeidaprogramming@gmail.com
*/

public class NewPasswordController implements PasswordControllable {
    private Stage passwordStage;
    private PassDialogMode mode = PassDialogMode.CHANGE;

    //--Singleton design--------------------------------------------------

    private volatile static NewPasswordController instance = null;
    private NewPasswordController() {  }
    public synchronized static NewPasswordController getInstance() {
        if(instance == null) {
            instance = new NewPasswordController();
        }
        return instance;
    }

    //--------------------------------------------------------------------

    public void setMode(PassDialogMode passDialogMode) {
        mode = passDialogMode;
    }

    public PassDialogMode getMode() {
        return mode;
    }

    //--PasswordControllable Interface methods----------------------------

    @Override
    public void setStage(Stage stage) {
        passwordStage = stage;
    }

    @Override
    public Stage getStage() {
        return passwordStage;
    }
}
