package com.github.palmeidaprog.checklisting.main;

/*
* Check Listing
* @author Paulo R. Almeida Filho
* http://www.github.com/palmeidaprog/check-listing
* @email palmeidaprogramming@gmail.com
*/

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import com.github.palmeidaprog.checklisting.data.ToDoData;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    // top HBox buttons
    @FXML private Button newBtn, removeBtn, newCategoryBtn;

    // To Do Table
    @FXML private TreeTableView<ToDoData> todoTable;
    @FXML private TreeTableColumn<ToDoData, CheckBox> checkCol;
    @FXML private TreeTableColumn<ToDoData, String> categoryCol, descriptionCol;
    private ObservableList<ToDoData> todoList = FXCollections.observableArrayList();

    //--Singleton design---------------------------------------------------------
    private volatile static MainController instance = null;
    private MainController() { }
    public static synchronized MainController getInstance() {
        if(instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    //--Implementing Initializable interface----------------------------------------------------

    @Override
    public void initialize(URL u, ResourceBundle rb) {
        // To Do TableView //todo: uncomment when implement ToDoClass
        /*checkCol.setCellValueFactory(new PropertyValueFactory<>("check"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));*/
    }

    //--Events methods--------------------------------------------------------------------------

    public void mouseButtonEnter(Button btn) {
        UIEffects.getInstance().btnShadow(btn, MouseEvent.ENTER);
    }

    public void mouseButtonExit(Button btn) {
        UIEffects.getInstance().btnShadow(btn, MouseEvent.EXIT);
    }

    public void newBtnEnter() {
        mouseButtonEnter(newBtn);
    }

    public void newBtnExit() {
        mouseButtonExit(newBtn);
    }

    public void removeBtnEnter() {
        mouseButtonEnter(removeBtn);
    }

    public void removeBtnExit() {
        mouseButtonExit(removeBtn);
    }

    public void newCategoryBtnEnter() {
        mouseButtonEnter(newCategoryBtn);
    }

    public void newCategoryBtnExit() {
        mouseButtonExit(newCategoryBtn);
    }
}
