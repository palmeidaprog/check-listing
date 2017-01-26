package com.github.palmeidaprog.checklisting.data;

/*
* Check Listing
* @author Paulo R. Almeida Filho
* http://www.github.com/palmeidaprog/check-listing
* @email palmeidaprogramming@gmail.com
*/

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import java.io.Serializable;

public class ToDoData implements Serializable {
    // data
    private transient CheckBox check;
    private String category, description;
    private boolean checked = false; // stores CheckBox

    // created new todoTable member contructor
    public ToDoData() {
        check = new CheckBox();
        check.setSelected(false);
        createCheckBoxEvent();
    }

    // create new instances read from Serialized Obj
    public ToDoData(ToDoData t) {
        check = new CheckBox();
        check.setSelected(t.isSelected());
        checked = t.isSelected();

    }

    //--Data Model-------------------------------------------------------

    public CheckBox getCheck() {
        return check;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String cat) {
        category = cat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String d) {
        description = d;
    }

    //--Serializable interface implementation-----------------------------------------------

    public String toString() {
        return "category=" + category +"; descrition=" + description + "checked=" + checked;
    }

    //--Other Serialization related and object write/read methods----------------------------

    //
    public boolean isSelected() {
        return checked;
    }

    private void createCheckBoxEvent() {
        check.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                checked = check.isSelected();
                System.out.println();
            }
        });
    }

}
