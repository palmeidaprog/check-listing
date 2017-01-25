package sample.com.github.palmeidaprog.checklisting.main;

/*
* Check Listing
* @author Paulo R. Almeida Filho
* http://www.github.com/palmeidaprog/check-listing
* @email palmeidaprogramming@gmail.com
*/

import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;

/**
 * Created by paulo on 1/24/17.
 */
public class UIEffects {

    //--Singleton design---------------------------------------------------------
    private volatile static UIEffects instance = null;
    private UIEffects() { }
    public static synchronized UIEffects getInstance() {
        if(instance == null) {
            instance = new UIEffects();
        }
        return instance;
    }

    //--Effects methods--------------------------------------------------------------------

    /*Add or remove drop shadow to button
    * @param btn JavaFX button to add effect
    * @paraam m MouseEvent.EXIT place shadow back and MouseEvent.ENTER to remove shadow*/
    protected void btnShadow(Button btn, MouseEvent m) {
        switch(m) {
            case ENTER:
                btn.setEffect(null);
                break;
            case EXIT:
                DropShadow ds = new DropShadow();
                ds.setOffsetY(2.0);
                ds.setOffsetX(2.0);
                ds.setBlurType(BlurType.GAUSSIAN);
                btn.setEffect(ds);
                break;
        }
    }
}
