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



    //--PasswordControllable Interface methods----------------------------

    @Override
    public void setStage(Stage stage) {
        passwordStage = stage;
    }
}
