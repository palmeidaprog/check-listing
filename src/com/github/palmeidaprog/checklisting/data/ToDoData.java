package com.github.palmeidaprog.checklisting.data;

/*
* Check Listing
* @author Paulo R. Almeida Filho
* http://www.github.com/palmeidaprog/check-listing
* @email palmeidaprogramming@gmail.com
*/

import javafx.scene.control.CheckBox;

public class ToDoData {
    public CheckBox getCheck() {
        return check;
    }

    // data
    private transient CheckBox check = new CheckBox();

    private String category, description;


    //--Data Model-------------------------------------------------------

    public void setCheck(CheckBox check) {
        this.check = check;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
