package sample.com.github.palmeidaprog.checklisting.main;

/*
* Check Listing
* @author Paulo R. Almeida Filho
* http://www.github.com/palmeidaprog/check-listing
* @email palmeidaprogramming@gmail.com
*/

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
    @FXML private Button newBtn, removeBtn, newCategoryBtn;

    //--Singleton design---------------------------------------------------------
    private volatile static MainController instance = null;
    private MainController() { }
    public static synchronized MainController getInstance() {
        if(instance == null) {
            instance = new MainController();
        }
        return instance;
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
