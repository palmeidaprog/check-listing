package com.github.palmeidaprog.checklisting.main;

/*
* Check Listing
* @author Paulo R. Almeida Filho
* http://www.github.com/palmeidaprog/check-listing
* @email palmeidaprogramming@gmail.com
*/

import com.github.palmeidaprog.checklisting.data.DataIO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import com.github.palmeidaprog.checklisting.data.ToDoData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML private HBox topBox;
    @FXML private VBox mainBox;

    // top HBox buttons
    @FXML private Button newBtn, removeBtn, newCategoryBtn;

    // To Do Table
    @FXML private TableView<ToDoData> todoTable;
    @FXML private TableColumn<ToDoData, CheckBox> checkCol;
    @FXML private TableColumn<ToDoData, String> categoryCol, descriptionCol;
    private ObservableList<ToDoData> todoList = FXCollections.observableArrayList();

    // Password Dialog
    private Stage passwordDialog = new Stage();

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
        todoList = DataIO.readObjList();

        // To Do TableView
        todoTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        todoTable.setItems(todoList);
        todoTable.setPlaceholder(new Label("Lista vazia"));
        checkCol.setCellValueFactory(new PropertyValueFactory<>("check"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryCol.setOnEditCommit(editCellEvent("category"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionCol.setOnEditCommit(editCellEvent("description"));

        loadPasswordDialog();

        // password lock
        lock(true);
        passwordDialog.showAndWait();
    }

    // Load FXML into passwordDialog stage
    private void loadPasswordDialog() {
        FXMLLoader passwordLoad = new FXMLLoader(getClass().getResource("password_dialog.fxml"));
        passwordLoad.setController(PasswordController.getInstance());
        Parent passRoot = null;
        try {
            passRoot = passwordLoad.load();
        } catch(IOException e) {
            System.err.println("Couln't load password_dialog.fxml");
            e.printStackTrace();
        }
        passwordDialog.setTitle("Password Protected");
        passwordDialog.setScene(new Scene(passRoot, 430, 180));
        passwordDialog.initStyle(StageStyle.UNDECORATED);
        PasswordController.getInstance().setStage(passwordDialog);
    }

    //--Enter/Exxit Events methods--------------------------------------------------------------------------

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

    //--Click Event-----------------------------------------------------------------------------------------

    private EventHandler<TableColumn.CellEditEvent<ToDoData,String>> editCellEvent(String col) {
        EventHandler<TableColumn.CellEditEvent<ToDoData, String>> editingCell =
                editingCell = new EventHandler<TableColumn.CellEditEvent<ToDoData, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ToDoData, String> e) {
                if(col.equals("category")) {
                    e.getTableView().getItems().get(
                            e.getTablePosition().getRow()).setCategory(e.getNewValue());
                }
                else if(col.equals("description")) {
                    e.getTableView().getItems().get(
                            e.getTablePosition().getRow()).setDescription(e.getNewValue());
                }
            }
        };
        updateObjOnClose();
        return editingCell;
    }

    public void newBtnClick() {
        todoList.add(new ToDoData());
        updateObjOnClose();
    }

    public void removeBtnClick() {
        // todo: getX() and getY() to calculate dialog show position
        if(todoTable.getSelectionModel().getSelectedItem() != null) {
            Alert showAlert = new Alert(Alert.AlertType.CONFIRMATION);
            showAlert.setTitle("Delete");
            showAlert.setHeaderText("Deleting selected items");
            showAlert.setContentText("Are you sure?");

            Optional<ButtonType> result = showAlert.showAndWait();

            // Asks if you want to see the windoww
            if (result.get() == ButtonType.OK) {
                Dialog dialog = new Dialog();
                List<ToDoData> toRemove = new ArrayList<>();
                toRemove.addAll(todoTable.getSelectionModel().getSelectedItems());
                todoList.removeAll(toRemove);
                updateObjOnClose();
            }
        }
    }

    //--Close Event-----------------------------------------------------------------------------------------

    public void updateObjOnClose() {
        DataIO.updateObjFile(todoList);
    }


    // lock / unlock event
    protected void lock(boolean b) {
        UIEffects.getInstance().blur(mainBox, b ? MouseEvent.LOCK : MouseEvent.UNLOCK);
        mainBox.setDisable(b);
        UIEffects.getInstance().blur(topBox, b ? MouseEvent.LOCK : MouseEvent.UNLOCK);
        topBox.setDisable(b);
    }
}
