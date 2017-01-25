package sample.com.github.palmeidaprog.checklisting.main;

/*
* Check Listing
* @author Paulo R. Almeida Filho
* http://www.github.com/palmeidaprog/check-listing
* @email palmeidaprogramming@gmail.com
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage mainStage;

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
        mainStage.setTitle("Check Listing - To Do List Manager");
        mainStage.setScene(new Scene(root, 670, 700));
        mainStage.show();
    }

    public static Stage getStage() {
        return mainStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}