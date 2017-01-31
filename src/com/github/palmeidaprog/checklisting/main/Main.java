package com.github.palmeidaprog.checklisting.main;

/*
* Check Listing
* @author Paulo R. Almeida Filho
* http://www.github.com/palmeidaprog/check-listing
* @email palmeidaprogramming@gmail.com
*/

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class Main extends Application {
    private static Stage mainStage;
    private final double WIDTH = 670, HEIGHT = 700;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;

        // FXML Loader
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        mainLoader.setController(MainController.getInstance());
        Parent root = null;
        try {
            root = mainLoader.load();
        } catch(IOException e) {
            System.err.println("Couldn't load main.fxml");
            e.printStackTrace();
        }
        mainStage.getIcons().add(new Image(getClass()
                .getResourceAsStream("resources/todo_trans128.png")));

        /*if(System.getProperty("os.name").toLowerCase().contains("mac")) {
            osx();
        }*/

        mainStage.setTitle("Check Listing v0.1 alpha - To Do List Manager");
        mainStage.setScene(new Scene(root, WIDTH, HEIGHT));
        mainStage.show();

        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                MainController.getInstance().updateObjOnClose();
            }
        });
    }


    /*public void osx() {
        // Mac OS X specific code TODO: Only use when compiling in OS X
*//*        try {
            com.apple.eawt.Application.getApplication().setDockIconImage(ImageIO.read(getClass()
                    .getResourceAsStream("../resources/todo_trans128.png")));
        } catch(IOException e) {
            System.err.println("Couldn't find the icon");
            e.printStackTrace();
        }*//*

        *//*MenuBar menu = new MenuBar();
        Menu file = new Menu();
        MenuItem quit = new MenuItem();
        quit.setText("Quit");
        file.setText("File");
        file.getItems().add(quit);
        menu.getMenus().add(file);
        menu.useSystemMenuBarProperty().set(true);*//*

        //com.apple.eawt.Application.getApplication().setDefaultMenuBar(menu);

    }*/

    public static Stage getStage() {
        return mainStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
