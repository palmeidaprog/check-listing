package com.github.palmeidaprog.checklisting.interfaces;

import javafx.stage.Stage;

/*
* Password Dialogs Controller Interface
* Check Listing
* @author Paulo R. Almeida Filho
* http://www.github.com/palmeidaprog/check-listing
* @email palmeidaprogramming@gmail.com
*/

public interface PasswordControllable {
    void setStage(Stage stage);
    Stage getStage();
    void show();
}
